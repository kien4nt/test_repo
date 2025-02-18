/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.*;
import utils.DBContext;

/**
 *
 * @author TRUNG NHAN
 */
public class orderItemDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;
    DBContext context;

    public orderItemDAO() {
        context = new DBContext();
    }

    public List<OrderItem> getItemsOfThisOrder(OrderInfo order) throws SQLException {
        String sql = "SELECT Book.*, OrderItems.*\n"
                + "FROM     Book INNER JOIN\n"
                + "                  OrderItems ON Book.bookID = OrderItems.bookID\n"
                + "WHERE  (OrderItems.orderID = ?)";
        Object[] params = {order.getOrderID()};
        ResultSet rs1 = context.exeQueryAlt(sql, params);

        List<OrderItem> itemList = new ArrayList<>();
        HomeBookDAO dao = new HomeBookDAO();
        while (rs1.next()) {
            itemList.add(new OrderItem(rs1.getString("orderID"), dao.getbookbyid(rs1.getInt("bookID")), rs1.getInt("quantity"), rs1.getDouble("finalPrice")));
        }
        return itemList;

    }

}
