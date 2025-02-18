<%-- 
    Document   : paymentSuccessful
    Created on : Oct 29, 2024, 7:08:45 PM
    Author     : TRUNG NHAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="css/styleForCustomInfor.css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>COD method</title>
    </head>
    <body>
        <h1>COD View</h1>
        <div class="payment-content">
            <div class="pm-right">
                 <div class="success-message">
                    <span class="check-icon">&#10003;</span> <!-- Unicode checkmark symbol -->
                    <div class="message-content">
                        <p><strong>Đặt hàng thành công</strong></p>
                        <p>Mã đơn hàng .....</p>
                        <p>Cảm ơn bạn đã mua hàng!</p>
                    </div>
                </div>
                <c:set value="${requestScope.cusinfo}" var="si"/>
                <div class="cus-info">
                   <h3>Thông tin khách hàng</h3>
                    <p>Tên: ${si.name}</p>
                    <p>Số điện thoại: ${si.phone}</p>
                    <p>Email: ${si.email}</p>
                    <p>Địa chỉ: ${si.address}</p>
                    <p>Phương thức thanh toán: ${si.address}</p>
                </div>      
            </div>

            <div class="pm-left">
                <div class="cus-info">
                    <div class="product-item">
                        <span class="product-infor"><img src="src" alt="alt"/></span>
                        <span class="product-infor">titlehere</span>
                        <span class="product-price">price here</span>
                    </div>
                    <hr>
                    <div class="price">
                        <div class="price-custom">
                            <p>Tạm tính</p>
                            <p>Phí vận chuyển</p>
                        </div><hr>
                    </div>
                    <div class="total-price"><strong>Tổng cộng</strong></div>  
                </div>
            </div>
        </div>
        <div class="bottom-content">
            <button class="back" onclick="history.back()">Quay lại</button>
            <a href="url">Tiếp tục mua hàng</a>
        </div>


    </body>
</html>
