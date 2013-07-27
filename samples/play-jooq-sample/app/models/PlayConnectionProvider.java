// Copyright 2013 Skiddoo Pty Ltd

package models;

import org.jooq.ConnectionProvider;
import org.jooq.exception.DataAccessException;
import play.Logger;
import play.db.DB;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author jaiew
 */
public class PlayConnectionProvider implements ConnectionProvider {

    private Connection connection = null;

    @Override
    public Connection acquire() throws DataAccessException {
        if (connection == null) {
            connection = DB.getConnection();
        }
        return connection;
    }

    @Override
    public void release(Connection released) throws DataAccessException {
        if (this.connection != released) {
            throw new IllegalArgumentException("Expected " + this.connection + " but got " + released);
        }
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            Logger.error("Error closing connection " + connection, e);
        }
    }
}
