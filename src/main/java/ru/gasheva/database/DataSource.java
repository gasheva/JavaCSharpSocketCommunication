package ru.gasheva.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {
    private static HikariDataSource ds;
    private static HikariConfig config = new HikariConfig();
    Properties properties = new Properties();
    FileInputStream fis;

    public DataSource(){
        try{
            fis = new FileInputStream("src/main/resources/JdbcConfig.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setUsername(properties.getProperty("db.login"));
        config.setPassword(properties.getProperty("db.password"));
        ds = new HikariDataSource(config);

    }
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
