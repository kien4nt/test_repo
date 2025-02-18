package controller.login;

import dal.AccountDAO;
import model.Account;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ManageUserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");

            // Lấy thông tin tài khoản đang đăng nhập từ session
            HttpSession session = request.getSession();
            Account loggedInUser = (Account) session.getAttribute("LOGIN_USER");

            // Kiểm tra nếu người dùng chưa đăng nhập, chuyển hướng đến trang đăng nhập
            if (loggedInUser == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            // Lấy danh sách tài khoản từ database
            AccountDAO accountDAO = new AccountDAO();
            List<Account> accounts = accountDAO.getAllAccount();

            // Đặt accounts và loggedInUser vào request để JSP sử dụng
            request.setAttribute("accounts", accounts);
            request.setAttribute("loggedInUser", loggedInUser);
            request.getRequestDispatcher("managerUser.jsp").forward(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(ManageUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "ManageUserController servlet to handle user management";
    }
}
