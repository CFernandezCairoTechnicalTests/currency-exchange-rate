package cu.avangenio.technicaltest.cfernandezcairo.neo4j.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class EventFindSubgraphDTO {

    private UUID requestKey;
    private UUID key;
    private String from;
    private String to;
    private Integer amountExchange;
    private Double finalExchange;
    private LocalDateTime mediaDateExchange;

}
