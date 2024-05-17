package cu.avangenio.technicaltest.cfernandezcairo.kafka.dto;

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
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    private float rate;;
}
