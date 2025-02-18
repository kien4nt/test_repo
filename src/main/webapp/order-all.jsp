<%-- 
    Document   : checkOut
    Created on : Oct 24, 2024, 10:11:30 AM
    Author     : anhkc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Table</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <c:import url="header.jsp"/>
<h2>Order Information</h2>

<table>
    <thead>
        <tr>
            <th>Order ID</th>
            <th>Order Date</th>
            <th>Delivery Address</th>
            <th>Payment Method ID</th>
            <th>Delivery Option ID</th>
            <th>Order Total Amount</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="order" items="${orderList}">
            
        <tr>
            <td>${order.orderID}</td>
            <td>${order.orderDate}</td>
            <td>${order.deliveryAddress}</td>
            <td>${order.paymentMethodID}</td>
            <td>${order.deliveryOptionID}</td>
            <td>${order.orderTotalAmount}</td>
        </tr>
        </c:forEach>
        
    </tbody>
</table>

    <c:import url="footer.jsp"/>
</body>
</html>

