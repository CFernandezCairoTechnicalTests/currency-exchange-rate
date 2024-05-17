package cu.avangenio.technicaltest.cfernandezcairo.kafka.kafka;

import cu.avangenio.technicaltest.cfernandezcairo.kafka.config.EventOpProducerProperties;
import cu.avangenio.technicaltest.cfernandezcairo.kafka.dto.EventRateOpDTO;
import cu.avangenio.technicaltest.cfernandezcairo.kafka.mapper.CurrencyExchangeRateEventMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class KakfaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final EventOpProducerProperties eventProperties;
    private final CurrencyExchangeRateEventMapper eventMapper;

    public void sendEvent(final String event) {
        this.kafkaTemplate.send(this.eventProperties.getDefaultTopic(), event);
    }

    public void sendEvent(final EventRateOpDTO event) {
        this.kafkaTemplate.send(this.eventProperties.getDefaultTopic(), eventMapper.convertToJson(event));
    }

    public void sendEvent(final String key, final EventRateOpDTO event) {
        this.kafkaTemplate.send(this.eventProperties.getDefaultTopic(), key, eventMapper.convertToJson(event));
    }

}


