package solvd.repairservice.utils;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlPooledConnection;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnection {
    private Connection connection;
    InputStream fis = null;

    {
        ConnectionPoolDataSource connectionPoolDataSource=new MysqlConnectionPoolDataSource();
        try {
            fis = getClass().getClassLoader().getResourceAsStream("mySQLcreds.properties");
            Properties property = new Properties();
            property.load(fis);


            connection = DriverManager.getConnection(property.getProperty("db.url"),property.getProperty("db.user"), property.getProperty("db.password"));
        } catch (IOException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }
}
