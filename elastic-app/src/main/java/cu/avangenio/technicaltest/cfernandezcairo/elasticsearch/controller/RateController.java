package cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.controller;

import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.kafka.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/rates")
public class RateController {

    @Autowired
    private KafkaConsumer kafkaConsumer;

    Message<Object> msgReceived;
    @GetMapping("/ingest")
    public String ingest() {

        //kafkaConsumer.listen(msgReceived);
        return "OK";
    }

}
