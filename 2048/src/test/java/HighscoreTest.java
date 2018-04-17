
import domain.Highscore;
import java.sql.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HighscoreTest {

    private Highscore h;
    private Date d;

    public HighscoreTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        d = new Date(System.currentTimeMillis());
        h = new Highscore(200, "Pelaaja", d);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void highscoreLuonti1() {
        assertEquals(h.toString(), d.toString() + ", Pelaaja: 200");
    }

    @Test
    public void highscoreLuonti2() {
        Highscore h2 = new Highscore(200, d);
        h2.setName("Pelaaja");
        assertEquals(h2.toString(), d.toString() + ", Pelaaja: 200");
    }

    @Test
    public void getNameTest() {
        assertEquals(h.getName(), "Pelaaja");
    }

    @Test
    public void getDateTest() {
        assertEquals(h.getDate().toString(), d.toString());
    }

    @Test
    public void getScoreTest() {
        assertEquals(h.getScore(), 200);
    }

    @Test
    public void setName() {
        h.setName("Toinen");
        assertEquals(h.getName(), "Toinen");
    }
}
