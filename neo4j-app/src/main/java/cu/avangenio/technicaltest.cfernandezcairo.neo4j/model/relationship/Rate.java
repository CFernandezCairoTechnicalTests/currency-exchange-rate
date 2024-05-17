package cu.avangenio.technicaltest.cfernandezcairo.neo4j.model.relationship;

import cu.avangenio.technicaltest.cfernandezcairo.neo4j.model.node.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RelationshipProperties
public class Rate {

    @RelationshipId
    private Long id;

    @TargetNode
    private Currency currency;

    @Property
    private LocalDateTime date;

    @Property
    private float rate;

}
