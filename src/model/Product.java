package Products;

public class Product {
    protected String _sku;
    protected String _name;
    protected String _price;

    public Product(String sku, String name, String price) {
        _sku = sku;
        _name = name;
        _price = price;
    }

    public Product() {
        _sku = "";
        _name = "";
        _price = "";
    }

    // Getters and Setters
    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_price() {
        return _price;
    }

    public void set_price(String _price) {
        this._price = _price;
    }

    public String get_sku() {
        return _sku;
    }

    public void set_sku(String _sku) {
        this._sku = _sku;
    }

    @Override
    public String toString() {
        return get_sku() + ": " + get_name() + ", " + get_price() + "" + "\n";
    }

}
