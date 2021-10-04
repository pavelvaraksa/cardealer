package by.varaksa.cardealer.exception;

/**
 * Exception for methods in the service layer
 *
 * @author Pavel Varaksa
 *
 */
public class ServiceException extends Exception {
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
