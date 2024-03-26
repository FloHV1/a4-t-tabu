package model;

// Puzzle Subclass
public class Puzzle extends PhysicalProducts {
    private int _numPiece;

    public Puzzle(String sku, String name, String price, int avaliableCount, int numPiece) {
        super(sku, name, price, avaliableCount);
        _numPiece = numPiece;
    }

    public Puzzle() {
        super("", "", "", 0);
        _numPiece = 0;
    }

    public int get_numPiece() {
        return _numPiece;
    }

    public void set_numPiece(int _numPiece) {
        this._numPiece = _numPiece;
    }

    @Override
    public String toString() {
        return get_sku() + ": " + get_name() + ", available count: " + get_avaliableCount() + ", # of pieces: "
                + get_numPiece() + ", $" + get_price() + "\n";
    }
}