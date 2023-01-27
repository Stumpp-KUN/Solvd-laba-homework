package solvd.repairservice.utils;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class PoolConnection {
    private static BasicDataSource dataSource=null;

    static {
        Properties property = new Properties();

        try {
            InputStream fis = PoolConnection.class.getClassLoader().getResourceAsStream("mySQLcreds.properties");

            property.load(fis);
    } catch (IOException e) {
            e.printStackTrace();
        }
        dataSource=new BasicDataSource();
        dataSource.setUrl(property.getProperty("db.url"));
        dataSource.setUsername(property.getProperty("db.user"));
        dataSource.setPassword(property.getProperty("db.password"));

        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxTotal(25);

}
public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
}
}
