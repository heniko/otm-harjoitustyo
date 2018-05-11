package domain;

import java.sql.*;

public class Database {

    private String databaseAddress;

    /**
     *
     * @param databaseAddress Tietokannan sijainti
     * @throws ClassNotFoundException
     */
    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    /**
     *
     * @return Palauttaa yhteyden tietokantaan
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }
}
