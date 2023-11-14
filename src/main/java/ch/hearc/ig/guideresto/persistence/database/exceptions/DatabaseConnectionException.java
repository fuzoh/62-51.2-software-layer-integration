package ch.hearc.ig.guideresto.persistence.database.exceptions;

public class DatabaseConnectionException extends RuntimeException {
    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
