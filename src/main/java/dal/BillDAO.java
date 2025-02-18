/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Bill;
import utils.DBContext;

/**
 *
 * @author Acer
 */
public class BillDAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
       public List<Bill> selectAllBill() throws SQLException {
        String query = "select * from Bill";
        List<Bill> list = new ArrayList<>();
           try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();  
             while (rs.next()) {
            list.add(new Bill(
                    rs.getString("orderID"),
                    rs.getInt("paymentStatusID"),
                    rs.getInt("deliveryStatusID"),
                    rs.getDouble("billTotalAmount"),
                    rs.getDate("billDate")
            ));   
            
        }
           } catch (Exception e) {
           }
           
       

        return list;
    }


}
