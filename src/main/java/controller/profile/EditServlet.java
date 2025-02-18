/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.profile;

import dal.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import model.Address;
import model.Customer;

/**
 *
 * @author Acer
 */
public class EditServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
            out.println("<title>Servlet EditServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditServlet at " + request.getContextPath() + "</h1>");
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
        String Id = request.getParameter("id");
        Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
//        Account account = new Account();
//        account.setAccountId("ACC002");
//       request.getSession().setAttribute("LOGIN_USER", account);
        if (account == null) {
            response.sendRedirect("login");
        }
        AddressDAO nv = new AddressDAO();
        try {
            Customer n = nv.getJoinedAddressDetailsByID(account.getAccountId(), Id);
            Address a = nv.getAddressByID(Id);
            request.setAttribute("address", a);
            request.setAttribute("customer", n);
            request.getRequestDispatcher("editPersonalInfo.jsp").forward(request, response);
        } catch (Exception e) {
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
//        Account account = new Account();
//        account.setAccountId("ACC002");
//       request.getSession().setAttribute("LOGIN_USER", account);
        if (account == null) {
            response.sendRedirect("login");
        }

        String addressID = request.getParameter("addressID");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String addressDetail = request.getParameter("addressDetail");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String birthDateStr = request.getParameter("birthDate");
        boolean defaultAddress = request.getParameter("defaultAddress") != null;

        Address address = new Address(addressID, addressDetail, defaultAddress);
        Customer customer = new Customer();
        customer.setCustomerID(account.getAccountId());
        customer.setFirstName(firstName);
        customer.setLastName(lastName);

        customer.setEmail(email);
        customer.setPhoneNumber(phoneNumber);
        customer.setBirthDate(Date.valueOf(birthDateStr));

        EditDAO editDAO = new EditDAO();
        boolean isUpdated = editDAO.updateCustomer(customer, address);

        if (isUpdated) {
            response.sendRedirect("AddressServlet");
        } else {
            request.setAttribute("errorMessage", "Cập nhật không thành công. Vui lòng thử lại.");
            request.getRequestDispatcher("AddressServlet").forward(request, response);
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
