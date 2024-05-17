package cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorConfig {
    private String code;
    private String message;
}
