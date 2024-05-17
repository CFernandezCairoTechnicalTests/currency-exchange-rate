package cu.cfernandezcairo.rateproducer;

import cu.avangenio.technicaltest.cfernandezcairo.kafka.dto.EventRateOpDTO;
import cu.avangenio.technicaltest.cfernandezcairo.kafka.kafka.KakfaProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@Testcontainers
@SpringBootTest
class CurrencyEnumExchangeRateProducerApplicationTests {

	@Container
	static KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));

	@DynamicPropertySource
	static void kafkaProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.kafka.bootstrap-servers", kafkaContainer::getBootstrapServers);
	}

	@Autowired
	private KakfaProducer currencyKafkaProducer;

	@Test
	void testProduceAndConsumeKafkaMessage() {
		EventRateOpDTO eventRateOpDTO = EventRateOpDTO.builder()
				.key(UUID.randomUUID())
				.from("CUP")
				.to("USD")
				.date(LocalDateTime.now())
				.rate(385).build();

		currencyKafkaProducer.sendEvent(eventRateOpDTO);

	}

}
