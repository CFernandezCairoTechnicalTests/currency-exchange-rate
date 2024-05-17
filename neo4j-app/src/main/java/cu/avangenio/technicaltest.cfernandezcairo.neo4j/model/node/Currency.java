package cu.avangenio.technicaltest.cfernandezcairo.neo4j.model.node;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import cu.avangenio.technicaltest.cfernandezcairo.neo4j.model.relationship.Rate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = JSOGGenerator.class)
@Node("Curency")
public class Currency {

    @Id
    @Property("title")
    private String title;

    @Relationship(type="RATE", direction= Relationship.Direction.INCOMING)
    //@org.neo4j.ogm.annotation.Relationship(type="RATE", direction = "UNDIRECTED")
    private Set<Rate> rates = new HashSet<>();

}