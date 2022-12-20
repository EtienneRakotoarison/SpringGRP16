package itu.s5.cloud.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB
{
    String url;
    String username;
    String password;
    Connection connection;
    public ConnectDB(String url,String username, String password) throws SQLException
    {
        this.url=url;
        this.password=password;
        this.username=username;
        initConnection(this.url,this.username, this.password);
    }
    public void initConnection(String url, String username, String password) throws SQLException
    {
        connection=DriverManager.getConnection(url,username,password);
        connection.setAutoCommit(true);
    }
    public Connection getConnection()
    {
        return connection;
    }
    public void closeConnection() throws SQLException
    {
        connection.close();
    }
}