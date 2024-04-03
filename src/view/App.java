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


    public static void main(String[] args) throws Exception{


    
    final String DATAFOLDER = "res/toysData.txt";
    final String strWorkingFolder = System.getProperty("user.dir");


    // instantiate the inventory manager and controller
    InventoryManager inventory = new InventoryManager();
    InventoryController controller = new InventoryController();

        ArrayList<Product> inventoryList = inventory.readProductsFromFile(DATAFOLDER);
        launch(args);
    }


    @Override
    public void stop() throws IOException {
        final String DATAFOLDER = "res/toysData.txt";

        InventoryManager inventory = new InventoryManager();
        inventory.writeProductsToFile(DATAFOLDER, inventory.get_productList());
        // save the inventory to the file before closing the window
    }
    public void start(Stage primaryStage) throws Exception {
        // Load root layout from fxml file.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));
        TabPane root = loader.load();

        // Show the scene containing the root layout.
        Scene scene = new Scene(root);

        primaryStage.setTitle("COMP 1502 Toy Store");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
}
