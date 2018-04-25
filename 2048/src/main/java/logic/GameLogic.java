package logic;

import domain.Direction;
import domain.Grid;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameLogic {

    private Grid grid;
    private boolean gameRunning;

    public GameLogic() {
        this.grid = new Grid(4);
        //Two tiles are added in the beginning
        grid.addNewTile();
        grid.addNewTile();
        gameRunning = true;
    }

    public boolean move(KeyEvent event) throws Exception {
        System.out.println("Key pressed! " + event.getCode());
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
        } else {
            throw new Exception("Pressed key was not valid");
        }
        return false;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    //Return false if game ended
    public boolean move(Direction dir) {
        boolean moveHappened = grid.moveTiles(dir);
        if (moveHappened) {
            grid.addNewTile();
        }
        return grid.gameEnded();
    }

    public int[][] getGrid() {
        return grid.getGrid();
    }
}
