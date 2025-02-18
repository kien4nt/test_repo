<%-- 
    Document   : updateOrderForm
    Created on : Nov 3, 2024, 8:31:21 PM
    Author     : TRUNG NHAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <style>
            body {
                width: 400px;
                margin: 0 auto;
               
            }
            form {
                width:100%;
                display: flex;
                flex-direction: column;
                gap: 5px;
                 
            }
        </style>
        <c:set var="c" value="${requestScope.order}"/>
        <h1>Order update</h1>
        <form action="update" method="post" id="orderForm"> <!--URL and ID according to the question-->
            <table>
                <!-- Order ID (Read-only) -->
                <tr>
                    <td><label for="orderID">Order ID</label></td>
                    <td><input value="${c.orderID}" id="orderID" type="text" name="orderID" readonly></td>
                </tr>

                <!-- Order Date (Read-only, for display only) -->
                <tr>
                    <td><label for="orderDate">Order Date</label></td>
                    <td><input value="${c.orderDate}" id="orderDate" type="date" name="orderDate" readonly></td>
                </tr>

                <!-- Delivery Address -->
                <tr>
                    <td><label for="deliveryAddress">Delivery Address</label></td>
                    <td><input value="${c.deliveryAddress}" id="deliveryAddress" type="text" name="deliveryAddress" required></td>
                </tr>

                <!-- Payment Method (Select) -->
                <tr>
                    <td><label for="paymentMethod">Payment Method</label></td>
                    <td>
                        <select id="paymentMethod" name="paymentMethod" required>
                            <c:forEach var="p" items="${requestScope.paymentList}">
                                <c:choose>
                                    <c:when test="${p.paymentMethodID==c.paymentMethodID}">
                                        <option value="${c.paymentMethodID}" selected>${p.methodName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${p.paymentMethodID}" >${p.methodName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>

                <!-- Delivery Option (Select) -->
                <tr>
                    <td><label for="deliveryOption">Delivery Option</label></td>
                    <td>
                        <select id="deliveryOption" name="deliveryOption" required>
                            <c:forEach var="d" items="${requestScope.deliveryList}">
                                <c:choose>
                                    <c:when test="${d.deliveryOptionID==c.deliveryOptionID}">
                                        <option value="${d.deliveryOptionID}" selected>${d.optionName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${d.deliveryOptionID}" >${d.optionName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>

                <!-- Order Total Amount (Read-only) -->
                <tr>
                    <td><label for="orderTotalAmount">Order Total Amount</label></td>
                    <td><input value="${c.orderTotalAmount}" id="orderTotalAmount" type="number" name="orderTotalAmount" readonly></td>
                </tr>

                <!-- Customer ID (Read-only, for display only) -->
                <tr>
                    <td><label for="customerID">Customer ID</label></td>
                    <td><input value="${c.customerID}" id="customerID" type="text" name="customerID" readonly></td>
                </tr>

                <!-- Payment Status (Select) -->
                <tr>
                    <td><label for="paymentStatus">Payment Status</label></td>
                    <td>
                        <select id="paymentStatus" name="paymentStatus" required>
                            <c:forEach var="ps" items="${requestScope.paymentStatusList}">
                                <c:choose>
                                    <c:when test="${ps.paymentStatusID==c.paymentStatusID}">
                                        <option value="${ps.paymentStatusID}" selected>${ps.statusDescription}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${ps.paymentStatusID}" >${ps.statusDescription}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                           
                        </select>
                    </td>
                </tr>

                <!-- Delivery Status (Select) -->
                <tr>
                    <td><label for="deliveryStatus">Delivery Status</label></td>
                    <td>
                        <select id="deliveryStatus" name="deliveryStatus" required>
                             <c:forEach var="ds" items="${requestScope.deliveryStatuslist}">
                                <c:choose>
                                    <c:when test="${ds.deliveryStatusID==c.deliveryStatusID}">
                                        <option value="${ds.deliveryStatusID}" selected>${ds.statusDescription}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${ds.deliveryStatusID}" >${ds.statusDescription}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                        <button type="submit">Submit Order</button>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
