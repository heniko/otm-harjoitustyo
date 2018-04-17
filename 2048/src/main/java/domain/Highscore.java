package domain;

import java.sql.Date;

public class Highscore {

    private int score;
    private String name;
    private Date date;

    public Highscore(int score, String name, Date date) {
        this.score = score;
        this.name = name;
        this.date = date;
    }

    public Highscore(int score, Date date) {
        this.score = score;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return date.toString() + ", " + name + ": " + score;
    }

    public void setName(String name) {
        this.name = name;
    }
}
