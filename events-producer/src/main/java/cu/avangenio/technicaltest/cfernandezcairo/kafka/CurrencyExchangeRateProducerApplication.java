package cu.avangenio.technicaltest.cfernandezcairo.kafka;

import cu.avangenio.technicaltest.cfernandezcairo.kafka.dto.EventRateOpDTO;
import cu.avangenio.technicaltest.cfernandezcairo.kafka.kafka.KakfaProducer;
import cu.avangenio.technicaltest.cfernandezcairo.kafka.vo.CurrencyEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;
import java.util.random.RandomGenerator;

@SpringBootApplication
public class CurrencyExchangeRateProducerApplication implements CommandLineRunner {

	@Autowired
	private KakfaProducer kakfaProducer;

	@Value( "${application.generate-events:true}" )
	private boolean generateEvents;

	@Value( "${application.amounts-events:1000}" )
	private int eventsAmount;
	@Value( "${application.bound-events:6000}" )
	private int delayBetweenEvents;

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeRateProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Random rn = new Random(656665);
		Instant random;

		if(generateEvents) {
			for(int i = 0; i < eventsAmount; i++) {
				String from = CurrencyEnum.currencies.get(rn.nextInt(CurrencyEnum.currenciesAmount));
				String to = CurrencyEnum.currencies.get(rn.nextInt(CurrencyEnum.currenciesAmount));

				while(to.equals(from)) {
					to = cu.avangenio.technicaltest.cfernandezcairo.kafka.vo.CurrencyEnum.currencies.get(rn.nextInt(CurrencyEnum.currenciesAmount));
				}

				if(!from.equals(to)) {
					cu.avangenio.technicaltest.cfernandezcairo.kafka.dto.EventRateOpDTO current = EventRateOpDTO.builder().build().builder()
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
		}

	}
}
