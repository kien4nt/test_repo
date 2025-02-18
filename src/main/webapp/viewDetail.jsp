<%-- 
    Document   : viewDetail
    Created on : Nov 4, 2024, 10:51:39 AM
    Author     : TRUNG NHAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styleForOrderDetail.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Chi tiết đơn hàng</h1>
        <table border="0">
             <tr>
                    <th>Bìa</th>
                    <th>Tên sách</th>
                    <th>Số lượng</th>
                    <th>Giá</th>
                </tr>
            <c:forEach var="o" items="${requestScope.olist}">
                <tr>
                    <td>
                        <img src="${o.getBook().getBookCover()}" alt="Hình bìa ${o.getBook().getBookTitle()}"/>
                    </td>
                    <td>
                        <div>
                            <strong>
                                <strong>${o.getBook().getBookTitle()}</strong><br/><span>${o.getBook().getBookVersion()}</span>
                            </strong>
                        </div>
                    </td>
                    <td>${o.quantity}</td>
                    <td>${o.finalPrice} VNĐ</td>
                </tr>
            </c:forEach>
        </table>
         <button class="back" onclick="history.back()">Quay lại</button>
    </body>
</html>
