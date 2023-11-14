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

    public static void commit() throws SQLException {
        conn().dbConn().commit();
    }
}
