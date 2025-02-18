/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.product;

import dal.BookDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import model.Book;

/**
 *
 * @author conkg
 */
public class Edit extends HttpServlet {

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
            out.println("<title>Servlet Edit</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Edit at " + request.getContextPath() + "</h1>");
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
        String ID = request.getParameter("id");
        int a;
       
        try {
             BookDAO nv = new BookDAO();
            a = Integer.parseInt(ID);
                Book n = nv.getHoaDonByName(a);

            request.setAttribute("product", n);
            request.getRequestDispatcher("edit.jsp").forward(request, response);
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
     String bookIDStr = request.getParameter("txtID");
    String bookTitle = request.getParameter("txtTitle");
    String bookCover = request.getParameter("txtCover");
    String bookVersion = request.getParameter("txtVersion");
    String publisherID = request.getParameter("txtPublisherID");
    String bookPublishDateStr = request.getParameter("txtPublishDate");
    String bookImportDateStr = request.getParameter("txtImportDate");
    String bookIntro = request.getParameter("txtIntro");
    String bookQuantityStr = request.getParameter("txtQuantity");
    String bookPriceStr = request.getParameter("txtPrice");
    String bookDiscountStr = request.getParameter("txtDiscount");
     String bookFlashStr = request.getParameter("txtFlashsale");
   

    try {
         BookDAO bookDAO = new BookDAO();
        // Parse input values
        int bookID = Integer.parseInt(bookIDStr);
        int bookQuantity = Integer.parseInt(bookQuantityStr);
        double bookPrice = Double.parseDouble(bookPriceStr);
        int bookDiscount = Integer.parseInt(bookDiscountStr);
         int bookFlashSale = Integer.parseInt(bookFlashStr);
        Date bookPublishDate = Date.valueOf(bookPublishDateStr);
        Date bookImportDate = Date.valueOf(bookImportDateStr);

        // Create Book object
       Book book = new Book(bookID, bookTitle, bookCover, bookVersion, publisherID,
                bookPublishDate, bookImportDate, bookIntro, bookQuantity, bookPrice, bookDiscount,bookFlashSale);

        // Update the book information in the database
        bookDAO.edit(book);
        response.sendRedirect("ListServlet"); // Redirect after successful edit
       
    } catch (NumberFormatException e) {
        e.printStackTrace();
        request.setAttribute("errorMessage", "Invalid input: " + e.getMessage());
        request.getRequestDispatcher("edit.jsp").forward(request, response);
    } catch (IllegalArgumentException e) {
        e.printStackTrace();
        request.setAttribute("errorMessage", "Invalid date format: " + e.getMessage());
        request.getRequestDispatcher("edit.jsp").forward(request, response);
    } catch (Exception e) {
        e.printStackTrace();    
        request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
        request.getRequestDispatcher("edit.jsp").forward(request, response);
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
