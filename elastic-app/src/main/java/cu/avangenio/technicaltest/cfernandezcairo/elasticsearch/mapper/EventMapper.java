package cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.config.ErrorProperties;
import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.dto.ErrorConfig;
import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.dto.EventRateOpDTO;
import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.exception.JsonConversionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EventMapper {

    private final ObjectMapper objectMapper;
    private final ErrorProperties errorProperties;

    public EventRateOpDTO convertFromJson(final String event) {
        try {
            return objectMapper.readValue(event, EventRateOpDTO.class);
        } catch (JsonProcessingException e) {
            ErrorConfig errorConfig = errorProperties.getConversionJson();
            throw new JsonConversionException(errorConfig.getCode(), errorConfig.getMessage(), e);
        }
    }
}

