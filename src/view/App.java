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
        

                    launch(args);
    }
                

            @Override
            /**
             * This method is called when the application should stop, and provides a
             * convenient place to prepare for application exit and destroy resources.
             */
            public void stop() {
                System.out.println("this is where the save action should be initiated.");
                InventoryManager inventoryManager = new InventoryManager();

                


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
