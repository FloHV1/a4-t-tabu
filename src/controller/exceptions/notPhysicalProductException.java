package controller.exceptions;

/**
 * This exception is thrown when a product is not a physical product.
 */
public class NotPhysicalProductException extends Throwable {

    /**
     * Constructs a new notPhysicalProductException with the specified detail message.
     * @param message the detail message
     */
    public NotPhysicalProductException(String message) {
        System.out.println("Product is not a physical product.");
    }
}
