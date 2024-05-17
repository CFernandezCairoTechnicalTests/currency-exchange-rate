package cu.avangenio.technicaltest.cfernandezcairo.kafka.controller;

import cu.avangenio.technicaltest.cfernandezcairo.kafka.dto.EventRateOpDTO;
import cu.avangenio.technicaltest.cfernandezcairo.kafka.kafka.KakfaProducer;
import cu.avangenio.technicaltest.cfernandezcairo.kafka.vo.CurrencyEnum;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.time.*;
import java.util.Random;
import java.util.UUID;
import java.util.random.RandomGenerator;

@RestController
@RequiredArgsConstructor
public class EventOpController {

    @Autowired
    private final KakfaProducer kakfaProducer;

    @Value( "${application.amounts-events:1000}" )
    private int eventsAmount;
    @Value( "${application.bound-events:6000}" )
    private int delayBetweenEvents;

    @PostMapping("/events")
    public String publishEvent(@RequestBody EventRateOpDTO eventRateOpDTO) {
       this.kakfaProducer.sendEvent(eventRateOpDTO);
        return "Published Event:" + eventRateOpDTO;
    }

    @SneakyThrows
    @GetMapping("/events/populate")
    public String populateMessage(){

        Random rn = new Random();

        Instant random;
        for(int i = 0; i < eventsAmount; i++) {
            String from = CurrencyEnum.currencies.get(rn.nextInt(CurrencyEnum.currenciesAmount));
            String to = CurrencyEnum.currencies.get(rn.nextInt(CurrencyEnum.currenciesAmount));

            if(!from.equals(to)) {
                EventRateOpDTO current = EventRateOpDTO.builder().build().builder()
                        .key(UUID.randomUUID())
                        .from(from)
                        .to(to)
                        .rate(rn.nextFloat())
                        .date(LocalDateTime.now())
                        .build();
                kakfaProducer.sendEvent(current.getKey().toString(), current);
            }

            Thread.sleep(RandomGenerator.getDefault().nextInt(delayBetweenEvents));

        }
        return "OK";
    }
}
