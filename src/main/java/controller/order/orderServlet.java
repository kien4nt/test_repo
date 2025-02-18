/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.order;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dal.*;
import jakarta.servlet.ServletConfig;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import utils.*;

/**
 *
 * @author anhkc
 */
public class orderServlet extends HttpServlet {
    
    BookDAO bookDAO;
    OrderDAO orderDAO;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        try {
            bookDAO = new BookDAO();
        } catch (SQLException ex) {
            Logger.getLogger(orderServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(orderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        orderDAO = new OrderDAO();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet orderServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet orderServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
//            List<OrderInfo> orderList = orderDAO.selectAllOrderInfo();
//            if (orderList == null) {
//                throw new Exception("There is no order to show!");
//            }
//            request.setAttribute("orderList", orderList);
//            request.getRequestDispatcher("order-all.jsp").forward(request, response);
            response.sendRedirect("order.jsp");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
        if (account == null) {
            response.sendRedirect("login");
        }
        String name = request.getParameter("name");
        String addr = request.getParameter("addr");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String paymentMethod = request.getParameter("paymentMethod");
        String deliveryOption = request.getParameter("deliveryOption");

//        Customer cus = new customer(name, addr, phone, email);
//        request.setAttribute("cusinfo", cus);
        if ("transfer".equals(paymentMethod)) {
            request.getRequestDispatcher("transferview.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("codview.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
