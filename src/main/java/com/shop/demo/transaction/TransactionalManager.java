package com.shop.demo.transaction;

import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class TransactionalManager {

    private final DataSource dataSource;
    private ThreadLocal threadLocal = new ThreadLocal();

    public TransactionalManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void startTransaction() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        threadLocal.set(connection);
    }

    public Connection getConnection() {
        return (Connection) threadLocal.get();
    }

    public void commit() {
        try {
            getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void rollBack() {
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
