package cu.avangenio.technicaltest.cfernandezcairo.neo4j.model.relationship;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatePropertiesResponse {

    private LocalDateTime date;
    private Float rate;

}
