/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.product;

import dal.BookDAO;
import dal.CategroryDAO;
import jakarta.servlet.RequestDispatcher;
import model.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Category;

/**
 *
 * @author conkg
 */
public class Phanloai extends HttpServlet {

    private static final int PAGE_SIZE = 12; // Items per page

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
            out.println("<title>Servlet Phanloai</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Phanloai at " + request.getContextPath() + "</h1>");
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
        int page = 1; //Default page
        String pageNumber = request.getParameter("page");
        String phanloai = request.getParameter("phanloai");
        CategroryDAO cdao = new CategroryDAO();
        BookDAO bdao = new BookDAO();

        List<Book> booksOfThisCategory = new ArrayList<>();
        List<Book> booksOfThisPage = new ArrayList<>();
        int totalProducts = 0;
        int totalPages = 0;

       
            if (pageNumber != null) {
                //Update the current page number
                page = Integer.parseInt(pageNumber);
            }

            if (phanloai != null) {
                // "phanloai" != null when you click on a category in the "DANH MUC"
                booksOfThisCategory = cdao.getBookByCategrogy(phanloai);
                totalProducts = booksOfThisCategory.size();
                totalPages = (int) Math.ceil((double) totalProducts / PAGE_SIZE);
                
                //Store the collection of all books belong to the category in "session"
                request.getSession().setAttribute("booksOfThisCategory", booksOfThisCategory);
                request.getSession().setAttribute("totalProducts", totalProducts);
                request.getSession().setAttribute("totalPages", totalPages);
            } else {
                
                // "phanloai" == null when you want to see the books in next page (you don't click the "DANH MUC", you click the page number)
                // Get the collection of all books the we store in "session" previously
                booksOfThisCategory = (List<Book>) request.getSession().getAttribute("booksOfThisCategory");
                totalProducts = (int) request.getSession().getAttribute("totalProducts");
            }
            
            // "booksOfThisPage" is all books that should be displayed on the current page
            if (totalProducts >= PAGE_SIZE) {
                int i = PAGE_SIZE * (page - 1);
                while (i < PAGE_SIZE * page && i < booksOfThisCategory.size()) {
                    booksOfThisPage.add(booksOfThisCategory.get(i));
                    i++;
                }
            } else {
                booksOfThisPage = booksOfThisCategory;
            }
            
            //Call "booksOfThisPage" on jsp to show data
            request.setAttribute("booksOfThisPage", booksOfThisPage);
            request.setAttribute("currentPage", page);
            request.getRequestDispatcher("newjsp1.jsp").forward(request, response);

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
        processRequest(request, response);
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
