package model;

public abstract class DigitalProducts extends Product {
    protected String _team;

    public DigitalProducts(String sku, String name, String price, String team) {
        super(sku, name, price);
        _team = team;
    }

    public static class VideoGame extends DigitalProducts {

        public VideoGame(String sku, String name, String price, String team) {
            super(sku, name, price, team);
        }

        public VideoGame() {
            super("", "", "", "");
        }

        public String get_team() {
            return _team;
        }

        public void set_team(String _team) {
            this._team = _team;
        }

        @Override
        public String toString() {
            return get_sku() + ": " + get_name() + ", " + get_team() + ", $" + get_price() + "\n";
        }

    }
}
