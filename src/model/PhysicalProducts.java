package model;

public abstract class PhysicalProducts extends Product {

    // The number of items in stock
    protected int _avaliableCount;

    // Physical Product Superconstructor
    public PhysicalProducts(String sku, String name, String price, int avaliableCount) {
        super(sku, name, price);
        _avaliableCount = avaliableCount;
    }

    public int get_avaliableCount() {
        return _avaliableCount;
    }

    public void set_avaliableCount(int _avaliableCount) {
        this._avaliableCount = _avaliableCount;
    }
}