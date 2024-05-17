package cu.avangenio.technicaltest.cfernandezcairo.neo4j.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class EventRateOpDTO {

    private UUID key;
    private String from;
    private String to;
    private LocalDateTime date;
    private float rate;;
}
