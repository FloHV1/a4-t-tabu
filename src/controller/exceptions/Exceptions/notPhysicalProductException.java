package controller.exceptions.Exceptions;

/**
 * This exception is thrown when a product is not a physical product.
 */
public class notPhysicalProductException extends Throwable {

    /**
     * Constructs a new notPhysicalProductException with the specified detail message.
     * @param message the detail message
     */
    public notPhysicalProductException(String message) {
        System.out.println("Product is not a physical product.");
    }
}
