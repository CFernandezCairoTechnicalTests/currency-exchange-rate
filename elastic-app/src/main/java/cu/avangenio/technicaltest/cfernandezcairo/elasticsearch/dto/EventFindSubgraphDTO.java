package cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
