package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import controller.exceptions.InvalidValuesException;
import controller.exceptions.NotPhysicalProductException;
import controller.exceptions.OutOfStockException;
import model.BoardGame;
import model.DigitalProducts;
import model.Figure;
import model.PhysicalProducts;
import model.Product;
import model.Puzzle;
import model.VideoGame;


/**
 * This class implements the Interface interface and manages the inventory of
 * products.
 * It provides methods for searching the inventory, purchasing products, adding
 * stock, adding new products,
 * removing products, and reading/writing products from/to a file.
 */
public class InventoryManager implements Interface{

    private static final ArrayList<Product> _productList = new ArrayList<>();

    /**
     * This method will search the current inventory for a product that contains the
     * keyword
     * 
     * @param keyword
     */
    public ArrayList<Product> searchInventory(String keyword) {
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
        }
        System.out.println("Search results: ");
        for (Product product : result) {
            System.out.println(product);
        }
        return result;
    }

    /**
     * This method will take a sku and purchase a product from the inventory.
     *
     * If a product is a physical product, it will decrease the stock by 1 and
     * return a "true" value.
     *
     * if a product is a digital product, it will return a "true" value.
     *
     * @param sku
     * @throws OutOfStockException
     * @throws InvalidValuesException
     */
    public boolean purchaseProduct(String sku) throws OutOfStockException {
        for (Product product : _productList) {
            if (product.get_sku() == sku) {
                if (product instanceof PhysicalProducts) {
                    if (((PhysicalProducts) product).get_avaliableCount() > 0) {
                        ((PhysicalProducts) product)
                                .set_avaliableCount(((PhysicalProducts) product).get_avaliableCount() - 1);
                        System.out.println("Product purchased");
                        return true;

                    }
                } else if (product instanceof DigitalProducts) {
                    System.out.println("Product purchased");
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method will add stock to an already existing PHYSICAL product
     *
     * @param sku
     * @param count
     *
     * @throws NotPhysicalProductException
     */
    public void addStock(String sku, int count) throws NotPhysicalProductException {
        for (Product product : _productList) {
            Long.parseLong(sku);
            if (product.get_sku() == sku) {
                if (product instanceof PhysicalProducts) {
                    ((PhysicalProducts) product)
                            .set_avaliableCount(((PhysicalProducts) product).get_avaliableCount() + count);
                    System.out.println(((PhysicalProducts) product).get_avaliableCount() + "items currently in stock.");
                } else {
                    throw new NotPhysicalProductException("This product is not a physical product");
                }
            }
        }
    }

    /**
     * This method will add a new product to the inventory using the products
     * characterstics
     *
     * @param product
     * 
     * @throws InvalidValuesException
     */
    public void addNewProduct(String[] Attributes) throws InvalidValuesException {

        if (Attributes[0].startsWith("0") || Attributes[0].startsWith("1")) {
            Figure figure = new Figure();
            figure.set_sku(Attributes[0]);
            figure.set_name(Attributes[1]);
            figure.set_price(Attributes[2]);
            figure.set_avaliableCount(Integer.parseInt(Attributes[3]));
            figure.set_classification(Attributes[4]);
            _productList.add(figure);
        }

        if (Attributes[0].startsWith("4") || Attributes[0].startsWith("5") || Attributes[0].startsWith("6")) {
            Puzzle puzzle = new Puzzle();
            puzzle.set_sku(Attributes[0]);
            puzzle.set_name(Attributes[1]);
            puzzle.set_price(Attributes[2]);
            puzzle.set_avaliableCount(Integer.parseInt(Attributes[3]));
            puzzle.set_numPiece(Integer.parseInt(Attributes[4]));
            _productList.add(puzzle);
        }

        if (Attributes[0].startsWith("7") || Attributes[0].startsWith("8") || Attributes[0].startsWith("9")) {
            BoardGame boardGame = new BoardGame();
            boardGame.set_sku(Attributes[0]);
            boardGame.set_name(Attributes[1]);
            boardGame.set_price(Attributes[2]);
            boardGame.set_avaliableCount(Integer.parseInt(Attributes[3]));
            boardGame.set_minAge(Integer.parseInt(Attributes[4]));
            _productList.add(boardGame);
        }

        if (Attributes[0].startsWith("2") || Attributes[0].startsWith("3")) {
            VideoGame VideoGame = new VideoGame();
            VideoGame.set_sku(Attributes[0]);
            VideoGame.set_name(Attributes[1]);
            VideoGame.set_price(Attributes[2]);
            VideoGame.set_team(Attributes[3]);
            _productList.add(VideoGame);
        }

    }

    /**
     * This method will remove a product from the inventory
     *
     * @param sku
     */
    public void removeProduct(String sku, ArrayList<Product> allProducts) {
        allProducts.removeIf(product -> product.get_sku().equals(sku));
        System.out.println("The product with the sku " + sku + " has been removed from the inventory");

    }

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

    public ArrayList<Product> get_productList() {
        return _productList;
    }

    // public void set_productList(ArrayList<Product> _productList) {
    //     InventoryManager._productList  = _productList;
    // }
}