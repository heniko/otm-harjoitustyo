package domain;

public class Position {

    private int x;
    private int y;

    /**
     * Olio vapaiden paikkojen listaamiselle
     *
     * @param x
     * @param y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return Palauttaa paikan rivillä
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return Palauttaa rivin
     */
    public int getY() {
        return y;
    }
}
