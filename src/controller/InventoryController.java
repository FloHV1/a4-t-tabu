package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import model.BoardGame;
import model.DigitalProducts;
import model.Figure;
import model.PhysicalProducts;
import model.Product;
import model.Puzzle;
import model.VideoGame;

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

    @FXML
    public void handleSearchAction(ActionEvent event) {
        String keyword = txtFieldKeywordSearch.getText().toLowerCase();
        System.out.println("Search by keyword: " + keyword);

        ArrayList<Product> result = new ArrayList<>();
        for (Product product : _productList) {
            if (product.get_name().toLowerCase().contains(keyword) ||
                    product.get_sku().toLowerCase().contains(keyword) ||
                    (product instanceof VideoGame
                            && ((VideoGame) product).get_team().toLowerCase().contains(keyword))) {
                result.add(product);
            }
        }

        // Display the search results
        ObservableList<Product> items = FXCollections.observableArrayList(result);
        listViewResults.setItems(items);
        listViewResults.refresh(); // Refresh ListView to reflect changes
    }

    @FXML
    /**
     * Handle the action of adding stock to a product.
     * 
     * @param event The ActionEvent triggering the method.
     */
    public void handleAddStockAction(ActionEvent event) {
        String sku = txtFieldAddStockSKU.getText();
        int quantity = Integer.parseInt(txtFieldQuantity.getText());
        System.out.println("Add stock to product with SKU: " + sku);

        for (Product product : _productList) {
            if (product.get_sku().equals(sku)) {
                if (product instanceof PhysicalProducts) {
                    int currentStock = ((PhysicalProducts) product).get_avaliableCount();
                    ((PhysicalProducts) product).set_avaliableCount(currentStock + quantity);
                    System.out.println("Stock added to product: " + sku);

                    // Update the ListView to reflect the changes
                    ObservableList<Product> items = FXCollections.observableArrayList(_productList);
                    listViewResults.setItems(items);
                    return; // Exit the method once stock is added
                } else {
                    System.out.println("Cannot add stock to a non-physical product.");
                    return; // Exit the method if the product is not physical
                }
            }
        }

        System.out.println("Product with SKU: " + sku + " not found.");
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

        for (Product product : _productList) {
            if (product.get_sku().equals(sku)) {
                if (product instanceof PhysicalProducts) {
                    if (((PhysicalProducts) product).get_avaliableCount() > 0) {
                        ((PhysicalProducts) product)
                                .set_avaliableCount(((PhysicalProducts) product).get_avaliableCount() - 1);
                        System.out.println("Product purchased: " + sku);
                        break; // Exit the loop once product is purchased
                    } else {
                        System.out.println("Product with SKU: " + sku + " is out of stock.");
                        return; // Exit the method if the product is out of stock
                    }
                } else if (product instanceof DigitalProducts) {
                    System.out.println("Product purchased: " + sku);
                    break; // Exit the loop once product is purchased
                }
            }
        }

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

    @FXML
    /**
     * Handle the action of adding a new product based on input from text fields.
     * 
     * @param event The ActionEvent triggering the method.
     */
    public void handleAddNewProduct(ActionEvent event) {
        // Assuming Attributes is an array obtained from text fields
        String[] attributes = new String[] {
                txtFieldAddToySKU.getText(),
                txtFieldAddToyName.getText(),
                txtFieldAddToyPrice.getText(),
                txtFieldAddToyAvailCount.getText(),
                txtFieldAddFigureClass.getText() // Assuming this field is for figure class
        };

        if (attributes[0].startsWith("0") || attributes[0].startsWith("1")) {
            Figure figure = new Figure();
            figure.set_sku(attributes[0]);
            figure.set_name(attributes[1]);
            figure.set_price(attributes[2]);
            figure.set_avaliableCount(Integer.parseInt(attributes[3]));
            figure.set_classification(attributes[4]);
            _productList.add(figure);
        } else if (attributes[0].startsWith("4") || attributes[0].startsWith("5") || attributes[0].startsWith("6")) {
            Puzzle puzzle = new Puzzle();
            puzzle.set_sku(attributes[0]);
            puzzle.set_name(attributes[1]);
            puzzle.set_price(attributes[2]);
            puzzle.set_avaliableCount(Integer.parseInt(attributes[3]));
            puzzle.set_numPiece(Integer.parseInt(attributes[4]));
            _productList.add(puzzle);
        } else if (attributes[0].startsWith("7") || attributes[0].startsWith("8") || attributes[0].startsWith("9")) {
            BoardGame boardGame = new BoardGame();
            boardGame.set_sku(attributes[0]);
            boardGame.set_name(attributes[1]);
            boardGame.set_price(attributes[2]);
            boardGame.set_avaliableCount(Integer.parseInt(attributes[3]));
            boardGame.set_minAge(Integer.parseInt(attributes[4]));
            _productList.add(boardGame);
        } else if (attributes[0].startsWith("2") || attributes[0].startsWith("3")) {
            VideoGame videoGame = new VideoGame();
            videoGame.set_sku(attributes[0]);
            videoGame.set_name(attributes[1]);
            videoGame.set_price(attributes[2]);
            videoGame.set_team(attributes[3]);
            _productList.add(videoGame);
        }
    }

}
