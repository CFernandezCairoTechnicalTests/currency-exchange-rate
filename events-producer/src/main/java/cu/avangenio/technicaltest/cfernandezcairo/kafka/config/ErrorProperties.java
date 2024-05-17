package cu.avangenio.technicaltest.cfernandezcairo.kafka.config;

import cu.avangenio.technicaltest.cfernandezcairo.kafka.dto.ErrorConfig;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "errors")
public class ErrorProperties {
    private ErrorConfig conversionJson;
}