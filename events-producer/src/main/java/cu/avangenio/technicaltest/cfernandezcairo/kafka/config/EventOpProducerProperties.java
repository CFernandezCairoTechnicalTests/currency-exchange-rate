package cu.avangenio.technicaltest.cfernandezcairo.kafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "spring.kafka.template")
@Getter
@Setter
public class EventOpProducerProperties {
    private String defaultTopic;
}
