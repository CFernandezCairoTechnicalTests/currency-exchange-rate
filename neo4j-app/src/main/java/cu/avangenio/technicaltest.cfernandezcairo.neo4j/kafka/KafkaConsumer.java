package cu.avangenio.technicaltest.cfernandezcairo.neo4j.kafka;

import cu.avangenio.technicaltest.cfernandezcairo.neo4j.dto.EventRateOpDTO;
import cu.avangenio.technicaltest.cfernandezcairo.neo4j.mapper.EventMapper;
import cu.avangenio.technicaltest.cfernandezcairo.neo4j.service.CurrencyService;
import org.springframework.messaging.handler.annotation.Payload;
import cu.avangenio.technicaltest.cfernandezcairo.neo4j.model.node.Currency;
import cu.avangenio.technicaltest.cfernandezcairo.neo4j.model.relationship.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class KafkaConsumer {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private EventMapper eventMapper;

    @KafkaListener(topics = "${spring.kafka.topics.rate-ops-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeMessage(String message) {
        EventRateOpDTO currentMSG = eventMapper.convertEventRateOpDTOFromJson(message);
        try {
            Currency from = Currency.builder()
                    .title(currentMSG.getFrom())
                    .rates(new HashSet<>())
                    .build();
            Currency to = Currency.builder()
                    .title(currentMSG.getTo())
                    .rates(new HashSet<>())
                    .build();

            from.getRates().add(Rate.builder()
                    .currency(to)
                    .date(currentMSG.getDate())
                    .rate(currentMSG.getRate())
                    .build());
            //currencyService.createOrUpdate(from);
            currencyService.update(from);
            System.out.println("Created:" + currentMSG);
        } catch (Exception e) {
            //throw new RuntimeException(e);
            System.out.println("Exception:" + currentMSG);
        }

    }

    /*@KafkaListener(
            topics = "${spring.kafka.topics.rate-ops-topic}",
            groupId = "${spring.kafka.consumer.group-id}",
            properties = {
                    "spring.json.value.default.type=cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.dto.EventRateOpDTO"
            })
    public void handleRateOpDTO(@Payload EventRateOpDTO eventRateOpDTO) {
        try {
            Currency from = Currency.builder()
                    .title(eventRateOpDTO.getFrom())
                    .rates(new HashSet<>())
                    .build();
            Currency to = Currency.builder()
                    .title(eventRateOpDTO.getTo())
                    .rates(new HashSet<>())
                    .build();

            from.getRates().add(Rate.builder()
                    .currency(to)
                    .date(eventRateOpDTO.getDate())
                    .rate(eventRateOpDTO.getRate())
                    .build());

            //currencyService.createOrUpdate(from);
            currencyService.update(from);
            System.out.println("Created:" + eventRateOpDTO);
        } catch (Exception e) {
            //throw new RuntimeException(e);
            System.out.println("Exception:" + eventRateOpDTO);
            System.out.println("Exception Message:" + e.getMessage());
        }
    }*/
}
