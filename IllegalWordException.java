/**
 * IllegalWordException is an exception class that extends IllegalArgumentException.
 * It is thrown when a word contains non-lowercase letters.
 */
public class IllegalWordException extends IllegalArgumentException{
    /**
     * Constructs a new IllegalWordException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public IllegalWordException(String message) {
        super(message);
    }
}
