package cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.exception;

import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(JsonConversionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseDTO handleJsonConversionException(JsonConversionException ex) {
        return new ErrorResponseDTO(ex.getErrorCode(), ex.getMessage());
    }

}
