/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.profile;

import dal.AddressDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import model.Address;
import model.Customer;
import utils.Utility;

/**
 *
 * @author Acer
 */
public class AddressServlet extends HttpServlet {
 private AddressDAO addressDAO = new AddressDAO();
 public AddressServlet() {
        super();
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
            out.println("<title>Servlet AddressServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddressServlet at " + request.getContextPath() + "</h1>");
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
        
        Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
//        Account account = new Account();
//        account.setAccountId("ACC002");
//       request.getSession().setAttribute("LOGIN_USER", account);
        try {
           if (account == null) {
               response.sendRedirect("login");
           } 
             Customer customer = addressDAO.getJoinedAddressDetails(account.getAccountId());
              if (customer.getAddressList()!=null && !customer.getAddressList().isEmpty()){ //list ko rong thi gui ra
                    request.setAttribute("customer", customer);
              }
          
          
        

    
        request.getRequestDispatcher("address.jsp").forward(request, response);
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
            String firstName = request.getParameter("firstName");
          String lastName = request.getParameter("lastName");
          String addressDetail = request.getParameter("addressDetail");
          String email = request.getParameter("email");
          String phoneNumber = request.getParameter("phoneNumber");
          String birthDate = request.getParameter("birthDate");
          String defaultAddress = request.getParameter("defaultAddress");
          AddressDAO addressdao = new AddressDAO();
          Utility utility = new Utility();
          try {
            Object[] param1 = {firstName,lastName,email,phoneNumber,birthDate,account.getAccountId()};
            addressDAO.update(param1);
            List<Address> address = addressdao.selectAllAddress();
            Object[] param2 = {utility.generateAddressID(address),addressDetail};
            addressDAO.insertAddress(param2);
            Object[] param3 = {account.getAccountId(),utility.generateAddressID(address),Boolean.parseBoolean(defaultAddress)};
            addressDAO.insertAccountAddress(param3);
            response.sendRedirect("AddressServlet");
        } catch (Exception e) {
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
