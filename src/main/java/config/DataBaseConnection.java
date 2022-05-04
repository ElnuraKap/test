package config;

import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBaseConnection {
    private Connection connection;

    public DataBaseConnection()  throws SQLException {

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "postgres");

    }

    public Connection getConnection() {
        return connection;
    }
}
