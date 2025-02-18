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
import utils.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anhkc
 */
public class OrderDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    DBContext context;

    public OrderDAO() {
        context = new DBContext();
    }

    public List<OrderInfo> selectAllOrderInfo() throws SQLException {
        String sql = "SELECT [orderID]\n"
                + "      ,[orderDate]\n"
                + "      ,[deliveryAddress]\n"
                + "      ,[paymentMethodID]\n"
                + "      ,[deliveryOptionID]\n"
                + "      ,[orderTotalAmount]\n"
                + "      ,[customerID]\n"
                + "  FROM [dbo].[OrderInfo]";
        List<OrderInfo> orderList = new ArrayList<>();
        ResultSet rs = context.exeQuery(sql);
        while (rs.next()) {
            orderList.add(new OrderInfo(rs.getString(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7)));
        }
        return orderList;

    }

    public OrderInfo getOrderByID(Object[] params) throws SQLException {
        String sql = "select * from OrderInfo where orderID = ?";
        ResultSet rs = context.exeQueryAlt(sql, params);
        if (rs.next()) {
            return new OrderInfo(rs.getString(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7));
        }
        return null;

    }

    public void insert(OrderInfo orderInfo) throws SQLException {
        String sql = "insert into OrderInfo(orderID,orderDate,orderTotalAmount,customerID) values(?,?,?,?)";
        Object[] params = {orderInfo.getOrderID(), orderInfo.getOrderDate(), orderInfo.getOrderTotalAmount(), orderInfo.getCustomerID()};
        int rowsAffected = context.exeNonQuery(sql, params);
        System.out.println(rowsAffected + " rows affected");
        callInsertOrderItem(orderInfo);
    }

    public void insertOrderItem(Object[] params) throws SQLException {
        String sql = "insert into OrderItems values(?,?,?,?)";
        int rows = context.exeNonQuery(sql, params);
        System.out.println(rows);
    }

    public void callInsertOrderItem(OrderInfo orderInfo) throws SQLException {
        for (int i = 0; i < orderInfo.getItemList().size(); i++) {
            OrderItem item = orderInfo.getItemList().get(i);
            Object[] params = {orderInfo.getOrderID(), item.getBook().getBookID(), item.getQuantity(), item.getFinalPrice()};
            insertOrderItem(params);
        }
    }

    public void updateBill(Object[] thamso) throws SQLException {
        String sqlBill = "UPDATE [dbo].[Bill]\n"
                + "   SET \n"
                + "      [paymentStatusID] = ?\n"
                + "      ,[deliveryStatusID] = ?\n"
                + "      ,[billTotalAmount] = ?\n"
                + "      ,[billDate] = ?\n"
                + " WHERE [orderID] = ?";
        context.exeNonQuery(sqlBill, thamso);
    }

    public void updateInfo(Object[] thamso) throws SQLException {
        String sqlInfo = "UPDATE [dbo].[OrderInfo]\n"
                + "   SET \n"
                + "     [orderDate] = ?\n"
                + "      ,[deliveryAddress] = ?\n"
                + "      ,[paymentMethodID] = ?\n"
                + "      ,[deliveryOptionID] = ?\n"
                + "      ,[orderTotalAmount] = ?\n"
                + "      ,[customerID] = ?\n"
                + "      ,[employeeID] = ?\n"
                + " WHERE [orderID] = ?";
        context.exeNonQuery(sqlInfo, thamso);
    }

    public void updateItem(Object[] thamso) throws SQLException {
        String sqlItems = "UPDATE [dbo].[OrderItems]\n"
                + "   SET \n"
                + "      [bookID] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[finalPrice] = ?\n"
                + " WHERE [orderID] = ?";

        context.exeNonQuery(sqlItems, thamso);
    }

    public List<delivery> getAllDelivery() {
        List<delivery> list = new ArrayList<>();
        String sql = "select * from deliveryOption";
//        String sql = "SELECT Bill.*, OrderInfo.*\n"
//                + "FROM     Bill INNER JOIN\n"
//                + "                  OrderInfo ON Bill.orderID = OrderInfo.orderID";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                delivery del = new delivery(rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDouble(4));

                list.add(del);
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }

        return list;
    }

    public List<payment> getAllPayment() {
        List<payment> list = new ArrayList<>();
        String sql = "select * from paymentMethod";
//        String sql = "SELECT Bill.*, OrderInfo.*\n"
//                + "FROM     Bill INNER JOIN\n"
//                + "                  OrderInfo ON Bill.orderID = OrderInfo.orderID";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                payment pay = new payment(rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3));

                list.add(pay);
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }

        return list;
    }

    public List<paymentStatus> getAllPstatus() {
        List<paymentStatus> list = new ArrayList<>();
        String sql = "select * from paymentStatus";
//        String sql = "SELECT Bill.*, OrderInfo.*\n"
//                + "FROM     Bill INNER JOIN\n"
//                + "                  OrderInfo ON Bill.orderID = OrderInfo.orderID";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                paymentStatus odl = new paymentStatus(rs.getInt(1),
                        rs.getString(2));

                list.add(odl);
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }

        return list;
    }

    public List<deliveryStatus> getAllDelStatus() {
        List<deliveryStatus> list = new ArrayList<>();
        String sql = "select * from deliveryStatus";
//        String sql = "SELECT Bill.*, OrderInfo.*\n"
//                + "FROM     Bill INNER JOIN\n"
//                + "                  OrderInfo ON Bill.orderID = OrderInfo.orderID";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                deliveryStatus odl = new deliveryStatus(rs.getInt(1),
                        rs.getString(2));

                list.add(odl);
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }

        return list;
    }

    public List<OrderInfo> getAll() throws SQLException {
        String sql = "SELECT [orderID]\n"
                + "      ,[orderDate]\n"
                + "      ,[deliveryAddress]\n"
                + "      ,[paymentMethodID]\n"
                + "      ,[deliveryOptionID]\n"
                + "      ,[orderTotalAmount]\n"
                + "      ,[customerID]\n"
                + "  FROM [dbo].[OrderInfo]";
        List<OrderInfo> orderList = new ArrayList<>();
        ResultSet rs = context.exeQuery(sql);
        while (rs.next()) {
            orderList.add(new OrderInfo(rs.getString(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7)));
        }
        return orderList;
    }
//--------delete------------

    public void deleteItem(String id) {
        String sql = "DELETE FROM [dbo].[OrderItems]\n"
                + "      WHERE orderID =?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
            deleteBill(id);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteBill(String id) {
        String sql = "DELETE FROM [dbo].[Bill]\n"
                + "  WHERE orderID =?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
            deleteInfo(id);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteInfo(String id) {
        String sql = "DELETE FROM [dbo].[OrderInfo]\n"
                + "      WHERE orderID = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

//    public void insertOL(Order ol) {
//        String sql = "INSERT INTO [dbo].[userManagement]\n"
//                + "           ([username]\n"
//                + "           ,[password])\n"
//                + "     VALUES\n"
//                + "           (?, ?)";
//        try {
//            ps = conn.prepareStatement(sql);
//            ps.setString(1, ol.getUsername());
//            ps.setString(2, ol.getPassword());
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//    }
//
    //--------------update-------------
    public void updateBill(OrderInfo o) {
        String sql = "UPDATE [dbo].[Bill]\n"
                + "   SET \n"
                + "      [paymentStatusID] = ?\n"
                + "      ,[deliveryStatusID] = ?\n"
                + "      ,[billTotalAmount] = ?\n"
                + "      ,[billDate] = ?\n"
                + " WHERE [orderID] = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, o.getPaymentStatusID());
            ps.setInt(2, o.getDeliveryStatusID());
            ps.setDouble(3, o.getOrderTotalAmount());
            ps.setDate(4, o.getOrderDate());
            ps.setString(5, o.getOrderID());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateInfo(OrderInfo o) {
        String sql = "UPDATE [dbo].[OrderInfo]\n"
                + "   SET \n"
                + "      [orderDate] = ?\n"
                + "      ,[deliveryAddress] = ?\n"
                + "      ,[paymentMethodID] = ?\n"
                + "      ,[deliveryOptionID] = ?\n"
                + "      ,[orderTotalAmount] = ?\n"
                + "      ,[customerID] = ?\n"
                + " WHERE [orderID] = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setDate(1, o.getOrderDate());
            ps.setString(2, o.getDeliveryAddress());
            ps.setString(3, o.getPaymentMethodID());
            ps.setString(4, o.getDeliveryOptionID());
            ps.setDouble(5, o.getOrderTotalAmount());
            ps.setString(6, o.getCustomerID());
            ps.setString(7, o.getOrderID());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

//----------get BY---------
//    public Order getOlByID(int id) {
//        String sql = "SELECT *\n"
//                + "  FROM [dbo].[userManagement]\n"
//                + "  WHERE [id] = ?";
//        try {
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1, id);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                Order odl = new Order(rs.getInt("id"),
//                        rs.getString("username"),
//                        rs.getString("password"));
//                return odl;
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return null;
//    }
    public OrderInfo getOrderByID(String id) throws SQLException {
        String sql = "select * from OrderInfo where orderID = ?";
        Object[] params = {id};
        ResultSet rs = context.exeQueryAlt(sql, params);
        if (rs.next()) {
            return new OrderInfo(rs.getString(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7));
        }
        return null;
    }

    public void setBillToOrder(String id, OrderInfo o) {
        String sql = "SELECT *\n"
                + "  FROM [dbo].[Bill]\n"
                + "  WHERE [orderID] = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                o.setDeliveryStatusID(rs.getInt(3));
                o.setPaymentStatusID(rs.getInt(2));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

//    public OrderInfo getBillByID(String id) {
//        String sql = "SELECT *\n"
//                + "  FROM [dbo].[Bill]\n"
//                + "  WHERE [orderID] = ?";
//        try {
//            ps = conn.prepareStatement(sql);
//            ps.setString(1, id);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                OrderInfo od = new OrderInfo(rs.getString(1),
//                        rs.getInt(2),
//                        rs.getInt(3),
//                        rs.getDouble(4),
//                        rs.getDate(5));
//                return od;
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return null;
//    }
}
