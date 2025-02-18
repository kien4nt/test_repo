package controller.login;

import dal.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String id = request.getParameter("id");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String repassword = request.getParameter("confirm_password");

            AccountDAO d = new AccountDAO();
            boolean check = d.checkExist(id);

            // Kiểm tra nếu tài khoản đã tồn tại
            if (check) {
                request.setAttribute("ERROR", "Tài khoản đã tồn tại");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            // Kiểm tra mật khẩu khớp
            if (!password.equals(repassword)) {
                request.setAttribute("ERROR", "Mật khẩu không khớp, vui lòng nhập lại");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            // Nếu không có lỗi, đăng ký tài khoản
            d.register(username, password, id);

            // Thông báo đăng ký thành công nhưng giữ lại ở trang đăng ký
            request.setAttribute("SUCCESS", "Đăng ký thành công! Bạn có thể đăng nhập.");
            request.getRequestDispatcher("register.jsp").forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("ERROR", "Lỗi trong quá trình đăng ký. Vui lòng thử lại sau.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
