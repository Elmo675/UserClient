package emil.burdach.userclient.exception;

import org.springframework.http.HttpStatus;

public class RandomUserClientServiceException extends RuntimeException {
    public RandomUserClientServiceException(String message) {
        super(message);
    }

    public RandomUserClientServiceException(HttpStatus responseCode, String responseBody) {
        super(String.format("Exception status: %d, body: %s", responseCode.value(), responseBody));
    }
}
