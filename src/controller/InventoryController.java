package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.exceptions.notPhysicalProductException;
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

    protected ArrayList<Product> _productList = new ArrayList<>();

    /*
     * Home tab
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
     * Remove Toy tab
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

    /*
     * Add new toy tab
     *
     */

    // Buttons

    @FXML
    private Button btnSaveInv;

    // ChoiceBox

    @FXML
    private ChoiceBox<String> addToyChoiceBox;
    private final ObservableList<String> TOY_CATEGORY = FXCollections.observableArrayList("Video Games", "Board Games",
            "Figures", "Puzzles");

    // Text Fields

    @FXML
    private TextField txtFieldAddToySKU;

    @FXML
    private TextField txtFieldAddToyName;

    @FXML
    private TextField txtFieldAddToyPrice;

    @FXML
    private TextField txtFieldAddToyAvailCount;

    @FXML
    private TextField txtFieldAddFigureClass;

    @FXML
    private TextField txtFieldAddPuzzlePieces;

    @FXML

    private TextField txtFieldAddBoardGameMinAge;

    @FXML
    private TextField txtFieldAddVideoGameTeam;

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

    // Methods/Functions

    InventoryManager inventoryManager = new InventoryManager();

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


     public void handleSearchAction(ActionEvent event) {
        System.out.println("Search by keyword...");
        // get the text in the 'keyword' text field
        System.out.println(txtFieldKeywordSearch.getText());

        InventoryManager inventoryManager = new InventoryManager();
        inventoryManager.searchInventory(txtFieldKeywordSearch.getText());


        // this is how you display that array list of toys to the user
        ObservableList<Product> items = FXCollections.observableArrayList(inventoryManager._productList);
        listViewResults.setItems(items);
    }
    



    @FXML
    /**
     * Handle the action of buying a product.
     * 
     * @param event The ActionEvent triggering the method.
     */
    public void handleBuyButtonAction(ActionEvent event) {
        String sku = txtFieldAddStockSKU.getText();
        System.out.println("Buy product with SKU: " + sku);

    
    

        System.out.println("Product with SKU: " + sku + " not found.");
    }

    @FXML
    /**
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
     * 
     * @param event
     */
    public void handleAddStockAction(ActionEvent event) throws NumberFormatException, notPhysicalProductException {
        System.out.println("Add stock...");
        // get the text in the 'SKU' text field
        System.out.println(txtFieldAddStockSKU.getText());
        // get the text in the 'Quantity' text field
        System.out.println(txtFieldQuantity.getText());

        InventoryManager inventoryManager = new InventoryManager();
        inventoryManager.addStock(txtFieldAddStockSKU.getText(), Integer.parseInt(txtFieldQuantity.getText()));

        // this is how you display that array list of toys to the user
        ObservableList<Product> items = FXCollections.observableArrayList(inventoryManager._productList);
        listViewResults.setItems(items);
    }

    @Override
    /**
     * What is the state of this screen when it loads, what is active, and what is
     * disabled
     */
    public void initialize(URL location, ResourceBundle resources) {
        addToyChoiceBox.setItems(TOY_CATEGORY);
        addToyChoiceBox.getSelectionModel().selectFirst();
        // disable everything related to add stock
        setHomeAddStockSetDisabled(true);

        // enable everything related to add stock
        setHomeSearchSetDisabled(false);
    }

    /*
     * Remove Toy tab Event Handlers
     */

    @FXML
    /**
     * 
     * @param event
     */
    public void handleRemoveAction(ActionEvent event) {
        System.out.println("Remove toy...");
        // get the text in the 'SKU' text field
        System.out.println(txtFieldRemoveProductSKU.getText());

        InventoryManager inventoryManager = new InventoryManager();
        inventoryManager.removeProduct(txtFieldRemoveProductSKU.getText(), inventoryManager._productList);

        // this is how you display that array list of toys to the user
        ObservableList<Product> items = FXCollections.observableArrayList(inventoryManager._productList);
        listViewResults.setItems(items);
    }

    /*
     * Add new toy tab Event Handlers
     */

 
}
