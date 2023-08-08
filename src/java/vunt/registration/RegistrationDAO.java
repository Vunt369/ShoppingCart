/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vunt.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import vunt.utils.DBHelper;

/**
 *
 * @author NguyenTuanVu
 */
public class RegistrationDAO implements Serializable {

    public RegistrationDTO checkLogin(String usename, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RegistrationDTO result = null;
        try {
            //1 Connect DB
            con = DBHelper.makeConnection();
            //2 Create SQL String
            if (con != null) {
                String sql = "Select username, lastname, isAdmin "
                        + "FROM Registration "
                        + "WHERE username = ? "
                        + "AND password = ?";

                //3 Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, usename);
                stm.setString(2, password);
                //4 Excute Query
                rs = stm.executeQuery();
                //5 process Result
                if (rs.next()) {
                    String fullName = rs.getString("lastname");
                    boolean isAdmin = rs.getBoolean("isAdmin");

                    result = new RegistrationDTO(usename, null, fullName, isAdmin);
                }//end user is existed!!     
            }//process when it is existed
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    //5. Tao DTO neu can
    private List<RegistrationDTO> accounts;

    //ko co phuong thuc set boi vi data from DB
    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastName(String searchValue)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1 Connect DB
            con = DBHelper.makeConnection();
            //2 Create SQL String
            if (con != null) {
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname like ?";

                //3 Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4 Excute Query
                rs = stm.executeQuery();
                //5 process Result
                while (rs.next()) {
                    //get tung field cua record of rs
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    RegistrationDTO dto = new RegistrationDTO(username, password, fullName, role);

                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }//end accounts are not existed

                    this.accounts.add(dto);
                }// end result set has not reached EOF
            }//process when it is existed
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deleteRecord(String userName)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Delete From Registration Where username = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, userName);

                int row = stm.executeUpdate();

                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateRecord(String userName, String password, boolean role)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE Registration "
                        + "SET password = ?, isAdmin = ? "
                        + "WHERE username = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, userName);
                int row = stm.executeUpdate();

                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean createNewAccount(String username, String password, String lastname, boolean role)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO Registration (username, password, lastname, isAdmin)"
                        + " VALUES (?, ?, ?, ?);";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, lastname);
                stm.setBoolean(4, role);

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
