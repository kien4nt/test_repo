/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import model.Address;
import model.Customer;
import utils.DBContext;

/**
 *
 * @author Acer
 */
public class AddressDAO {
     DBContext dbContext = new DBContext();  

// Phương thức để lấy dữ liệu với các bảng được join
    public Customer getJoinedAddressDetails(String customerID) {
        List<Customer> addressDetails = new ArrayList<>();
        String sql = "SELECT Address.addressID, Address.addressDetail, Customer.customerID, " +
                     "Customer.firstName, Customer.lastName, Customer.email, Customer.phoneNumber, " +
                     "Customer.birthDate, Account_Address.defaultAddress " +
                     "FROM Account " +
                     "INNER JOIN Account_Address ON Account.accountID = Account_Address.accountID " +
                     "INNER JOIN Address ON Account_Address.addressID = Address.addressID " +
                     "INNER JOIN Customer ON Account.accountID = Customer.customerID " +
                     "WHERE Customer.customerID = ?";
 Customer customer = new Customer();
        try {
            Object[] params = {customerID};
            ResultSet rs = dbContext.exeQueryAlt(sql, params);
           
            List<Address> addressList = new ArrayList<>();
            while (rs.next()) {
         
                Address address = new Address();
                address.setAddressID(rs.getString("addressID"));
                address.setAddressDetail(rs.getString("addressDetail"));
                address.setDefaultAddress(rs.getBoolean("defaultAddress"));
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                Date birthDate = rs.getDate("birthDate");
                                
                addressList.add(address);
                
                customer.setFirstName(firstName);
                customer.setLastName(lastName);
                customer.setEmail(email);
                customer.setPhoneNumber(phoneNumber);
                customer.setBirthDate(birthDate);
                

                
            
           
            }
            customer.setAddressList(addressList);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }
    public void update(Object[] params) throws SQLException {
        String sql = "update customer\n"
                + "set firstName =?,\n"
                + "lastName =?,\n"
                + " email =?, \n"
                + "phoneNumber =?,\n"
                + "birthDate =?\n"
                + "where customerID = ?";
        int rowsAffected = dbContext.exeNonQuery(sql, params);
        System.out.println(rowsAffected + " rows affected!!!!!!!!");
    }
    
    public void insertAddress(Object[] params) throws SQLException {
        String sql = "insert into address values(?,?)";
        dbContext.exeNonQuery(sql, params);
    }
    
    public void insertAccountAddress(Object[] params) throws SQLException {
        String sql = "insert into Account_Address values(?,?,?)";
        dbContext.exeNonQuery(sql, params);
    }
    
     public List<Address> selectAllAddress() throws SQLException {
        String sql = "select * from address";
        List<Address> categoryList = new ArrayList<>();

        ResultSet rs = dbContext.exeQuery(sql);
        while (rs.next()) {
            categoryList.add(new Address(rs.getString(1), rs.getString(2)
            ));
        }
        return categoryList;
    }
     
      public Customer getJoinedAddressDetailsByID(String customerID, String addressID) {
          
       
        String sql = "SELECT Address.addressID, Address.addressDetail, Customer.customerID, " +
                     "Customer.firstName, Customer.lastName, Customer.email, Customer.phoneNumber, " +
                     "Customer.birthDate, Account_Address.defaultAddress " +
                     "FROM Account " +
                     "INNER JOIN Account_Address ON Account.accountID = Account_Address.accountID " +
                     "INNER JOIN Address ON Account_Address.addressID = Address.addressID " +
                     "INNER JOIN Customer ON Account.accountID = Customer.customerID " +
                     "WHERE Customer.customerID = ? AND Address.addressID = ?";
       
        Customer customer = new Customer();
        try {
            Object[] params = {customerID, addressID};
            ResultSet rs = dbContext.exeQueryAlt(sql, params);
           
            List<Address> addressList = new ArrayList<>();
            while (rs.next()) {
         
                Address address = new Address();
                address.setAddressID(rs.getString("addressID"));
                address.setAddressDetail(rs.getString("addressDetail"));
                address.setDefaultAddress(rs.getBoolean("defaultAddress"));
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                Date birthDate = rs.getDate("birthDate");
                                
                addressList.add(address);
                customer.setCustomerID(rs.getString("customerID"));
                
                customer.setFirstName(firstName);
                customer.setLastName(lastName);
                customer.setEmail(email);
                customer.setPhoneNumber(phoneNumber);
                customer.setBirthDate(birthDate);
                

                
            
           
            }
            customer.setAddressList(addressList);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
}   
      public Address getAddressByID(String id) throws SQLException {
        DBContext dc = new DBContext();
        Object[] params = {id};
        String sql = "SELECT Address.*, Account_Address.defaultAddress\n"
                + "FROM     Account_Address INNER JOIN\n"
                + "                  Address ON Account_Address.addressID = Address.addressID\n"
                + "WHERE  (Address.addressID = ?)";
        ResultSet rs = dc.exeQueryAlt(sql, params);
        if (rs.next()) {
            return new Address (rs.getString(1),rs.getString(2),rs.getBoolean(3));
        }
        return null;

    }
}   

