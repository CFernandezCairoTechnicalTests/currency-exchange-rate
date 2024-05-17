package cu.avangenio.technicaltest.cfernandezcairo.neo4j.kafka;

import cu.avangenio.technicaltest.cfernandezcairo.neo4j.config.EventProperties;
import cu.avangenio.technicaltest.cfernandezcairo.neo4j.dto.EventFindSubgraphDTO;
import cu.avangenio.technicaltest.cfernandezcairo.neo4j.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final EventProperties eventProperties;
    private final EventMapper eventMapper;

    public void sendEvent(final String event) {
        this.kafkaTemplate.send(this.eventProperties.getDefaultTopic(), event);
    }

    public void sendEvent(final EventFindSubgraphDTO event) {
        this.kafkaTemplate.send(this.eventProperties.getDefaultTopic(), eventMapper.convertEventFindSubgraphDTOToJson(event));
    }

    public void sendEvent(final String key, final EventFindSubgraphDTO event) {
        this.kafkaTemplate.send(this.eventProperties.getDefaultTopic(), key, eventMapper.convertEventFindSubgraphDTOToJson(event));
    }

}


