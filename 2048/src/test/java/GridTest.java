
import domain.Direction;
import domain.Grid;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GridTest {

    private Grid g;

    public GridTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    private String intArrayToString(int[][] array) {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < array.length; y++) {
            for (int x = 0; x < array[y].length; x++) {
                sb.append(array[y][x]);
                sb.append(',');
            }
        }
        return sb.toString();
    }

    @Test
    public void moveLeft1() {
        g = new Grid(4);
        int grid[][] = {
            {0, 0, 2, 0},
            {0, 0, 2, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}};
        int result[][] = {
            {0, 0, 0, 2},
            {0, 0, 0, 2},
            {0, 0, 0, 0},
            {0, 0, 0, 0}};
        g.setGrid(grid);
        g.moveTilesLeft();
        assertEquals(intArrayToString(result), intArrayToString(g.getGrid()));
    }

    @Test
    public void moveLeft2() {
        g = new Grid(4);
        int grid[][] = {
            {0, 2, 2, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}};
        int result[][] = {
            {0, 0, 0, 4},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}};
        g.setGrid(grid);
        g.moveTilesLeft();
        assertEquals(intArrayToString(result), intArrayToString(g.getGrid()));
    }

    @Test
    public void moveLeft3() {
        g = new Grid(4);
        int grid[][] = {
            {2, 2, 2, 2},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}};
        int result[][] = {
            {0, 0, 4, 4},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}};
        g.setGrid(grid);
        g.moveTilesLeft();
        assertEquals(intArrayToString(result), intArrayToString(g.getGrid()));
    }

    @Test
    public void moveLeft4() {
        g = new Grid(4);
        int grid[][] = {
            {4, 2, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}};
        int result[][] = {
            {0, 0, 4, 2},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}};
        g.setGrid(grid);
        g.moveTilesLeft();
        assertEquals(intArrayToString(result), intArrayToString(g.getGrid()));
    }

    @Test
    public void moveRight() {
        g = new Grid(4);
        int grid[][] = {
            {0, 0, 4, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}};
        int result[][] = {
            {4, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}};
        g.setGrid(grid);
        g.moveTiles(Direction.RIGHT);
        assertEquals(intArrayToString(result), intArrayToString(g.getGrid()));
    }

    @Test
    public void moveUp() {
        g = new Grid(4);
        int grid[][] = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 8, 0, 0},
            {0, 0, 0, 0}};
        int result[][] = {
            {0, 8, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}};
        g.setGrid(grid);
        g.moveTiles(Direction.UP);
        assertEquals(intArrayToString(result), intArrayToString(g.getGrid()));
    }

    @Test
    public void moveDown() {
        g = new Grid(4);
        int grid[][] = {
            {4, 2, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}};
        int result[][] = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {4, 2, 0, 0}};
        g.setGrid(grid);
        g.moveTiles(Direction.DOWN);
        assertEquals(intArrayToString(result), intArrayToString(g.getGrid()));
    }

    @Test
    public void moveLeft() {
        g = new Grid(4);
        int grid[][] = {
            {4, 2, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}};
        int result[][] = {
            {0, 0, 4, 2},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}};
        g.setGrid(grid);
        g.moveTiles(Direction.LEFT);
        assertEquals(intArrayToString(result), intArrayToString(g.getGrid()));
    }

    @Test
    public void scoreTest1() {
        g = new Grid(4);
        int grid[][] = {
            {2, 2, 2, 2},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}};
        g.setGrid(grid);
        g.moveTiles(Direction.LEFT);
        assertEquals(8, g.getScore());
    }

    @Test
    public void scoreTest2() {
        g = new Grid(4);
        int grid[][] = {
            {4, 2, 4, 2},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}};
        g.setGrid(grid);
        g.moveTiles(Direction.LEFT);
        assertEquals(0, g.getScore());
    }

    @Test
    public void gameEndedTest1() {
        g = new Grid(4);
        int grid[][] = {
            {4, 2, 4, 2},
            {2, 4, 2, 4},
            {4, 2, 4, 2},
            {2, 4, 2, 4}};
        g.setGrid(grid);
        g.moveTiles(Direction.LEFT);
        assertEquals(true, g.gameEnded());
    }

    @Test
    public void gameEndedTest2() {
        g = new Grid(4);
        int grid[][] = {
            {4, 2, 4, 2},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}};
        g.setGrid(grid);
        g.moveTiles(Direction.LEFT);
        assertEquals(false, g.gameEnded());
    }
}
