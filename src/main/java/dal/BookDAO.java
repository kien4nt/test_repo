/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import utils.DBContext;
import model.Author;
import model.Book;
import model.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author conkg
 */
public class BookDAO {
     private DBContext dbContext;
    Connection conn ;
    PreparedStatement ps;
    ResultSet rs ;

    public BookDAO() throws SQLException, ClassNotFoundException {
        conn = new DBContext().getConnection();
         dbContext = new DBContext();
    }
  
    

    
    public List<Book> getALLBook() {
        List<Book> list = new ArrayList<>();
        String query = "select * from Book";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Book(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        rs.getString(8),
                        rs.getInt(9),
                         rs.getDouble(10),
                         rs.getInt(11),
                          rs.getInt(12)
                ));

            }
            
        } catch (Exception e) {
        }

        return list;
    }

   

    public List<Category> getAllcategory() {
        List<Category> list = new ArrayList<>();
        String query = "select * from Category"  ;
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(
                        rs.getString(1),
                        rs.getString(2)
                ));

            }
        } catch (Exception e) {
        }

        return list;
    }
 public Book getbookbyid(int bookID) {
       
        String query = "select * from Book\n"
                + "where bookID =?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1,bookID);
            rs = ps.executeQuery();
           
            while (rs.next()) {
                return new Book(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        rs.getString(8),
                        rs.getInt(9),
                         rs.getDouble(10),
                         rs.getInt(11),
                         rs.getInt(12))
                ;

            }
        } catch (Exception e) {
        }
        return null;
 }
    public List<Book> getbooknew(String query) {
    List<Book> list = new ArrayList<>();
  
    try {
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery(); 
        while (rs.next()) {
            list.add(new Book(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getDate(6),
                    rs.getDate(7),
                    rs.getString(8),
                    rs.getInt(9),
                    rs.getDouble(10),
                    rs.getInt(11),
                         rs.getInt(12)
            ));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // Return the populated list
}
    
    
    
    
//   public List<Book> getBooksSortedBy(String orderBy) {
//    List<Book> list = new ArrayList<>();
//    String query = "SELECT * FROM Book ORDER BY " + orderBy;
//    try {
//        conn = new DBContext().getConnection();
//        ps = conn.prepareStatement(query);
//        rs = ps.executeQuery();
//        while (rs.next()) {
//            list.add(new Book(
//                    rs.getInt("bookID"),
//                    rs.getString("bookTitle"),
//                    rs.getString("bookAuthor"),
//                    rs.getString("bookCover"),
//                    rs.getString("bookDescription"),
//                    rs.getDate("publishDate"),
//                    rs.getDate("updateDate"),
//                    rs.getString("publisher"),
//                    rs.getInt("sales"),        // Cột doanh số bán hàng
//                    rs.getDouble("bookPrice"), // Cột giá sách
//                    rs.getInt("bookQuantity")  // Cột số lượng sách
//            ));
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//    return list;
//
//} 
 
     public Book getHoaDonByName(int bookID) throws ClassNotFoundException {
        String query = "select * from book where bookID=?";
        try {
            conn = new DBContext().getConnection();
             ps = conn.prepareStatement(query);
              ps.setInt(1, bookID);
             rs = ps.executeQuery();
            while (rs.next()) {
                Book hd = new Book(
                      rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        rs.getString(8),
                        rs.getInt(9),
                         rs.getDouble(10),
                         rs.getInt(11),
                             rs.getInt(12)
                );
                return hd;
            }
        } catch (SQLException e) {
        }
        return null;
    
      
 
    
}
public void edit(Book book) throws ClassNotFoundException {
    String sql = "UPDATE [dbo].[Book] SET [bookTitle] = ?, [bookCover] = ?, [bookVersion] = ?, " +
                 "[publisherID] = ?, [bookPublishDate] = ?, [bookImportDate] = ?, " +
                 "[bookIntro] = ?, [bookQuantity] = ?, [bookPrice] = ?, [bookDiscount] = ? ,bookFlashSale = ? " +
                 "WHERE [bookID] = ?";
   
    PreparedStatement st = null;

    try {
        
        st = conn.prepareStatement(sql);
        st.setString(1, book.getBookTitle());
        st.setString(2, book.getBookCover());
        st.setString(3, book.getBookVersion());
        st.setString(4, book.getPublisherID());
        st.setDate(5, book.getBookPublishDate());
        st.setDate(6, book.getBookImportDate());
        st.setString(7, book.getBookIntro());
        st.setInt(8, book.getBookQuantity());
        st.setDouble(9, book.getBookPrice());
        st.setDouble(10, book.getBookDiscount());
        st.setInt(11, book.getBookFlashSale());
        st.setInt(12, book.getBookID());
        st.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close resources
        try {
            if (st != null) st.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
  public void create(Book book) {
        String query = "INSERT INTO [dbo].[Book] (bookID, bookTitle, bookCover, bookVersion, publisherID, bookPublishDate, bookImportDate, bookIntro, bookQuantity, bookPrice, bookDiscount,bookFlashSale) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
          
             ps = conn.prepareStatement(query);
      
            ps.setInt(1, book.getBookID()); // bookID
            ps.setString(2, book.getBookTitle());
            ps.setString(3, book.getBookCover());
            ps.setString(4, book.getBookVersion());
            ps.setString(5, book.getPublisherID());
            ps.setDate(6, book.getBookPublishDate());
            ps.setDate(7, book.getBookImportDate());
            ps.setString(8, book.getBookIntro());
            ps.setInt(9, book.getBookQuantity());
            ps.setDouble(10, book.getBookPrice());
            ps.setDouble(11, book.getBookDiscount());
              ps.setInt(12, book.getBookFlashSale());
            // Execute the update
           int role = ps.executeUpdate();
            System.out.println(role);
        } catch (SQLException e) {
            e.printStackTrace(); // Print exception if an error occurs
        } finally {
            // Clean up resources
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
  
   public void delete(int bookID) {
        String deleteOrderItems = "DELETE FROM OrderItems WHERE bookID = ?";
        String deleteCartItems = "DELETE FROM CartItems WHERE bookID = ?";
        String deleteBookAuthor = "DELETE FROM Book_Author WHERE bookID = ?";
        String deleteBookCategory = "DELETE FROM Book_Category WHERE bookID = ?";
        String deleteBook = "DELETE FROM Book WHERE bookID = ?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = new DBContext().getConnection();
            conn.setAutoCommit(false); // Begin transaction

            // Delete from OrderItems
            ps = conn.prepareStatement(deleteOrderItems);
            ps.setInt(1, bookID);
            ps.executeUpdate();
            ps.close();

            // Delete from CartItems
            ps = conn.prepareStatement(deleteCartItems);
            ps.setInt(1, bookID);
            ps.executeUpdate();
            ps.close();

            // Delete from Book_Author
            ps = conn.prepareStatement(deleteBookAuthor);
            ps.setInt(1, bookID);
            ps.executeUpdate();
            ps.close();

            // Delete from Book_Category
            ps = conn.prepareStatement(deleteBookCategory);
            ps.setInt(1, bookID);
            ps.executeUpdate();
            ps.close();

            // Delete from Book
            ps = conn.prepareStatement(deleteBook);
            ps.setInt(1, bookID);
            ps.executeUpdate();
            ps.close();

            conn.commit(); // Commit transaction
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback transaction on error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public List<Book> getPaginatedProducts(int pageNumber, int pageSize) throws ClassNotFoundException {
        List<Book> list = new ArrayList<>();
        String query = "SELECT *\n"
                + "FROM Book ORDER BY bookID\n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);

            ps.setInt(1, (pageNumber - 1) * pageSize);
            ps.setInt(2, pageSize);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Book(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getDouble(10),
                        rs.getInt(11),
                        rs.getInt(12)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalProducts() throws ClassNotFoundException {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Book";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<Book> getSeach(String tuKhoaTimKiem) {
        List<Book> list = new ArrayList<>();
        String query = "SELECT distinct Book.*\n"
                + "FROM     Author INNER JOIN\n"
                + "                  Book_Author ON Author.authorID = Book_Author.authorID INNER JOIN\n"
                + "                  Book ON Book_Author.bookID = Book.bookID INNER JOIN\n"
                + "                  Book_Category ON Book.bookID = Book_Category.bookID INNER JOIN\n"
                + "                  Category ON Book_Category.categoryID = Category.categoryID\n"
                + "WHERE  (Book.bookTitle LIKE ?) OR\n"
                + "                  (Author.authorName LIKE ?) OR\n"
                + "                  (Category.categoryName LIKE ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
                   ps.setString(1, tuKhoaTimKiem);
                   ps.setString(2, tuKhoaTimKiem);
                   ps.setString(3, tuKhoaTimKiem);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Book(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getDouble(10),
                        rs.getInt(11),
                        rs.getInt(12)
                ));

            }
        } catch (Exception e) {
        }

        return list;
    }
public List<Book> selectOrderBy(String condition, String order) throws SQLException {
        String sql = "select * from Book order by" + " " + condition + " " + order;
        List<Book> bookList = new ArrayList<>();
        ResultSet resultSet = dbContext.exeQuery(sql);

        while (resultSet.next()) {
            bookList.add(new Book(resultSet.getInt("bookID"), resultSet.getString("bookTitle"), resultSet.getString("bookCover"),
                    resultSet.getString("bookVersion"), resultSet.getString("publisherID"), resultSet.getDate("bookPublishDate"),
                    resultSet.getDate("bookImportDate"), resultSet.getString("bookIntro"), resultSet.getInt("bookQuantity"),
                    resultSet.getDouble("bookPrice"), resultSet.getInt("bookDiscount"), resultSet.getInt("bookFlashSale")));
        }
        return bookList;

    }

    public List<Book> selectBestSeller() throws SQLException {
        String sql = "SELECT s.bookID, bookTitle, bookCover, bookQuantity, bookPrice, bookDiscount, SUM(soldQuantity) AS totalSales\n"
                + "FROM SaleHistory s\n"
                + "join Book b\n"
                + "on b.bookID = s.bookID\n"
                + "WHERE saleDate between getdate() - 30 and getdate()\n"
                + "GROUP BY s.bookID, bookTitle, bookCover, bookQuantity, bookPrice, bookDiscount\n"
                + "ORDER BY totalSales DESC";
        System.out.println(sql);
        List<Book> bookList = new ArrayList<>();
        ResultSet resultSet = dbContext.exeQuery(sql);

        while (resultSet.next()) {
            bookList.add(new Book(resultSet.getInt("bookID"), resultSet.getString("bookTitle"), resultSet.getString("bookCover"),
                    resultSet.getInt("bookQuantity"), resultSet.getDouble("bookPrice"), resultSet.getInt("bookDiscount"), resultSet.getInt("totalSales")));
        }
        return bookList;

    }
}
  
  









    
        
    
    
    

