package controller.login;

import dal.AccountDAO;
import model.Account;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;

public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Kiểm tra người dùng đã đăng nhập chưa
        Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
        if (account == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
//            response.sendRedirect("myAccount.jsp");
            response.sendRedirect("home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy tên đăng nhập và mật khẩu từ form
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            AccountDAO accountDAO = new AccountDAO();
            Account loginUser = accountDAO.checkLogin(username, password);

            if (loginUser == null) {
                // Tên đăng nhập hoặc mật khẩu không chính xác
                request.setAttribute("ERROR", "Tên đăng nhập hoặc mật khẩu không chính xác.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else if (loginUser.getStatus() == 0) {
                // Tài khoản bị vô hiệu hóa
                request.setAttribute("ERROR", "Tài khoản của bạn đã bị vô hiệu hóa.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                // Đăng nhập thành công, lưu thông tin tài khoản vào session
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", loginUser);

                // Chuyển hướng người dùng dựa trên roleId
                if ("ROL001".equals(loginUser.getRoleId())) {
                    response.sendRedirect("home"); // Admin chuyển đến trang quản lý người dùng
                } else {
                    response.sendRedirect("home"); // Người dùng thông thường chuyển đến trang tài khoản cá nhân
                }
            }
        } catch (SQLException ex) {
            // Xử lý lỗi SQL khi kết nối đến cơ sở dữ liệu
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, "Database connection error", ex);
            request.setAttribute("ERROR", "Có lỗi xảy ra. Vui lòng thử lại sau.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Login controller servlet to handle user login and role-based redirection.";
    }
}
