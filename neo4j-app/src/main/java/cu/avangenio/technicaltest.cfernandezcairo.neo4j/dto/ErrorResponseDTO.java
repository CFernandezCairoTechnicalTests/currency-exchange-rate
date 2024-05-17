package cu.avangenio.technicaltest.cfernandezcairo.neo4j.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponseDTO {
    private final String errorCode;
    private final String message;
}

