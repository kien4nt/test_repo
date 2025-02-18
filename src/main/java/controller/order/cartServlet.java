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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.*;
import utils.*;

/**
 *
 * @author anhkc
 */
public class cartServlet extends HttpServlet {

    HomeBookDAO homeDAO;
    OrderDAO orderDAO;
    Utility tool;
    CartDAO cartDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        homeDAO = new HomeBookDAO();
        orderDAO = new OrderDAO();
        cartDAO = new CartDAO();
        tool = new Utility();
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
            out.println("<title>Servlet cartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet cartServlet at " + request.getContextPath() + "</h1>");
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
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
        try {
            if (account == null) {
                response.sendRedirect("login");
            } else if (cart == null) {
                cart = cartDAO.getLatestCartOfThisAccount(account);
                if (cart != null) {
                    List<CartItem> itemList = cartDAO.getItemsOfThisCart(cart);
                    cart.setItemList(itemList);
                    cart.setTotalQuantity();
                    cart.setTotalAmount(tool.getTotalAmount(itemList));
                    request.getSession().setAttribute("cart", cart);
                }
            }

            request.getRequestDispatcher("giohang.jsp").forward(request, response);
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
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "add":
                    addToCart(request, response);
                    break;
                case "delete":
                    deleteSelectedItems(request, response);
                    break;
                case "update":
                    updateQuantity(request, response);
                    break;
                case "pay":
                    pay(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

    public void addToCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String bookID = request.getParameter("id");
        String quantity = request.getParameter("quantity");

        Book orderedBook = homeDAO.getbookbyid(Integer.parseInt(bookID));
        if (orderedBook == null) {
            throw new Exception("Book not found!");
        }


//        if (orderedBook.getBookQuantity() == 0) {
//            throw new Exception("Book is out of stock!");
//        }

        if (Integer.parseInt(quantity) - orderedBook.getBookQuantity() < 0) {
            throw new Exception("Book quantity is not sufficient!");
        }
        
        orderedBook.setBookQuantity(Integer.parseInt(quantity));

        Account account = (Account) request.getSession().getAttribute("LOGIN_USER");
        Cart sessionCart = (Cart) request.getSession().getAttribute("cart");
        List<CartItem> itemList = new ArrayList<>();
        Cart dtbCart;

        if (account == null) {
            response.sendRedirect("login");
        } else {
            if (sessionCart == null) {
                dtbCart = cartDAO.getLatestCartOfThisAccount(account);
                if (dtbCart != null) {
                    itemList = cartDAO.getItemsOfThisCart(dtbCart);
                    updateItemList(itemList, dtbCart, orderedBook);
                    dtbCart.setItemList(itemList);
                } else {
                    List<Cart> cartList = cartDAO.getALLCart();
                    String cartID = tool.generateCartID(cartList);
                    CartItem newItem = new CartItem(cartID, orderedBook, orderedBook.getBookQuantity(), tool.getPrice(orderedBook));
                    itemList.add(newItem);
                    dtbCart = new Cart(cartID, account.getAccountId(), itemList, false);
                    cartDAO.insertCart(dtbCart);
                }
                dtbCart.setTotalQuantity();
                dtbCart.setTotalAmount(tool.getTotalAmount(itemList));
                request.getSession().setAttribute("cart", dtbCart);
            } else {
                itemList = sessionCart.getItemList();
                updateItemList(itemList, sessionCart, orderedBook);
                sessionCart.setTotalQuantity();
                sessionCart.setTotalAmount(tool.getTotalAmount(itemList));
                request.getSession().setAttribute("cart", sessionCart);
            }

        }

        request.getRequestDispatcher("giohang.jsp").forward(request, response);

    }

    public void updateItemList(List<CartItem> itemList, Cart cart, Book orderedBook) throws SQLException {
        boolean isFound = tool.findItemAndUpdate(itemList, orderedBook);
        if (!isFound) {
            itemList.add(new CartItem(cart.getCartID(), orderedBook, orderedBook.getBookQuantity(), tool.getPrice(orderedBook)));
            Object[] params = {cart.getCartID(), orderedBook.getBookID(), orderedBook.getBookQuantity(), tool.getPrice(orderedBook)};
            cartDAO.insertCartItem(params);
        } else {
            cartDAO.updateItemQuantityOnInsert(cart, orderedBook);
        }
    }

    public void deleteSelectedItems(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CustomException, SQLException {
        String[] selectedPids = request.getParameterValues("deletepid");
        if (selectedPids != null) {
            Cart cart = (Cart) request.getSession().getAttribute("cart");
            if (cart == null) {
                throw new CustomException("Cart not found!");
            }

            List<CartItem> itemList = cart.getItemList();
            if (itemList == null || itemList.isEmpty()) {
                throw new CustomException("Cart has no item!");
            }

            for (String pid : selectedPids) {
                Book bookToDelete = homeDAO.getbookbyid(Integer.parseInt(pid));
                if (bookToDelete == null) {
                    throw new CustomException("Book not found!");
                }

                Iterator<CartItem> iterator = itemList.iterator();
                while (iterator.hasNext()) {
                    CartItem item = iterator.next();
                    if (item.getBook().getBookID() == bookToDelete.getBookID()) {
                        iterator.remove();
                        break;
                    }
                }
                cartDAO.deleteAnItemOfThisCart(cart, bookToDelete);

            }
            cart.setTotalQuantity();
            cart.setTotalAmount(tool.getTotalAmount(itemList));
            request.getSession().setAttribute("cart", cart);
            request.getRequestDispatcher("giohang.jsp").forward(request, response);
        }

    }

    public void updateQuantity(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CustomException, SQLException {
        String[] pid = request.getParameterValues("pid");
        String[] quantities = request.getParameterValues("quantity");
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            throw new CustomException("Cart not found!");
        }

       
        List<CartItem> itemList = cart.getItemList();
        if (itemList == null || itemList.isEmpty()) {
            throw new CustomException("Cart has no item!");
        }
//
//        for (int i = 0; i < itemList.size(); i++) {
//            int bookID = Integer.parseInt(pid[i]);
//            int quantity = Integer.parseInt(arrQuantity[i]);
//            itemList.get(i).setQuantity(quantity);
//            cartDAO.updateItemQuantity(cart, bookID, quantity);
//        }
       
         for (int i = 0; i < itemList.size(); i++) {
            int bookID = Integer.parseInt(pid[i]);

            if (quantities[i] != null) {
                int quantity = Integer.parseInt(quantities[i]);
                itemList.get(i).setQuantity(quantity);
                cartDAO.updateItemQuantity(cart, bookID, quantity);
            }
        }

        
        cart.setTotalQuantity();
        cart.setTotalAmount(tool.getTotalAmount(itemList));
        request.getSession().setAttribute("cart", cart);
        request.getRequestDispatcher("giohang.jsp").forward(request, response);

    }

    public void pay(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, CustomException, SQLException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            throw new CustomException("Cart not found!");
        }

        List<CartItem> cartItemList = cart.getItemList();
        if (cartItemList == null || cartItemList.isEmpty()) {
            throw new CustomException("Cart has no item!");
        }

        List<OrderInfo> orderList = orderDAO.selectAllOrderInfo();
        String id = tool.generateOrderID(orderList);

        List<OrderItem> orderItemList = new ArrayList<>();
        for (CartItem cartItem : cart.getItemList()) {
            OrderItem item = new OrderItem(id, cartItem.getBook(), cartItem.getQuantity(), cartItem.getFinalPrice());
            orderItemList.add(item);
        }

        OrderInfo info = new OrderInfo(id, tool.getLocalDate(), tool.getTotalAmount(cartItemList), cart.getAccountID(), orderItemList);
        orderDAO.insert(info);
        cartDAO.updateCheckOutStatus(cart);
//        Iterator<CartItem> iterator = cartItemList.iterator();
//        while (iterator.hasNext()) {
//            CartItem item = iterator.next();
//            iterator.remove();
//        }
        request.getSession().removeAttribute("cart");
        response.sendRedirect("order");

    }

}
