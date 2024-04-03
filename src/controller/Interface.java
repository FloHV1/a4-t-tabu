package controller;

import java.util.ArrayList;

import controller.exceptions.InvalidValuesException;
import controller.exceptions.NotPhysicalProductException;
import controller.exceptions.OutOfStockException;
import model.Product;


public interface Interface {

    public ArrayList<Product> searchInventory(String keyword);

    public boolean purchaseProduct(String sku) throws OutOfStockException;

    public void addStock(String sku, int count) throws NotPhysicalProductException;

    public void addNewProduct(String[] attributes) throws InvalidValuesException;;

    public void removeProduct(String sku, ArrayList<Product> inventoryList);

}
