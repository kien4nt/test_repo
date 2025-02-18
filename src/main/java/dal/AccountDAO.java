package dal;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import model.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class AccountDAO {

    public Account checkLogin(String username, String password) throws SQLException {
        Account user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection1();
            String sql = "SELECT * FROM [Account] WHERE username = ? AND password = ?";
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, username);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String accountId = rs.getString("accountID");
                    String username1 = rs.getString("username");
                    String role = rs.getString("roleID");
                    String registrationDate = rs.getString("registrationDate");
                    int status = rs.getInt("status");
                    user = new Account(accountId, username1, "", role, registrationDate, status);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public boolean register(String username, String password, String accountID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean isRegistered = false;
        try {
            conn = DBUtils.getConnection1();
            String sql = "INSERT INTO [Account] (username, password, accountID, registrationDate, roleId,status) VALUES (?, ?, ?, ?, 'ROL003',1)";
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, username);
                ptm.setString(2, password);
                ptm.setString(3, accountID);

                String registrationDate = LocalDate.now().toString();
                ptm.setString(4, registrationDate);

                isRegistered = ptm.executeUpdate() > 0;
                System.out.println(isRegistered);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return isRegistered;
    }

    public boolean checkExist(String accountId) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        boolean exists = false;
        try {
            conn = DBUtils.getConnection1();
            String sql = "SELECT accountId FROM [Account] WHERE accountId = ?";
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, accountId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    exists = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return exists;
    }
    
  
    
    public Account getById(String accountId) throws SQLException {
        Account user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection1();
            String sql = "SELECT * FROM [Account] WHERE accountId = ?";
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, accountId);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String accountId1 = rs.getString("accountId");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String registrationDate = rs.getString("registrationDate");
                    user = new Account(accountId1, username, password, registrationDate);
                    user.setLv2password(rs.getString("lv2password"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public boolean editUser(String accountId, String username, String roleId, int status) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean isUpdated = false;

        try {
            conn = DBUtils.getConnection1();
            String sql = "UPDATE [Account] SET username = ?, roleId = ?, status = ? WHERE accountId = ?";
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, username);
                ptm.setString(2, roleId);
                ptm.setString(4, accountId);
                ptm.setInt(3, status);

                isUpdated = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return isUpdated;
    }

    public Account getByUsername(String username1) throws SQLException {
        Account user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection1();
            String sql = "SELECT * FROM [Account] WHERE username = ?";
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, username1);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String accountId1 = rs.getString("accountId");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String registrationDate = rs.getString("registrationDate");
                    user = new Account(accountId1, username, password, registrationDate);
                    user.setLv2password(rs.getString("lv2password"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public List<Account> getAllAccount() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection1();
            String sql = "SELECT * FROM [Account]";
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String accountId = rs.getString("accountId");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String role = rs.getString("roleId");
                    String registrationDate = rs.getString("registrationDate");
                    int status = rs.getInt("status");

                    Account account = new Account(accountId, username, password, role, registrationDate, status);
                    accounts.add(account);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return accounts;
    }

    public boolean deleteUser(String accountId, int status) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean isUpdated = false;

        try {
            conn = DBUtils.getConnection1();
            String sql = "UPDATE [Account] SET status = ? WHERE accountId = ?";
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setString(2, accountId);
                ptm.setInt(1, status);

                isUpdated = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return isUpdated;
    }

    public boolean resetPassword(String accountId, String oldPassword, String newPassword) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean isUpdated = false;
        try {
            conn = DBUtils.getConnection1();
            if (conn != null) {

                String updateSql = "UPDATE [Account] SET password = ? WHERE accountId = ?";
                ptm = conn.prepareStatement(updateSql);
                ptm.setString(1, newPassword);
                ptm.setString(2, accountId);

                isUpdated = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return isUpdated;
    }

}
