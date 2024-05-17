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

@Document(indexName = "rate-operations")
@Data
@Builder
public class RateOp {
    @Id
    UUID key;
    String from;
    String to;
    float rate;
    //@Field(type = FieldType.Date, format = DateFormat.date, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSS")
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second_millis)
    LocalDateTime date;
}
