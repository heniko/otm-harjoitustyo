package domain;

import java.sql.Date;

public class Highscore {

    private int score;
    private String name;
    private Date date;

    /**
     * Huipputuloksen alustus
     *
     * @param score Pistemäärä
     * @param name Nimi
     * @param date Päivämäärä
     */
    public Highscore(int score, String name, Date date) {
        this.score = score;
        this.name = name;
        this.date = date;
    }

    /**
     * Huipputuloksen alustus ilman nimeä
     *
     * @param score Pistemäärä
     * @param date Päivämäärä
     */
    public Highscore(int score, Date date) {
        this.score = score;
        this.date = date;
    }

    /**
     *
     * @return Palauttaa päivämäärän
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @return Palauttaa nimen
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return Palauttaa pistemäärän
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @return päivämäärä, nimi: pistemäärä
     */
    @Override
    public String toString() {
        return date.toString() + ", " + name + ": " + score;
    }

    /**
     *
     * @param name Asettaa tulokselle nimen
     */
    public void setName(String name) {
        this.name = name;
    }
}
