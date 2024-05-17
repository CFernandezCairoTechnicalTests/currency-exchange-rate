package cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.kafka;

import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.dto.EventFindSubgraphDTO;
import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.dto.EventRateOpDTO;
import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.mapper.EventMapper;
import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.model.RateOp;
import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.model.ServiceSubgraph;
import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.service.RateOpService;
import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.service.ServiceSubgraphService;
import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.service.exception.DuplicateIdException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Log
public class KafkaConsumer {

    @Autowired
    private RateOpService rateOpService;

    @Autowired
    private ServiceSubgraphService subgraphService;

    @Autowired
    private EventMapper eventMapper;

    @KafkaListener(
            topics = "${spring.kafka.topics.rate-ops-topic}",
            groupId = "${spring.kafka.consumer.group-id}",
            properties = {
                    "spring.json.value.default.type=cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.dto.EventRateOpDTO"
            })
    public void handleRateOpDTO(@Payload EventRateOpDTO eventRateOpDTO) {
        try {
            RateOp newRateOp = RateOp.builder()
                    .key(eventRateOpDTO.getKey())
                    .from(eventRateOpDTO.getFrom())
                    .to(eventRateOpDTO.getTo())
                    .rate(eventRateOpDTO.getRate())
                    .date(eventRateOpDTO.getDate())
                    .build();
            rateOpService.create(newRateOp);
            System.out.println("Created:" + eventRateOpDTO);
        } catch (DuplicateIdException e) {
            //throw new RuntimeException(e);
            System.out.println("Exception:" + eventRateOpDTO);
            System.out.println("Exception Message:" + e.getMessage());
        }

    }

    @KafkaListener(
            topics = "${spring.kafka.topics.service-findsubgraph-topic}",
            groupId = "${spring.kafka.consumer.group-id}",
            properties = {
                    "spring.json.value.default.type=cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.dto.EventFindSubgraphDTO"
            })
    public void handleFindSubgraphDTO(@Payload EventFindSubgraphDTO eventFindSubgraphDTO) {
        try {
            ServiceSubgraph newServiceSubgraphRequestResult = ServiceSubgraph.builder()
                    .requestKey(eventFindSubgraphDTO.getRequestKey())
                    .key(eventFindSubgraphDTO.getKey())
                    .from(eventFindSubgraphDTO.getFrom())
                    .to(eventFindSubgraphDTO.getTo())
                    .amountExchange(eventFindSubgraphDTO.getAmountExchange())
                    .finalExchange(eventFindSubgraphDTO.getFinalExchange())
                    .date(eventFindSubgraphDTO.getMediaDateExchange())
                    .build();
            subgraphService.create(newServiceSubgraphRequestResult);
        } catch (DuplicateIdException e) {
            //throw new RuntimeException(e);
            System.out.println("Exception:" + eventFindSubgraphDTO);
        }
        System.out.println("Created:" + eventFindSubgraphDTO);
    }

    @KafkaHandler(isDefault = true)
    public void handleMessage(Object message) {
        Logger.getLogger(KafkaConsumer.class.toString()).info(String.format("The default message is : " + message.toString()));
    }

}
