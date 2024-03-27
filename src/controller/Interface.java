package controller;

import model.*;

import java.util.ArrayList;

import controller.exceptions.Exceptions.*;

public interface Interface {

    public ArrayList<Product> searchInventory(String keyword);

    public boolean purchaseProduct(String sku) throws outOfStockException;

    public void addStock(String sku, int count) throws notPhysicalProductException;

    public void addNewProduct(String[] attributes) throws invalidValuesException;;

    public void removeProduct(String sku, ArrayList<Product> inventoryList);

}
