package cu.avangenio.technicaltest.cfernandezcairo.neo4j.exception;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class JsonConversionException extends RuntimeException {

    private final String errorCode;

    public JsonConversionException(@NonNull String errorCode, @NonNull String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public JsonConversionException(@NonNull String errorCode, @NonNull String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}


