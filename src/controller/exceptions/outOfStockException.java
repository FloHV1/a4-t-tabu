package controller.exceptions;

/**
 * This exception is thrown when a product is out of stock.
 */
public class outOfStockException extends Throwable {

    /**
     * Constructs a new outOfStockException with the specified detail message.
     *
     * @param message the detail message
     */
    public outOfStockException(String message) {
        System.out.println("Product is out of stock.");
    }

}
