package logic;

import domain.Direction;
import domain.Position;
import java.util.ArrayList;
import static java.util.Collections.shuffle;
import java.util.List;
import java.util.Random;

public class Grid {

    private final Random random;
    private int score;
    private int grid[][];
    private boolean mergeable[][];
    private final int size;

    /**
     *
     * @param size Pelikentän koko
     * @param board Pelikenttä
     */
    public Grid(int size, int[][] board) {
        this.random = new Random();
        this.grid = board;
        this.mergeable = new boolean[size][size];
        this.size = size;
        this.score = 0;
        initializeMergeable();
    }

    /**
     * Alustaa jokaisen pelikentän ruudun luvuksi 0
     */
    public void initializeGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = 0;
            }
        }
    }

    /**
     * Alustaa mergeable ruudukon
     */
    public void initializeMergeable() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                mergeable[i][j] = true;
            }
        }
    }

    //Rotating grid clockwise
    //Rotating is done so only one method for moving tiles is needed
    /**
     * Kääntää pelikenttää myötäpäivään
     */
    public void rotateGrid() {
        int oldGrid[][] = copyGrid();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                grid[y][x] = oldGrid[size - 1 - x][y];
            }
        }
    }

    /**
     * Kääntää pelikenttää myötäpäivään n verran
     *
     * @param n Kääntöjen määrä
     */
    public void rotateNTimes(int n) {
        for (int i = 0; i < n; i++) {
            rotateGrid();
        }
    }

    /**
     * Luo kopion pelikentästä
     *
     * @return kopio pelikentästä
     */
    public int[][] copyGrid() {
        int copy[][] = new int[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                copy[y][x] = grid[y][x];
            }
        }
        return copy;
    }

    /**
     * Siirtää laattoja oikealle 2048 sääntöjen mukaisesti
     *
     * @return
     */
    public boolean moveTilesLeft() {
        int beforeMoves[][] = copyGrid();

        for (int y = 0; y < size; y++) {
            for (int x = size - 2; x >= 0; x--) {
                if (grid[y][x] == 0) {
                    continue; //No need for moving and merging 0's
                }
                int xPos = x; //For moving individual tile left
                while (xPos < size - 1) {
                    if (grid[y][xPos + 1] == 0) { //Moving left if tile on the left is 0
                        grid[y][xPos + 1] = grid[y][xPos];
                        grid[y][xPos] = 0;
                    } else { //Trying to merge if tile on the left is not 0
                        mergeIfPossible(new Position(xPos, y));
                        break; //No moves can be made for this tile after merging
                    }
                    xPos++;
                }
            }
        }

        return gridChanged(beforeMoves);
    }

    /**
     * Yhdistää kaksi laattaa, mikäli mahdollista
     *
     * @param pos Yhdistettävän laatan sijainti
     */
    public void mergeIfPossible(Position pos) {
        if (grid[pos.getY()][pos.getX()] == grid[pos.getY()][pos.getX() + 1] && mergeable[pos.getY()][pos.getX() + 1] == true) {
            grid[pos.getY()][pos.getX() + 1] = grid[pos.getY()][pos.getX() + 1] * 2;
            grid[pos.getY()][pos.getX()] = 0;
            mergeable[pos.getY()][pos.getX() + 1] = false;
            score += grid[pos.getY()][pos.getX() + 1];
        }
    }

    /**
     * Vertaa pelikentän tilannetta ennen siirtoyritystä ja sen jälkeen
     *
     * @param beforeMoves Kopio pelikentästä ennen siirtoja
     * @return True jos pelikenttä muuttui siirtoyrityksellä
     */
    public boolean gridChanged(int[][] beforeMoves) {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                //True if move happened
                if (grid[y][x] != beforeMoves[y][x]) {
                    return true;
                }
            }
        }
        //False if grids are the same
        return false;
    }

    /**
     * Lista tyhjistä paikoista kentällä
     *
     * @return Listä tyhjistä paikoista
     */
    public List<Position> emptyTiles() {
        List<Position> emptyTiles = new ArrayList<>();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (grid[y][x] == 0) {
                    emptyTiles.add(new Position(x, y));
                }
            }
        }
        return emptyTiles;
    }

    /**
     * Palauttaa pistemäärän
     *
     * @return Pistemäärä
     */
    public int getScore() {
        return score;
    }

    /**
     * Lisää uuden laatan tyhjään kohtaan
     */
    public void addNewTile() {
        List<Position> emptyTiles = emptyTiles();
        shuffle(emptyTiles);
        int newTile = random.nextDouble() < 0.9 ? 2 : 4;
        grid[emptyTiles.get(0).getY()][emptyTiles.get(0).getX()] = newTile;
    }

    /**
     * Kääntää pelikenttää niin, että laatat voidaan siirtää oikealle, siirtää
     * laatat oikealle ja kääntää pelikentän takaisin alkuperäiseen asentoon
     *
     * @param direction Suunta mihin laattoja halutaan siitää
     * @return Palauttaa tiedon siitä, tapahtuiko muutoksia siirrolla
     */
    public boolean moveTiles(Direction direction) {
        //Rotating grid for moving tiles
        if (direction == Direction.DOWN) {
            rotateNTimes(3);
        }
        if (direction == Direction.UP) {
            rotateNTimes(1);
        }
        if (direction == Direction.LEFT) {
            rotateNTimes(2);
        }

        initializeMergeable();
        boolean changesHappened = moveTilesLeft();

        //Rotating grid back to original position
        if (direction == Direction.DOWN) {
            rotateNTimes(1);
        }
        if (direction == Direction.UP) {
            rotateNTimes(3);
        }
        if (direction == Direction.LEFT) {
            rotateNTimes(2);
        }
        return changesHappened;
    }

    /**
     * Palauttaa pelikentän
     *
     * @return Pelikenttä
     */
    public int[][] getGrid() {
        return grid;
    }

    /**
     * Testejä varten luotu ruudukon pelikentän asettaminen tiettyyn tilaan
     *
     * @param grid Haluttu pelikenttä
     */
    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    /**
     * Palauttaa tiedon siitä, onko siirto mihinkään suuntaan mahdollinen
     *
     * @return True jos peli voi jatkua
     */
    public boolean gameEnded() {
        Grid g = new Grid(4, copyGrid());
        for (Direction d : Direction.values()) {
            if (g.moveTiles(d) == true) {
                return false;
            }
        }
        return true;
    }

    /**
     * Asettaa pisteet takaisin nollaan
     */
    public void setScoreZero() {
        this.score = 0;
    }

}
