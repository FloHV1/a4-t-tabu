package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.exceptions.InvalidValuesException;
import controller.exceptions.NotPhysicalProductException;
import controller.exceptions.OutOfStockException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Product;

public class InventoryController implements Initializable {

    // private ArrayList<Product> _productList;

    // public void set_inventoryList(ArrayList<Product> inventoryList) {
    // this._productList = inventoryList;
    // }

    // public ArrayList<Product> get_inventoryList() {
    // return this._productList;
    // }

    private InventoryManager inventoryManager = new InventoryManager();

    /*
     *                                                       Home tab
     */

    // Buttons
    @FXML
    private Button btnSearch;

    @FXML
    private Button btnAddStock;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnBuy;

    // radio buttons
    @FXML
    private RadioButton radioSearch;

    @FXML
    private RadioButton radioAddStock;

    // ListView
    @FXML
    private ListView<Product> listViewResults;

    // text fields
    @FXML
    private TextField txtFieldKeywordSearch;

    @FXML
    private TextField txtFieldAddStockSKU;

    @FXML
    private TextField txtFieldQuantity;

    // Labels
    @FXML
    private Label lblWelcome;

    @FXML
    private Label lblSearchInventory;

    @FXML
    private Label lblKeywordSearch;

    @FXML
    private Label lblAddStock;

    @FXML
    private Label lblAddStockSKU;

    @FXML
    private Label lblAddStockQuantity;


    /*
     *                                                 Remove Toy tab
     */

    // Buttons
    @FXML
    private Button btnRemoveProduct;

    // Text Fields
    @FXML
    private TextField txtFieldRemoveProductSKU;

    // Labels
    @FXML
    private Label lblToySKURemove;

    // ListView
    @FXML
    private ListView<Product> listViewRemoveResults;

    @FXML
    private Label lblAvailCount;



    /*
     *                                                  Add new toy tab
     *
     */

    @FXML
    private TextField textFieldAddToySKU;

    @FXML
    private TextField textFieldAddToyName;

    @FXML
    private TextField textFieldAddToyPrice;

    @FXML
    private TextField textFieldAddToyAvailCount;

    @FXML
    private TextField textFieldAddFigureClass;

    @FXML
    private TextField textFieldAddPuzzlePieces;

    @FXML
    private TextField textFieldBoardGameMinAge;

    @FXML
    private TextField textFieldVideoGameTeam;

    // Buttons

    @FXML
    private Button btnSaveInv;

    // ChoiceBox

    @FXML
    private ChoiceBox<String> addToyChoiceBox;
    private final ObservableList<String> TOY_CATEGORY = FXCollections.observableArrayList("Video Games", "Board Games",
            "Figures", "Puzzles");


    // Labels

    @FXML
    private Label lblEnterToyInfo;

    @FXML
    private Label lblNewToyCategory;

    @FXML
    private Label lblAddNewToySKU;

    @FXML
    private Label lblAddNewToyName;

    @FXML
    private Label lblAddNewToyPrice;

    @FXML
    private Label lblAddNewToyAvailCount;

    @FXML
    private Label lblAddNewFigureClass;

    @FXML

    private Label lblAddNewPuzzlePieces;

    @FXML
    private Label lblAddNewBoardGameMinAge;

    @FXML
    private Label lblAddNewVideoGameTeam;



    // General Methods for enabling and disabling components of GUI

    @FXML
    /**
     *
     * @param status
     */
    private void setHomeSearchSetDisabled(boolean status) {
        lblKeywordSearch.setDisable(status);
        txtFieldKeywordSearch.setDisable(status);
        btnSearch.setDisable(status);
        // if we are disabling the text field, then let's reset the text in the text
        // field too
        if (status) {
            txtFieldKeywordSearch.setText("");
        }
    }

    @FXML
    /**
     * 
     * @param status
     */
    private void setHomeAddStockSetDisabled(boolean status) {
        lblAddStockSKU.setDisable(status);
        txtFieldAddStockSKU.setDisable(status);
        lblAddStockQuantity.setDisable(status);
        btnAddStock.setDisable(status);
        txtFieldQuantity.setDisable(status);

        lblAddStockSKU.setVisible(!status);
        txtFieldAddStockSKU.setVisible(!status);
        lblAddStockQuantity.setVisible(!status);
        btnAddStock.setVisible(!status);
        txtFieldQuantity.setVisible(!status);
        // if we are disabling the text fields, then let's reset the text in the text
        // field too
        if (status) {
            txtFieldAddStockSKU.setText("");
            txtFieldQuantity.setText("");
        }
    }

    @FXML
    /**
     * THis function will set the visibility of the availCount label and text field if the selected choicebox item is video game
     * @param selectedCategory
     */
    private void availCountVisibility(String selectedCategory) {
        boolean isVideoGameSelected = "Video Games".equals(selectedCategory);
        
        // Assuming lblAvailCount and textFieldAddToyAvailCount should be hidden for "Video Games"
        if (lblAvailCount != null && textFieldAddToyAvailCount != null) {
            lblAvailCount.setVisible(!isVideoGameSelected);
            textFieldAddToyAvailCount.setVisible(!isVideoGameSelected);
        }
    
        // Here, add any additional UI components that need to be shown/hidden or enabled/disabled based on the selection
    }
    

    /**
     * What is the state of this screen when it loads, what is active, and what is
     * disabled
     */
    public void initialize(URL location, ResourceBundle resources) {
        addToyChoiceBox.setItems(TOY_CATEGORY);
        addToyChoiceBox.getSelectionModel().selectFirst();

        // Set the visibility of the availCount label and text field based on the selected category
        // Oldvalue is what was selected before, newValue is what is selected now, observable is the value being observed
        addToyChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            availCountVisibility(newValue); 
        });

        // Disable everything related to add stock
        setHomeAddStockSetDisabled(true);
    
        // Enable everything related to search
        setHomeSearchSetDisabled(false);
    
    }


    @FXML
    /**
     * This function will be called by a radio button being selected. It will
     * disable
     * certain elements on the screen, while enabling others
     * 
     * @param event - what is the event that is calling this function
     */
    public void handleRadioButtonSelection(ActionEvent event) {
        System.out.println("handleRadioButtonSelection method called");

        if (event.getSource() instanceof RadioButton) {
            String radioButtonSelected = ((RadioButton) event.getSource()).getText();

            if ("Search".equalsIgnoreCase(radioButtonSelected)) {
                System.out.println("Search to be performed...");

                // disable everything related to add stock
                setHomeAddStockSetDisabled(true);

                // enable everything related to add stock
                // 2 - button search should be active
                setHomeSearchSetDisabled(false);

            } else if ("Add stock".equalsIgnoreCase(radioButtonSelected)) {
                System.out.println("Add stock to be performed...");

                // enable everything related to add stock
                setHomeSearchSetDisabled(true);
                // enable everything connected with add stock
                setHomeAddStockSetDisabled(false);
            }
        }
    }

    /*
     * Home tab Event Handlers
     */

    @FXML
    /**
     * On button press, search for a product in the inventory by keyword.
     * 
     * @param event The ActionEvent triggering the method.
     */
    public void handleSearchAction(ActionEvent event) {
        System.out.println("Search by keyword...");
        // get the text in the 'keyword' text field
        System.out.println(txtFieldKeywordSearch.getText());
        String keyword = txtFieldKeywordSearch.getText();

        // InventoryManager inventoryManager = new InventoryManager();
        ArrayList<Product> foundItems = inventoryManager.searchInventory(keyword);

        // this is how you display that array list of toys to the user
        ObservableList<Product> items = FXCollections.observableArrayList(foundItems);
        listViewResults.setItems(items);
    }

    @FXML
    /**
     * On button press, Handle the action of buying a product.
     * 
     * @param event The ActionEvent triggering the method.
     */
    public void handleBuyButtonAction(ActionEvent event) throws OutOfStockException {
        
        Product selectedProduct = listViewResults.getSelectionModel().getSelectedItem();
        inventoryManager.purchaseProduct(selectedProduct.get_sku());
    }

    @FXML
    /**
     * This function will clear the list view and text fields on the specified tab
     *
     * @param event
     */
    public void handleClearButtonAction(ActionEvent event) {
        // clear the listView
        listViewResults.setItems(null);

        // reset the text in the text fields
        txtFieldKeywordSearch.setText("");
        txtFieldAddStockSKU.setText("");
        txtFieldQuantity.setText("");
    }

    @FXML
    /**
     * This function will call the InventoryManager to add stock to a Physical
     * product
     * 
     * @param event
     */
    public void handleAddStockAction(ActionEvent event) throws NumberFormatException, NotPhysicalProductException {
        System.out.println("Add stock...");
        // get the text in the 'SKU' text field
        System.out.println(txtFieldAddStockSKU.getText());

        // get the text in the 'Quantity' text field
        System.out.println(txtFieldQuantity.getText());

        // InventoryManager inventoryManager = new InventoryManager();
        inventoryManager.addStock(txtFieldAddStockSKU.getText(), Integer.parseInt(txtFieldQuantity.getText()));

        // this is how you display that array list of toys to the user
        ObservableList<Product> items = FXCollections.observableArrayList();
        listViewResults.setItems(items);
    }



    /*
     * Remove Toy tab Event Handlers
     */

    @FXML
    /**
     * This function will call the InventoryManager to remove a product from the
     * inventory
     * 
     * @param event
     */
    public void handleRemoveAction(ActionEvent event) {
        System.out.println("Remove toy...");

        // Get the selected product from listViewRemoveResults
        Product selectedProduct = listViewRemoveResults.getSelectionModel().getSelectedItem();
    
        // Check if a product is selected
        if (selectedProduct != null) {
            // Remove the selected product from the inventory
            inventoryManager.removeProduct(selectedProduct.get_sku(), inventoryManager.get_productList());
        } else {
            // Handle the case where no product is selected
            System.out.println("Please select a product to remove.");
        }
    }
    
    @FXML
    /**
     * Handle the action of searching for a product.
     * 
     * @param event The ActionEvent triggering the method.
     */
    public void handleSearchRemoveAction(ActionEvent event) {
        System.out.println("Search by keyword...");
        // get the text in the 'keyword' text field
        System.out.println(txtFieldRemoveProductSKU.getText());
        String keyword = txtFieldRemoveProductSKU.getText();

        // InventoryManager inventoryManager = new InventoryManager();
        ArrayList<Product> foundItems = inventoryManager.searchInventory(keyword);

        // this is how you display that array list of toys to the user
        ObservableList<Product> items = FXCollections.observableArrayList(foundItems);
        listViewRemoveResults.setItems(items);
    }


    /*
     * Add new toy tab Event Handlers
     */


    @FXML
    /**
     * This function will call the InventoryManager to add a new product to the
     * inventory
     * 
     * @param event
     */
    public void handleAddToyAction(ActionEvent event) throws InvalidValuesException {
        System.out.println("Save inventory...");
        
        // Get the input values from the text fields
        String sku = textFieldAddToySKU.getText();
        String name = textFieldAddToyName.getText();
        String price = textFieldAddToyPrice.getText();

        String availCount = textFieldAddToyAvailCount.getText();
    
        String category = addToyChoiceBox.getValue(); // Get the selected category from the dropdown menu
    
        String[] attributes = {sku, name, price};

        // Check the category of the product and add the corresponding attributes

        switch (category) {
            case "Video Games":
                String team = textFieldVideoGameTeam.getText();
                attributes = new String[]{sku, name, price, team};
                break;
            case "Board Games":
                String minAge = textFieldBoardGameMinAge.getText();
                attributes = new String[]{sku, name, price, availCount, minAge};
                break;
            case "Figures":
                String figureClass = textFieldAddFigureClass.getText();
                attributes = new String[]{sku, name, price, availCount, figureClass};
                break;
            case "Puzzles":
                String pieces = textFieldAddPuzzlePieces.getText();
                attributes = new String[]{sku, name, price, availCount, pieces};
                break;
            default:
                break;
        }
    
        // Call the InventoryManager to add a new product to the inventory
        inventoryManager.addNewProduct(attributes);
    }
    
}