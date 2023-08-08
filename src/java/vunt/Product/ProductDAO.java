/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vunt.Product;

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
public class ProductDAO implements Serializable{
        
    public static List<ProductDTO> getProducts(boolean status)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<ProductDTO> products = null;
        try {
            //1 Connect DB
            con = DBHelper.makeConnection();
            //2 Create SQL String
            if (con != null) {
                String sql = "Select sku, name, price "
                        + "From Product "
                        + "Where status = ?";

                //3 Create Statement
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, status);
                //4 Excute Query
                rs = stm.executeQuery();
                //5 process Result
                while (rs.next()) {
                    //get tung field cua record of rs
                    String sku = rs.getString("sku");
                    String name = rs.getString("name"); 
                    float price = rs.getFloat("price");
                    ProductDTO dto = new ProductDTO(sku, name, price);
                    
                    if (products == null) {
                        products = new ArrayList<>();
                    }//end accounts are not existed

                    products.add(dto);
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
        return products;
    }
}
