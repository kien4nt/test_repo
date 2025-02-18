<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đăng nhập</title>
        <link rel="stylesheet" href="css/styleLogin.css">
        
    </head>
    <body>
        <div class="container">

            <h2>Đăng nhập</h2>
            <form action="login" method="POST">
                <p style="color: red;">${ERROR}</p>
                <label for="username">Tên đăng nhập</label>
                <input type="text" id="username" name="username" required>

                <label for="password">Mật khẩu</label>
                <input type="password" id="password" name="password" required>

                <button type="submit" class="submit-btn">Đăng nhập</button>
                <a href="/verifyPassword" class="action-link">Quên mật khẩu</a>
                <a href="register.jsp" class="action-link">Đăng ký</a>
                <a href="home" class="action-link">Về trang chủ</a>
            </form>

        </div>
    </body>
</html>
