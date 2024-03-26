package model;

// Figure Subclass
public class Figure extends PhysicalProducts {
    private String _classification;

    public Figure(String sku, String name, String price, int avaliableCount, String classification) {
        super(sku, name, price, avaliableCount);
        _classification = classification;
    }

    public Figure() {
        super("", "", "", 0);
        _classification = "";
    }

    public String get_classification() {
        return _classification;
    }

    public void set_classification(String _classification) {
        this._classification = _classification;
    }

    @Override
    public String toString() {
        return get_sku() + ": " + get_name() + ", available count: " + get_avaliableCount() + ", classification: "
                + get_classification() + ", $" + get_price() + "\n";
    }
}
