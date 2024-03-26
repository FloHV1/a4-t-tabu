package Products;

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

    // Boardgame Subclass
    public static class BoardGame extends PhysicalProducts {
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
            return get_sku() + ": " + get_name() +", available count: " + get_avaliableCount() +  ", minimum age:  "
                    + get_minAge()+ ", $" + get_price() +"\n";
        }
    }

    // Figure Subclass
    public static class Figure extends PhysicalProducts {
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
            return get_sku() + ": " + get_name() +", available count: " + get_avaliableCount() +  ", classification: "
                    + get_classification() + ", $" + get_price() +"\n";
        }
    }

    // Puzzle Subclass
    public static class Puzzle extends PhysicalProducts {
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
            return get_sku() + ": " + get_name() +", available count: " + get_avaliableCount() +  ", # of pieces: "
                    + get_numPiece() + ", $" + get_price() +"\n";
        }
    }

}
