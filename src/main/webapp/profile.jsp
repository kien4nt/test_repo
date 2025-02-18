<%-- 
    Document   : index
    Created on : Oct 13, 2024, 10:18:34 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style><%@include file="css/index.css"%></style>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <meta charshet="utf-8"/>
        
    </head>
    <body>
        <c:import url="header.jsp"/>
       <div class="order-table">
        <table>
            <tr>
                <th>Mã đơn hàng</th>
                <th>Ngày đặt hàng</th>
                <th>Trạng thái thanh toán</th>
                <th>Vận chuyển</th>
                <th>Tổng tiền</th>
            </tr>    
                <c:forEach var="bill" items="${requestScope.listBill}">
                    <tr>  
                    <td>${bill.orderID}</td>
                    <td>${bill.paymentStatusID}</td>
                    <td>${bill.deliveryStatusID}</td>
                    <td>${bill.billTotalAmount}</td>
                    <td>${bill.billDate}</td>
                    </tr>
                </c:forEach>
                    
        </table>
    </div>
    
    <div class="shipping-info">
        <h2>Thông tin giao hàng</h2>
        <p>Tên đầy đủ: <span id="fullName"></span></p>
        <p>Tên đăng nhập: <span id="username"></span></p>
        <p>Số nhà - tên đường: <span id="address"></span></p>
        <p>Phường - quận huyện - thành phố - quốc gia: <span id="cityCountry"></span></p>
        <p>Số điện thoại: <span id="phoneNumber"></span></p>
    </div>

        <button onclick="window.location.href='AddressServlet'">Xem địa chi </button>
    <c:import url="footer.jsp"/>    
    </body>
</html>
