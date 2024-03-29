package view;

import java.io.IOException;
import java.util.Scanner;

import controller.InventoryManager;
import controller.exceptions.Exceptions.invalidValuesException;
import controller.exceptions.Exceptions.outOfStockException;
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
public class App extends Application{

    public static void main(String[] args) throws IOException, outOfStockException, invalidValuesException {


            if (args.length == 1 && args[0].equals("4")) {

                launch(args);
            } else {
                System.out.println("run your code as in Assignment 3");
            }
        }
    
        @Override
        /**
         * This method is called when the application should stop, and provides a
         * convenient place to prepare for application exit and destroy resources.
         */
        public void stop() {
            System.out.println("this is where the save action should be initiated.");
    
        }
    
        @Override
        public void start(Stage primaryStage) throws Exception {
            // Load root layout from fxml file.
            TabPane root = (TabPane) FXMLLoader.load(getClass().getResource("app.fxml"));
    
            // Show the scene containing the root layout.
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    
            primaryStage.setTitle("COMP 1502 Toy Store");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        final String DATALOADER = "res/toysData.txt";
        final String strWorkingFolder = System.getProperty("user.dir");
        final String FILE_PATH = strWorkingFolder + DATALOADER;

        Scanner keyboard = new Scanner(System.in);
        InventoryManager inventory = new InventoryManager();
        int option = 0;

    }



       /*  while (option != 4) {
            System.out.println("[1] Search Inventory and purchase toy");
            System.out.println("[2] Add New Product");
            System.out.println("[3] Remove Product");
            System.out.println("[4] Save & Exit \n");
            System.out.print("Enter option: ");
            option = keyboard.nextInt();
            keyboard.nextLine();
            System.out.println("");

            switch (option) {
                case 1:
                    System.out.print("Enter a search term: ");
                    String keyword = keyboard.nextLine();

                    ArrayList<Product> results = inventory.searchInventory(keyword);
                    int i = 1;
                    for (Product p : results) {
                        System.out.print("[" + i + "]" + " " + p);
                        i++;
                    }

                    if (results.size() == 0) {
                        System.out.println("No products found \n");
                        break;
                    }

                    System.out.println("[" + i + "] " + "Back to main menu \n");
                    System.out.print("Enter the number of the product you want to select: ");

                    int selection = keyboard.nextInt();
                    keyboard.nextLine(); // Consume the newline character

                    if (selection == i) {
                        break;
                        // Go back to main menu
                    }

                    if (selection < 1 || selection > results.size()) {
                        System.out.println("Invalid selection. Please choose a number between 1 and " + results.size());
                        // validate selection
                    } else {
                        Product selectedProduct = results.get(selection - 1);
                        inventory.purchaseProduct(selectedProduct.get_sku());
                        System.out.println("");

                        // inventory.writeProductsToFile(DATAFOLDER, inventoryList);
                        break;
                    }

                case 2:
                    System.out.print("Enter SKU: ");
                    String sku = keyboard.nextLine();

                    boolean productExists = false;
                    for (Product p : inventoryList) {
                        if (p.get_sku().equals(sku)) {
                            System.out.println("Product with SKU " + sku + " already exists");
                            productExists = true;
                            System.exit(0);
                        }
                    }
                    if ((!productExists) && (sku.startsWith("0") || sku.startsWith("1"))) {

                        String[] productAttributes = { sku, "name", "price", "count", "classification" };

                        System.out.print("Enter figure name: ");
                        String name = keyboard.nextLine();
                        productAttributes[1] = name;

                        System.out.print("Enter price: ");
                        double price = keyboard.nextDouble();
                        productAttributes[2] = Double.toString(price);
                        keyboard.nextLine(); // Consume the newline character

                        System.out.print("Enter availiable count: ");
                        int count = keyboard.nextInt();
                        productAttributes[3] = Integer.toString(count);

                        System.out.print("Enter figure classification: ");
                        String classification = keyboard.nextLine();
                        productAttributes[4] = classification;

                        inventory.addNewProduct(productAttributes);
                        System.out.println("");
                        System.out.println("Product added");
                        System.out.println("");

                        // inventory.writeProductsToFile(DATAFOLDER, inventoryList);
                    } else if ((!productExists)
                            && (sku.startsWith("4") || sku.startsWith("5") || sku.startsWith("6"))) {
                        String[] productAttributes = { sku, "name", "price", "count", "numPieces" };

                        System.out.print("Enter Puzzle name: ");
                        String name = keyboard.nextLine();
                        productAttributes[1] = name;

                        System.out.print("Enter price: ");
                        double price = keyboard.nextDouble();
                        productAttributes[2] = Double.toString(price);
                        keyboard.nextLine(); // Consume the newline character

                        System.out.print("Enter availiable count: ");
                        String count = keyboard.nextLine();
                        productAttributes[3] = count;

                        System.out.print("Enter number of pieces: ");
                        String classification = keyboard.nextLine();
                        productAttributes[4] = classification;

                        inventory.addNewProduct(productAttributes);
                        System.out.println("");
                        System.out.println("Product added");
                        System.out.println("");

                        // inventory.writeProductsToFile(DATAFOLDER, inventoryList);

                    } else if ((!productExists)
                            && (sku.startsWith("7") || sku.startsWith("8") || sku.startsWith("9"))) {
                        String[] productAttributes = { sku, "name", "price", "count", "minAge" };

                        System.out.print("Enter Board Game name: ");
                        String name = keyboard.nextLine();
                        productAttributes[1] = name;

                        System.out.print("Enter price: ");
                        double price = keyboard.nextDouble();
                        productAttributes[2] = Double.toString(price);
                        keyboard.nextLine(); // Consume the newline character

                        System.out.print("Enter availiable count: ");
                        String count = keyboard.nextLine();
                        productAttributes[3] = count;

                        System.out.print("Enter the minimum age: ");
                        String classification = keyboard.nextLine();
                        productAttributes[4] = classification;

                        inventory.addNewProduct(productAttributes);
                        System.out.println("");
                        System.out.println("Product added");
                        System.out.println("");

                        // inventory.writeProductsToFile(DATAFOLDER, inventoryList);
                    } else if ((!productExists) && (sku.startsWith("2") || sku.startsWith("3"))) {
                        String[] productAttributes = { sku, "name", "price", "team" };

                        System.out.print("Enter Video game name: ");
                        String name = keyboard.nextLine();
                        productAttributes[1] = name;

                        System.out.print("Enter price: ");
                        double price = keyboard.nextDouble();
                        productAttributes[2] = Double.toString(price);
                        keyboard.nextLine(); // Consume the newline character

                        System.out.print("Enter team: ");
                        String team = keyboard.nextLine();
                        productAttributes[3] = team;

                        inventory.addNewProduct(productAttributes);
                        System.out.println("");
                        System.out.println("Product added");
                        System.out.println("");

                        // inventory.writeProductsToFile(DATAFOLDER, inventoryList);
                        break;
                    }

                case 3:
                    System.out.print("Enter SKU: ");
                    String skuToRemove = keyboard.nextLine();

                    inventory.removeProduct(skuToRemove, inventoryList);

                    break;

                case 4:
                    inventory.writeProductsToFile(DATAFOLDER, inventoryList);
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
            }
                }
            }
        

 */