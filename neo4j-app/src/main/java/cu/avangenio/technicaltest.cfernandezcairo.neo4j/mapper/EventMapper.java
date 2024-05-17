package cu.avangenio.technicaltest.cfernandezcairo.neo4j.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cu.avangenio.technicaltest.cfernandezcairo.neo4j.config.ErrorProperties;
import cu.avangenio.technicaltest.cfernandezcairo.neo4j.dto.ErrorConfig;
import cu.avangenio.technicaltest.cfernandezcairo.neo4j.dto.EventFindSubgraphDTO;
import cu.avangenio.technicaltest.cfernandezcairo.neo4j.dto.EventRateOpDTO;
import cu.avangenio.technicaltest.cfernandezcairo.neo4j.exception.JsonConversionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EventMapper {

    private final ObjectMapper objectMapper;
    private final ErrorProperties errorProperties;

    public EventFindSubgraphDTO convertEventFindSubgraphDTOFromJson(final String event) {
        try {
            return objectMapper.readValue(event, EventFindSubgraphDTO.class);
        } catch (JsonProcessingException e) {
            ErrorConfig errorConfig = errorProperties.getConversionJson();
            throw new JsonConversionException(errorConfig.getCode(), errorConfig.getMessage(), e);
        }
    }

    public EventRateOpDTO convertEventRateOpDTOFromJson(final String event) {
        try {
            return objectMapper.readValue(event, EventRateOpDTO.class);
        } catch (JsonProcessingException e) {
            ErrorConfig errorConfig = errorProperties.getConversionJson();
            throw new JsonConversionException(errorConfig.getCode(), errorConfig.getMessage(), e);
        }
    }

    public String convertEventFindSubgraphDTOToJson(final EventFindSubgraphDTO event) {
        try {
            return objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            ErrorConfig errorConfig = errorProperties.getConversionJson();
            throw new JsonConversionException(errorConfig.getCode(), errorConfig.getMessage(), e);
        }
    }

    public String convertEventRateOpDTOToJson(final EventRateOpDTO event) {
        try {
            return objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            ErrorConfig errorConfig = errorProperties.getConversionJson();
            throw new JsonConversionException(errorConfig.getCode(), errorConfig.getMessage(), e);
        }
    }
}

