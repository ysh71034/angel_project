package com.angel.comm;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbconn=new DBConnection();
    public  static   DBConnection getDbConn(){
        return dbconn;
    }
    private DBConnection(){

    }
    public Connection getConnection() throws SQLException, NamingException
    {
        Context init=new InitialContext();
        DataSource ds=(DataSource) init.lookup("java:comp/env/jdbc/angel");

        Connection conn=ds.getConnection();
        return conn;
    }

    public void disconn(Connection conn){
        if(conn!=null) try{conn.close();} catch(Exception e){}
    }


}
