package controller.exceptions;


/**
 * This exception is thrown when invalid values are provided for a product.
 */
public class InvalidValuesException extends Exception {

    /**
     * Constructs a new invalidValuesException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidValuesException(String message) {
        System.out.println("Invalid values for product.");
    }
}
