package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.InventoryManager;
import controller.exceptions.InvalidValuesException;
import model.Product;





public class InventoryManagerTest {

    private InventoryManager inventory;

    @BeforeEach
    void setUp() throws InvalidValuesException {
        inventory = new InventoryManager();

        // Add some products to the inventory to set up the test environment
        inventory.addNewProduct(new String[] { "234", "DMC5", "49.99", "5", "CAPCOM" });
    }

    // tests for searching inventory
    @Test
    void testSearchInventoryByName() {
        ArrayList<Product> results = inventory.searchInventory("dmc");
        assertEquals(1, results.size(), "Expected to find exactly one product with name 'VIRGIL FIGURE'");
    }

    @Test
    void testSearchInventoryBySKU() {
        ArrayList<Product> results = inventory.searchInventory("234");
        assertEquals(1, results.size(), "Expected to find exactly one product with SKU '002'");
    }

    @Test
    void testSearchInventoryNoMatch() {
        ArrayList<Product> results = inventory.searchInventory("LALALAALALLALLA");
        assertTrue(results.isEmpty(), "Expected no products to be found with keyword 'LALALAALALLALLA'");
    }

    // tests for adding new product
    @Test
    void testAddNewProductFigure() throws InvalidValuesException {
        String[] attributes = { "001", "Figure 1", "19.99", "10", "Classification 1" };
        inventory.addNewProduct(attributes);
        ArrayList<Product> results = inventory.searchInventory("Figure 1");
        assertEquals(1, results.size(), "Expected to find exactly one product with name 'Figure 1'");
    }

    @Test
    void testAddNewProductPuzzle() throws InvalidValuesException {
        String[] attributes = { "401", "Puzzle 1", "29.99", "5", "100" };
        inventory.addNewProduct(attributes);
        ArrayList<Product> results = inventory.searchInventory("Puzzle 1");
        assertEquals(1, results.size(), "Expected to find exactly one product with name 'Puzzle 1'");
    }

    @Test
    void testAddNewProductBoardGame() throws InvalidValuesException {
        String[] attributes = { "701", "Board Game 1", "39.99", "3", "8" };
        inventory.addNewProduct(attributes);
        ArrayList<Product> results = inventory.searchInventory("Board Game 1");
        assertEquals(1, results.size(), "Expected to find exactly one product with name 'Board Game 1'");
    }

    @Test
    void testAddNewProductVideoGame() throws InvalidValuesException {
        String[] attributes = { "201", "Video Game 1", "49.99", "Team 1" };
        inventory.addNewProduct(attributes);
        ArrayList<Product> results = inventory.searchInventory("Video Game 1");
        assertEquals(1, results.size(), "Expected to find exactly one product with name 'Video Game 1'");
    }

    @Test
    void testAddNewProductInvalidValuesException() {
        String[] attributes = { "001", "Figure 1", "19.99", "10", "Classification 1" };
        assertThrows(InvalidValuesException.class, () -> inventory.addNewProduct(attributes),
                "Expected an invalidValuesException to be thrown when adding a product with a duplicate SKU");
    }
}