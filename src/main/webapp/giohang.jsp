<%-- 
    Document   : newjsp
    Created on : Oct 27, 2024, 4:09:12 PM
    Author     : anhkc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

    <head>
        <title>ICHIBOOKS| Cart</title>
        <!-- Required meta tags -->
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

        <!-- Bootstrap CSS v5.2.1 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous" />
        <link rel="stylesheet" href="css\styleCart.css">

    </head>

    <body>
        <c:import url="header.jsp"/>
        <c:choose>
            <c:when test="${cart == null || cart.itemList == null || cart.itemList.isEmpty()}">
                <div class="cart-container">
                    <h2>GIỎ HÀNG</h2>
                    <div>
                        <p style="text-align:center">Không có sản phẩm trong giỏ hàng</p>

                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="cart-container">
                    <h2>GIỎ HÀNG</h2>

                    <form action="cart" method="post" id="cart-form">
                        <table>
                            <tbody>
                                <c:forEach var="item" items="${sessionScope.cart.itemList}" varStatus="status">
                                    <tr>
                                        <td>
                                            <input type="hidden" name="pid" value="${item.getBook().getBookID()}"/>
                                            <img src="${item.book.bookCover}" alt="Hình bìa ${item.book.bookTitle}" class="img-fluid" style="max-width: 80px;">
                                        </td>
                                        <td><strong>${item.book.bookTitle}</strong><br/><span>${item.book.bookVersion}</span></td>
                                        <td>
                                            <!-- Add a unique name for each quantity field using an index -->
                                            <input type="number" name="quantity" value="${item.quantity}" min="1" required/>
                                        </td>
                                        <td>
                                            ${item.finalPrice}
                                        </td>
                                        <td>
                                            <input type="checkbox" name="deletepid" value="${item.getBook().getBookID()}"/>
                                        </td>
                                    </tr>
                                </c:forEach>

                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td>
                                        <div class="total">
                                            <strong>Tổng cộng:</strong>
                                        </div>
                                    </td>
                                    <td>${sessionScope.cart.totalAmount} VNÐ</td>
                                    <td></td>
                                </tr>
                            </tbody>
                        </table>

                        <div class="pay-custom clear-fix">
                            <button type="submit" value="delete" name="action" form="cart-form">Xóa mục đã chọn</button>
                            <button type="submit" value="update" name="action" form="cart-form">Cập nhật</button>
                            <button type="submit" value="pay" name="action" form="cart-form">Thanh toán</button>
                        </div>
                    </form>

                </div>

            </c:otherwise>
        </c:choose> 
        <div class="quick-link">
            <div class="container">
                <a href="SanPhamcontrol">Tiếp tục mua hàng</a>

            </div>
        </div>

        <c:import url="footer.jsp"/>

        <!-- Bootstrap JavaScript Libraries -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
                integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>
    </body>

</html>
