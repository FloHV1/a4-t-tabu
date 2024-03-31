package controller;

import java.util.ArrayList;

import controller.exceptions.invalidValuesException;
import controller.exceptions.notPhysicalProductException;
import controller.exceptions.outOfStockException;
import model.Product;


public interface Interface {

    public ArrayList<Product> searchInventory(String keyword);

    public boolean purchaseProduct(String sku) throws outOfStockException;

    public void addStock(String sku, int count) throws notPhysicalProductException;

    public void addNewProduct(String[] attributes) throws invalidValuesException;;

    public void removeProduct(String sku, ArrayList<Product> inventoryList);

}
