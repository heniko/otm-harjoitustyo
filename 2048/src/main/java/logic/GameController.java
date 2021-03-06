package logic;

import domain.Direction;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameController {

    private Grid grid;
    private boolean gameRunning;

    /**
     *
     * @param board Peliruudukko jossa tieto pelin tilasta säilytetään
     */
    public GameController(int[][] board) {
        this.grid = new Grid(4, board);
        gameRunning = true;
    }

    /**
     * Alustaa pelin
     */
    public void initializeGame() {
        grid.initializeGrid();
        grid.setScoreZero();
        //Two tiles are added in the beginning
        grid.addNewTile();
        grid.addNewTile();
        gameRunning = true;
    }

    /**
     * Siirto näppäinpainalluksen perusteella
     *
     * @param event Painettu näppäin
     * @return
     */
    public boolean move(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            gameRunning = false;
        } else if (event.getCode() == KeyCode.RIGHT) {
            gameRunning = move(Direction.RIGHT);
        } else if (event.getCode() == KeyCode.LEFT) {
            gameRunning = move(Direction.LEFT);
        } else if (event.getCode() == KeyCode.UP) {
            gameRunning = move(Direction.UP);
        } else if (event.getCode() == KeyCode.DOWN) {
            gameRunning = move(Direction.DOWN);
        }
        return false;
    }

    /**
     *
     * @return
     */
    public boolean gameIsRunning() {
        return gameRunning;
    }

    /**
     *
     * @param dir Suunta, johon laattoja liikutetaan
     * @return Palauttaa false jos peli on loppunut
     */
    public boolean move(Direction dir) {
        boolean moveHappened = grid.moveTiles(dir);
        if (moveHappened) {
            grid.addNewTile();
        }
        return !grid.gameEnded(); //Game ended returns true if game ended so game running is the opposite
    }

    public int[][] getGrid() {
        return grid.getGrid();
    }

    /**
     * Metodi pistemäärän hakemiseen Gridistä
     *
     * @return Palauttaa pistemäärän
     */
    public int getScore() {
        return grid.getScore();
    }
}
