/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.order;

import model.*;
import dal.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author TRUNG NHAN
 */
@WebServlet(name = "updateServlet", urlPatterns = {"/updateOrder"})
public class updateOrderController extends HttpServlet {

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
            out.println("<title>Servlet updateServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateServlet at " + request.getContextPath() + "</h1>");
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
        String id = request.getParameter("id");
         OrderDAO oddao = new OrderDAO();
        try {
            OrderInfo od = oddao.getOrderByID(id);
            request.setAttribute("order", od);
            List<delivery> deliveryList = oddao.getAllDelivery();
        List<deliveryStatus> deliveryStatuslist = oddao.getAllDelStatus();
        List<payment> paymentList = oddao.getAllPayment();
        List<paymentStatus> paymentStatusList = oddao.getAllPstatus();
        request.setAttribute("deliveryList", deliveryList);
        request.setAttribute("deliveryStatuslist", deliveryStatuslist);
        request.setAttribute("paymentList", paymentList);
        request.setAttribute("paymentStatusList", paymentStatusList);
            request.getRequestDispatcher("updateOrderForm.jsp").forward(request, response);
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
        String orderID = request.getParameter("orderID");
        String orderDate_temp = request.getParameter("orderDate");
        String deliveryAddress = request.getParameter("deliveryAddress");
        String paymentMethodID = request.getParameter("paymentMethodID");
        String deliveryOptionID = request.getParameter("deliveryOptionID");
        String orderTotalAmount_temp = request.getParameter("orderTotalAmount");
        String customerID = request.getParameter("customerID");
//        String employeeID = request.getParameter("employeeID");
        OrderDAO odao = new OrderDAO();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        double orderTotalAmount;
        try {
            java.util.Date orderDate = (java.util.Date) format.parse(orderDate_temp);
            java.sql.Date sqlDate = new java.sql.Date(orderDate.getTime());
            orderTotalAmount = Double.parseDouble(orderTotalAmount_temp);
            odao.updateInfo(new OrderInfo(orderID, sqlDate, deliveryAddress, paymentMethodID, deliveryOptionID, orderTotalAmount, customerID));
            response.sendRedirect("listOrder");
        } catch (IOException | ParseException e) {
            System.out.println(e);
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
