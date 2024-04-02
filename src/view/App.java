package view;

import java.io.IOException;
import java.util.ArrayList;

import controller.InventoryController;
import controller.InventoryManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import model.Product;

public class App extends Application {

    InventoryManager inventory = new InventoryManager();
    InventoryController controller = new InventoryController();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load root layout from fxml file.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));
        TabPane root = loader.load();

        // Set up the inventory manager and load products from the file
        final String DATAFOLDER = "res/toysData.txt";
        final String strWorkingFolder = System.getProperty("user.dir");
        final String FILE_PATH = strWorkingFolder + DATAFOLDER;

        InventoryManager inventory = new InventoryManager();
        ArrayList<Product> inventoryList;

        try {
            inventoryList = inventory.readProductsFromFile(DATAFOLDER);
        } catch (IOException e) {
            System.out.println("Error loading products from file: " + e.getMessage());
            inventoryList = new ArrayList<Product>();
        }


        // Show the scene containing the root layout.
        Scene scene = new Scene(root);

        primaryStage.setTitle("COMP 1502 Toy Store");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
