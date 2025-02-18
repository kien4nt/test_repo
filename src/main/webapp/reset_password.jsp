<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đổi mật khẩu</title>
        <link rel="stylesheet" href="css/styleLogin.css">
    </head>
    <body>
    <div class="container">
        <h2>Đổi mật khẩu</h2>
        <form action="resetPassword" method="POST">
            <p style="color: red;">${ERROR}</p>

            <label for="old_password">Mật khẩu cũ</label>
            <input type="password" id="old_password" name="old_password" required>

            <label for="new_password">Mật khẩu mới</label>
            <input type="password" id="new_password" name="new_password" required>

            <label for="confirm_new_password">Xác thực mật khẩu mới</label>
            <input type="password" id="confirm_new_password" name="confirm_new_password" required>

            <button type="submit" class="submit-btn">Đổi mật khẩu</button>
        </form>
        <div id="success-message" class="success-message" style="display: none;">
            Đổi mật khẩu thành công
            <a href="login.jsp" class="action-link">Đăng nhập</a>
        </div>
    </div>
</body>
</html>
