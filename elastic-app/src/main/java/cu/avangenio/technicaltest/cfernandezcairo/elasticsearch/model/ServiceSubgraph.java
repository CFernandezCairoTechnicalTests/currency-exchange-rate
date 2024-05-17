package cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(indexName = "moneda-findsubgraph-service")
@Data
@Builder
public class ServiceSubgraph {
    @Id
    private UUID key;
    private UUID requestKey;
    private String from;
    private String to;
    private Integer amountExchange;
    private Double finalExchange;
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second_millis)
    private LocalDateTime date;
}
