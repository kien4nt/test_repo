<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- forgot_password.html -->
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quên mật khẩu</title>
    <link rel="stylesheet" href="css/styleLogin.css">
</head>
<body>
    <div class="container">
        <h2>Quên mật khẩu</h2>
        <form action="verifyPassword" method="POST">
            <p style="color: red;">${ERROR}</p>
               <label for="username">Tên đăng nhập</label>
               <input value="${sessionScope.LOGIN_USER.getUsername()}" type="text" id="username" name="username" required>
            <label for="verification_code">Nhập mã xác thực</label>
            <input type="password" id="verification_code" name="verification_code" required>

            <button type="submit" class="submit-btn">Gửi</button>
            <a href="login.jsp" class="action-link">Hủy</a>
        </form>
    </div>
</body>
</html>

