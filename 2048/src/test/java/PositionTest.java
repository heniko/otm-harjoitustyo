
import domain.Position;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PositionTest {

    private Position p;

    public PositionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        p = new Position(3, 1);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void posX() {
        assertEquals(p.getX(), 3);
    }

    @Test
    public void posY() {
        assertEquals(p.getY(), 1);
    }
}
