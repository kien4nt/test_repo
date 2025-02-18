<%-- 
    Document   : orderList
    Created on : Oct 24, 2024, 9:04:36 AM
    Author     : TRUNG NHAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="css/style_1.css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OrderList page</title>
    </head>
    <body>
        <div class="order-head">
            <h1>DANH SÁCH ĐƠN HÀNG</h1>
        </div>       
        <div class="order">
            <c:forEach items="${requestScope.list}" var="c"> 
                <c:set var="id" value="${c.orderID}"/>
                <div class="order-custom">
                    <p><strong>Mã đơn:</strong> ${id}</p>
                    <p><strong>Khách hàng:</strong> ${c.customerID}</p>
                    <p><strong>Ngày đặt:</strong> ${c.orderDate}</p>
                    <p><strong>Tổng tiền:</strong> ${c.orderTotalAmount}</p>
                    <p><strong>Phương thức thanh toán:</strong> 
                        <c:forEach items="${requestScope.paymentList}" var="p">
                            <c:if test="${p.paymentMethodID==c.paymentMethodID}">
                                <span>${p.methodName}</span>  
                            </c:if>
                        </c:forEach>

                    </p>
                    <p><strong>Phương thức vận chuyển:</strong>
                        <c:forEach items="${requestScope.deliveryList}" var="d">
                            <c:if test="${d.deliveryOptionID==c.deliveryOptionID}">
                                <span>${d.optionName}</span>  
                            </c:if>
                        </c:forEach>

                    </p>
                    <p><strong>Trạng thái:</strong> Đang xử lý</p>
                    <div class="action">
                        <a href="updateOrder?id=${id}" class="action-custom upd"><button class="action-custom upd">Cập nhật</button></a>
                        <a href="deleteOrder?id=${id}" class="action-custom del" onclick="return confirm('Are you sure you want to delete item with id = ${id}?')"><button class="action-custom del">Xóa</button></a>
                    </div>
                    <div class="view-detail">
                        <a href="orderDetail?id=${id}" class="view-detail-btn"><button class="view-detail-btn">Xem chi tiết</button></a>
                    </div>                  
                </div>            
            </c:forEach>
        </div>
    </body>
</html>
