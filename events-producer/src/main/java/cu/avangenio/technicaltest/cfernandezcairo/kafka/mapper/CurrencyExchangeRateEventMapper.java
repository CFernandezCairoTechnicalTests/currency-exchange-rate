package cu.avangenio.technicaltest.cfernandezcairo.kafka.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import cu.avangenio.technicaltest.cfernandezcairo.kafka.config.ErrorProperties;
import cu.avangenio.technicaltest.cfernandezcairo.kafka.dto.ErrorConfig;

import cu.avangenio.technicaltest.cfernandezcairo.kafka.dto.EventRateOpDTO;
import cu.avangenio.technicaltest.cfernandezcairo.kafka.exception.JsonConversionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CurrencyExchangeRateEventMapper {

    private final ObjectMapper objectMapper;
    private final ErrorProperties errorProperties;

    public String convertToJson(final EventRateOpDTO eventRateOpDTO) {
        try {
            return objectMapper.writeValueAsString(eventRateOpDTO);
        } catch (JsonProcessingException e) {
            ErrorConfig errorConfig = errorProperties.getConversionJson();
            throw new JsonConversionException(errorConfig.getCode(), errorConfig.getMessage(), e);
        }
    }
}

