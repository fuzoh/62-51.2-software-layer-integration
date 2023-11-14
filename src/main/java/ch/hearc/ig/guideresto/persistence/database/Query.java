package ch.hearc.ig.guideresto.persistence.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Query implements AutoCloseable {

    private final DatabaseConnection conn;

    private PreparedStatement stmt;

    private int paramIndex;

    public Query(DatabaseConnection conn, String query) throws SQLException {
        this.conn = conn;
        paramIndex = 1;
        this.query(query);
    }

    public Query query(String query) throws SQLException {
        stmt = conn.dbConn().prepareStatement(query);
        return this;
    }

    public Query bind(int intParam) throws SQLException {
        Objects.requireNonNull(stmt);
        try {
            stmt.setInt(paramIndex, intParam);
            paramIndex++;
        } catch (SQLException e) {
            throw new SQLException("Parameter cannot be bind", e);
        }
        return this;
    }

    public Query bind(String stringParam) throws SQLException {
        Objects.requireNonNull(stmt);
        try {
            stmt.setString(paramIndex, stringParam);
            paramIndex++;
        } catch (SQLException e) {
            throw new SQLException("Parameter cannot be bind", e);
        }
        return this;
    }

    public ResultSet execute() throws SQLException {
        return stmt.executeQuery();
    }

    @Override
    public void close() throws Exception {
        stmt.close();
    }
}
