package logic;

import domain.Direction;
import domain.Grid;

public class GameLogic {
    Grid grid;

    public GameLogic() {
        this.grid = new Grid(4);
        //Two tiles are added in the beginning
        grid.addNewTile();
        grid.addNewTile();
        
    }
    
    //Return true if moves are possible, false if not
    public boolean move(Direction dir) {
        boolean moveHappened = grid.moveTiles(dir);
        if(moveHappened) {
            grid.addNewTile();
        }
        return grid.gameEnded();
    }
}
