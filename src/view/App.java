package view;

import java.io.IOException;

import controller.InventoryManager;
import controller.exceptions.invalidValuesException;
import controller.exceptions.outOfStockException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 * The main class that runs the application
 * This class contains the main method and handles the user interaction with the
 * inventory management system.
 */
public class App extends Application {

    public static void main(String[] args) throws IOException, outOfStockException, invalidValuesException {

        final String DATALOADER = "res/toysData.txt";
        final String strWorkingFolder = System.getProperty("user.dir");
        final String strAbsPath = strWorkingFolder + "/" + DATALOADER;


			InventoryManager inventoryManager = new InventoryManager();
            inventoryManager.readProductsFromFile(DATALOADER);
			launch(args);
	
	}


            

            @Override
            public void start(Stage primaryStage) throws Exception {
        // Load root layout from fxml file.
        TabPane root = (TabPane) FXMLLoader.load(getClass().getResource("App.fxml"));

        // Show the scene containing the root layout.
        Scene scene = new Scene(root);

        primaryStage.setTitle("COMP 1502 Toy Store");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
