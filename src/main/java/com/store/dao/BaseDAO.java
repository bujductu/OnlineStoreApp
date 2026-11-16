package com.store.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDAO {
    protected Connection connection;

    public BaseDAO() {

        String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
        String dbUser = "postgres";
        String dbPassword = "123456";

        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}