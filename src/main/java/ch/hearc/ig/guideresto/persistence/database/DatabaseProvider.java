package ch.hearc.ig.guideresto.persistence.database;

import java.sql.SQLException;

public class DatabaseProvider {

    private static DatabaseConnection conn;

    /**
     * This getter acts as a singleton. Warning, this singleton implementation
     * is not thread safe
     */
    private static DatabaseConnection conn() {
        if (conn == null) {
            conn = new DatabaseConnection();
        }
        return conn;
    }

    public static Query preparedQueryOf(String query) throws SQLException {
        return new Query(conn(), query);
    }

    public static void commit() {
        try {
            conn().dbConn().commit();
        } catch (SQLException e) {
            System.out.println("Failed to commit : " + e.getCause());
            throw new RuntimeException(e);
        }
    }
}
