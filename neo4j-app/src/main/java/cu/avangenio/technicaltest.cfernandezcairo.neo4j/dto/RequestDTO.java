package cu.avangenio.technicaltest.cfernandezcairo.neo4j.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class RequestDTO {

    private String from;
    private String to;
    private LocalDateTime start;

}
