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

    @Override
    public Connection acquire() throws DataAccessException {
        return DB.getConnection();
    }

    @Override
    public void release(Connection connection) throws DataAccessException {
        try {
            connection.close();
        } catch (SQLException e) {
            Logger.error("Error closing connection " + connection, e);
        }
    }
}
