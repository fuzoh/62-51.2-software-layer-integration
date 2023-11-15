package ch.hearc.ig.guideresto.persistence.database;

import oracle.jdbc.internal.OraclePreparedStatement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Optional;

public class Query implements AutoCloseable {

    private final DatabaseConnection conn;

    private OraclePreparedStatement stmt;

    private int paramIndex;

    public Query(DatabaseConnection conn, String query) throws SQLException {
        this.conn = conn;
        paramIndex = 1;
        this.query(query);
    }

    public Query query(String query) throws SQLException {
        stmt = (OraclePreparedStatement)conn.dbConn().prepareStatement(query);
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

    public Query registerReturnParameter(int type) throws SQLException {
        Objects.requireNonNull(stmt);
        try {
            stmt.registerReturnParameter(paramIndex, type);
            paramIndex++;
        } catch (SQLException e) {
            throw new SQLException("Return parameter cannot be bind", e);
        }
        return this;
    }

    public ResultSet execute() throws SQLException {
        Objects.requireNonNull(stmt);
        return stmt.executeQuery();
    }

    public int executeUpdate() throws SQLException {
        Objects.requireNonNull(stmt);
        return stmt.executeUpdate();
    }

    public ResultSet getReturnResultSet() throws SQLException {
        Objects.requireNonNull(stmt);
        return stmt.getReturnResultSet();
    }

    public Optional<Integer> getReturnedInt() throws SQLException {
        var rs = this.getReturnResultSet();
        if (rs.next()) {
            return Optional.of(rs.getInt(1));
        }
        return Optional.empty();
    }

    @Override
    public void close() throws Exception {
        stmt.close();
    }
}
