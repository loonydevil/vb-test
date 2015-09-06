package nef.util;

/**
 * Contains some application-wide utility methods
 */
public class Utils {
    
    /**
     * Simple method similar to guava's {@code Preconditions}
     * @param message to show
     */
    public static void checkIfNull(Object o, String message) {
        if (o == null) {
            throw new IllegalArgumentException(message);
        }
    }

}
