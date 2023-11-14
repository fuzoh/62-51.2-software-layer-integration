package ch.hearc.ig.guideresto.persistence.database;

import ch.hearc.ig.guideresto.persistence.database.exceptions.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection implements AutoCloseable {

    private final Connection dbConn;

    public DatabaseConnection() {
        try {
            dbConn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@db.ig.he-arc.ch:1521:ens",
                    "BASTIEN_NICOUD",
                    "BASTIEN_NICOUD"
            );
            dbConn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("The database connection could not be established.", e);
        }
    }

    public Connection dbConn() {
        return dbConn;
    }

    @Override
    public void close() {
        try {
            dbConn.close();
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Database connection could not be properly closed.", e);
        }
    }
}
