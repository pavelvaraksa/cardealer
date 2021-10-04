package by.varaksa.cardealer.exception;

/**
 * Exception for methods in the repository layer
 *
 * @author Pavel Varaksa
 *
 */
public class RepositoryException extends Exception {
    public RepositoryException() {
    }

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryException(Throwable cause) {
        super(cause);
    }
}
