package model;

public abstract class DigitalProducts extends Product {
    protected String _team;

    public DigitalProducts(String sku, String name, String price, String team) {
        super(sku, name, price);
        _team = team;
    }

}
