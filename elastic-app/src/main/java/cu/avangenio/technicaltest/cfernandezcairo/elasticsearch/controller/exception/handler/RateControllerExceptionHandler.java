package cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.controller.exception.handler;

import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.service.exception.DuplicateIdException;
import cu.avangenio.technicaltest.cfernandezcairo.elasticsearch.service.exception.RateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class RateControllerExceptionHandler {

    @ExceptionHandler(value = {RateNotFoundException.class, DuplicateIdException.class})
    public ResponseEntity<Body> doHandleRateOpExceptions(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Body(ex.getMessage()));
    }

    public static class Body {

        private String message;

        public Body(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}