package dao;

import domain.Database;
import domain.Highscore;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HighscoreDao {

    private Database db;

    public HighscoreDao(Database db) {
        this.db = db;
    }
    
    public void createDatabaseAndTableIfNotExists() throws SQLException {
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS highscores(id integer PRIMARY KEY, score integer, name varchar(30), date date)";
        try (Connection conn = db.getConnection()) {
            PreparedStatement s = conn.prepareStatement(sqlCreateTable);
            s.execute();
            s.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Tuloksien lisäys
    public void addNew(Highscore score) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement s = conn.prepareStatement("INSERT INTO highscores (score, name, date) VALUES (?, ?, ?)");
        s.setInt(1, score.getScore());
        s.setString(2, score.getName());
        s.setDate(3, score.getDate());

        s.executeUpdate();
        s.close();
        conn.close();
    }

    public List<Highscore> getTop20() throws SQLException {
        List<Highscore> scores = new ArrayList<>();

        Connection conn = db.getConnection();
        PreparedStatement s = conn.prepareStatement("SELECT score, name, date FROM highscores ORDER BY -score LIMIT 20");
        ResultSet rs = s.executeQuery();

        while (rs.next()) {
            Highscore h = new Highscore(rs.getInt("score"), rs.getString("name"), rs.getDate("date"));
            scores.add(h);
        }

        s.close();
        rs.close();
        conn.close();

        return scores;
    }
}
