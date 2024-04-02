package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
import model.BoardGame;
import model.Figure;
import model.PhysicalProducts;
import model.Product;
import model.Puzzle;
import model.VideoGame;

public class InventoryController implements Initializable {

    private ArrayList<Product> _productList;

    

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

    public ArrayList<Product> get_productList() {
        return this._productList;
    }

    public void set_productList(ArrayList<Product> _productList) {
        this._productList = _productList;
    }


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

    @FXML
        /**
     * This method will read the products from a specified filepath and return an
     * arraylist of products
     * 
     * @param filePath
     * @return
     * @throws IOException
     */
    public ArrayList<Product> readProductsFromFile(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] productData = line.split(";");

                if (productData[0].startsWith("0") || productData[0].startsWith("1")) {
                    Figure figure = new Figure();

                    figure.set_sku(productData[0]);
                    figure.set_name(productData[1]);
                    figure.set_price(productData[2]);
                    figure.set_avaliableCount(Integer.parseInt(productData[3]));
                    figure.set_classification(productData[4]);
                    get_productList().add(figure);
                }

                if (productData[0].startsWith("4") || productData[0].startsWith("5")
                        || productData[0].startsWith("6")) {
                    Puzzle puzzle = new Puzzle();

                    puzzle.set_sku(productData[0]);
                    puzzle.set_name(productData[1]);
                    puzzle.set_price(productData[2]);
                    puzzle.set_avaliableCount(Integer.parseInt(productData[3]));
                    puzzle.set_numPiece(Integer.parseInt(productData[4]));
                    this.get_productList().add(puzzle);
                }

                if (productData[0].startsWith("7") || productData[0].startsWith("8")
                        || productData[0].startsWith("9")) {
                    BoardGame boardGame = new BoardGame();

                    boardGame.set_sku(productData[0]);
                    boardGame.set_name(productData[1]);
                    boardGame.set_price(productData[2]);
                    boardGame.set_avaliableCount(Integer.parseInt(productData[3]));
                    boardGame.set_minAge(Integer.parseInt(productData[4]));
                    get_productList().add(boardGame);
                }

                if (productData[0].startsWith("2") || productData[0].startsWith("3")) {
                    VideoGame VideoGame = new VideoGame();

                    VideoGame.set_sku(productData[0]);
                    VideoGame.set_name(productData[1]);
                    VideoGame.set_price(productData[2]);
                    VideoGame.set_team(productData[3]);
                    get_productList().add(VideoGame);
                }
            }
        }
        return get_productList();
    }

    @FXML
    /**
     * this method will take a data file and an arraylist of products and write the
     * products to the file
     * 
     * @param FILE_PATH
     * @param products
     * 
     * @throws IOException
     */
    public void writeProductsToFile(String dataFile, ArrayList<Product> products) throws IOException {
        try (FileWriter writer = new FileWriter(dataFile)) {
            for (Product product : products) {

                if (product instanceof Figure) {
                    writer.write(product.get_sku() + ";" + product.get_name() + ";" + product.get_price() + ";"
                            + ((PhysicalProducts) product).get_avaliableCount() + ";"
                            + ((Figure) product).get_classification() + "\n");
                }
                if (product instanceof Puzzle) {
                    writer.write(product.get_sku() + ";" + product.get_name() + ";" + product.get_price() + ";"
                            + ((PhysicalProducts) product).get_avaliableCount() + ";"
                            + ((Puzzle) product).get_numPiece() + "\n");
                }
                if (product instanceof BoardGame) {
                    writer.write(product.get_sku() + ";" + product.get_name() + ";" + product.get_price() + ";"
                            + ((PhysicalProducts) product).get_avaliableCount() + ";"
                            + ((BoardGame) product).get_minAge() + "\n");
                }
                if (product instanceof VideoGame) {
                    writer.write(product.get_sku() + ";" + product.get_name() + ";" + product.get_price() + ";"
                            + ((VideoGame) product).get_team() + "\n");
                }
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
        String keyword = txtFieldKeywordSearch.getText();

        InventoryManager inventoryManager = new InventoryManager();
        ArrayList<Product> result = new ArrayList<>();

                for (Product product : _productList) {

            if (product.get_name().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(product);
            }

            if (product.get_sku().contains(keyword)) {
                result.add(product);
            }

            if (product instanceof VideoGame) {
                if (((VideoGame) product).get_team().toLowerCase().contains(keyword.toLowerCase())) {
                    result.add(product);
                }
            }

        // this is how you display that array list of toys to the user
        ObservableList<Product> items = FXCollections.observableArrayList(inventoryManager._productList);
        listViewResults.setItems(items);
                }
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
