<%-- 
    Document   : edit
    Created on : Nov 3, 2024, 10:40:01 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style><%@include file="css/address.css"%></style>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <meta charshet="utf-8"/>
        <script src="js/address.js"></script>
        <style>
        body {
            padding: 20px;
        }
    </style>
    </head>
    <body>
       <c:import url="header.jsp"/>
       <h1>Chỉnh sửa địa chỉ</h1>
        <c:set var="customer" value="${requestScope.customer}" />
            <form action="EditServlet" method="post">           
                <input type="hidden" name="customerID" value="${customer.customerID}"/>
                <input type="hidden" name="addressID" value="${address.addressID}"/> 
        <div class="form-group">        
            <label for="firstName">Họ:</label>
            <input type="text" class="form-control" id="firstName" name="firstName" value="${customer.firstName}" required>
        </div>
        
        <div class="form-group">
            <label for="lastName">Tên:</label>
            <input type="text" class="form-control" id="lastName" name="lastName" value="${customer.lastName}" required>
        </div>
        
        <div class="form-group">
            <label for="addressDetail">Địa chỉ:</label>
            <input type="text" class="form-control" id="addressDetail" name="addressDetail" value="${address.addressDetail}" required>
        </div>
        
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" name="email" value="${customer.email}" required>
        </div>
        
        <div class="form-group">
            <label for="phoneNumber">Số điện thoại:</label>
            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${customer.phoneNumber}" required>
        </div>
        
        <div class="form-group">
            <label for="birthDate">Ngày sinh:</label>
            <input type="date" class="form-control" id="birthDate" name="birthDate" value="${customer.birthDate}" required>
        </div>
        
        <div class="form-check">
            <input type="checkbox" class="form-check-input" id="defaultAddress" name="defaultAddress" <c:if test="${address.defaultAddress}">checked</c:if>>
            <label class="form-check-label" for="defaultAddress">Địa chỉ mặc định</label>
        </div>
            
            <br>
                <button type="submit" class="btn btn-success">Lưu</button>
                <a href="AddressServlet" class="btn btn-secondary">Hủy</a>
            </form>
        <c:import url="footer.jsp"/>
        
    </body>
</html>
