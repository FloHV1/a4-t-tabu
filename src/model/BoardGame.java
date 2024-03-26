package model;

// Boardgame Subclass
public class BoardGame extends PhysicalProducts {
    private int _minAge;

    public BoardGame(String sku, String name, String price, int avaliableCount, int minAge) {
        super(sku, name, price, avaliableCount);
        _minAge = minAge;
    }

    public BoardGame() {
        super("", "", "", 0);
        _minAge = 0;
    }

    public int get_minAge() {
        return _minAge;
    }

    public void set_minAge(int _minAge) {
        this._minAge = _minAge;
    }

    @Override
    public String toString() {
        return get_sku() + ": " + get_name() + ", available count: " + get_avaliableCount() + ", minimum age:  "
                + get_minAge() + ", $" + get_price() + "\n";
    }
}
