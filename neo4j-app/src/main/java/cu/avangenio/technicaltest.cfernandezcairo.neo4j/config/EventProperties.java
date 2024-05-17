package cu.avangenio.technicaltest.cfernandezcairo.neo4j.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.kafka.template")
@Getter
@Setter
public class EventProperties {
    private String defaultTopic;
}
