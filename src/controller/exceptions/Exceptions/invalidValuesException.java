package controller.exceptions.Exceptions;


/**
 * This exception is thrown when invalid values are provided for a product.
 */
public class invalidValuesException extends Exception {

    /**
     * Constructs a new invalidValuesException with the specified detail message.
     *
     * @param message the detail message
     */
    public invalidValuesException(String message) {
        System.out.println("Invalid values for product.");
    }
}
