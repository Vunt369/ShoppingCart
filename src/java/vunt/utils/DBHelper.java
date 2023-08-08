/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vunt.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author NguyenTuanVu
 */
public class DBHelper implements Serializable {
    //Cac methods lien quan den Helper, no mang tinh chat la library de ho tro xu ly ma khong can tao Object, nen la cac methods do can co "static"
    //de tranh chuyen cap phat memory roi thu hoi lenh

    public static Connection makeConnection()
            throws /*ClassNotFoundException*/ SQLException,NamingException {
        
        //1. Find context hien hanh cua minh
        Context context = new InitialContext();
        //2. Find contex of Context
        Context tomcatContext = (Context)context.lookup("java:comp/env");
        //3 Find data source
        DataSource ds = (DataSource) tomcatContext.lookup("DBcuaTuanVu");
        //4. Open conection
        Connection con = ds.getConnection();

//        //1: load driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2: create connection String
//        String url = "jdbc:sqlserver:"
//                + "//localhost:1433"
//                + ";databaseName=PRJ301_SE162119";
//        //3: open connection
//        Connection con = DriverManager.getConnection(url, "sa", "vuongga147");
//        return con;
        return con;
    }
    
}
    
