<%-- 
    Document   : order
    Created on : Oct 20, 2024, 12:35:29 PM
    Author     : TRUNG NHAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styleForPayment.css"/>
        <title>Thanh toán</title>
    </head>
    <body>
        <div class="title-custom">
            <h1>Thanh toán</h1>
        </div>
        <div class="title-custom">
            <p>Vui lòng kiểm tra thông tin Khách hàng, thông tin Giỏ hàng trước khi đặt hàng</p>
        </div>
        <div class="content">
            <div class="left-content" style="width: 60%">
                <h2>Thông tin giao hàng</h2> <hr>
                <form action="payment" method="POST"">
                    <div class="input-custom">
                        <label for="name">Họ tên</label><br>
                        <input type="text" name="name" id="name" required/>
                    </div>
                    <div class="input-custom">
                        <label for="addr">Địa chỉ</label><br>
                        <input type="text" name="addr" id="addr" value="" required/>
                    </div>
                    <div class="input-custom">
                        <label for="phone">Điện thoại</label><br>
                        <input type="tel" name="phone" id="phone" required />
                    </div>
                    <div class="input-custom">
                        <label for="email">Email</label><br>
                        <input type="text" name="email" id="email" required/>
                    </div>
                    <div class="payment-custom">
                        <h2>Phương thức thanh toán</h2>
                        <label><input type="radio" name="paymentMethod" value="PAY001" /> Chuyển khoản</label><br>
                        <label><input type="radio" name="paymentMethod" value="PAY002" /> Ship COD</label><br>  
                    </div> <hr>
                    <div class="payment-method">
                        <h2>Phương thức vận chuyển</h2>
                        <label><input type="radio" name="deliveryOption" value="DEL001" /> Giao hàng nhanh</label><br>
                        <label><input type="radio" name="deliveryOption" value="DEL002" /> Giao hàng tiết kiệm</label><br>  
                    </div> <hr>
                    <button class="back" onclick="history.back()">Quay lại Giỏ hàng</button>
                    <button type="submit">Đặt hàng</button>
                </form>
            </div>
            <div class="right-content" style="width: 40%">
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
    </body>
</html>
