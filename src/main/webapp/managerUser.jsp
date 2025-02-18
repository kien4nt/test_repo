<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Quản lý người dùng</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f5f7fa;
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
            }
            .container {
                width: 80%;
                margin: 20px auto;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                padding: 20px;
            }
            .user-table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 10px;
                font-size: 16px;
            }
            .user-table th, .user-table td {
                padding: 12px 15px;
                text-align: center;
                border-bottom: 1px solid #ddd;
            }
            .edit-btn, .deactivate-btn, .activate-btn {
                padding: 8px 12px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                color: #fff;
                font-size: 14px;
                margin: 0 2px;
            }
            .edit-btn {
                background-color: #3498db;
            }
            .deactivate-btn {
                background-color: #e74c3c;
            }
            .activate-btn {
                background-color: #2ecc71;
            }
            .edit-btn:hover {
                background-color: #2980b9;
            }
            .deactivate-btn:hover {
                background-color: #c0392b;
            }
            .activate-btn:hover {
                background-color: #27ae60;
            }
            .text-muted {
                color: #aaa;
            }

            /* CSS cho popup */
            .popup {
                display: none;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background: rgba(0, 0, 0, 0.5);
                justify-content: center;
                align-items: center;
                z-index: 1000;
            }
            .popup-content {
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                width: 300px;
                text-align: center;
            }
            .form-group {
                margin-bottom: 15px;
                text-align: left;
            }
            .form-control {
                width: 100%;
                padding: 8px;
                border-radius: 4px;
                border: 1px solid #ccc;
                margin-top: 5px;
            }
            .form-actions {
                display: flex;
                justify-content: space-between;
            }
            .btn {
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 14px;
            }
            .save-btn {
                background-color: #007bff;
                color: #fff;
            }
            .cancel-btn {
                background-color: #6c757d;
                color: #fff;
            }
            .btn:hover {
                opacity: 0.9;
            }
        </style>
    </head>
    <body>
        <c:set var="adminRole" value="ROL001"/>
        <div class="container">
            <h2>Quản lý người dùng</h2>
            <table class="user-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tên đăng nhập</th>
                        <th>Vai trò</th>
                        <th>Trạng thái</th>
                        <th>Ngày đăng ký</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${accounts}">
                        <tr>
                            <td>${user.accountId}</td>
                            <td>${user.username}</td>
                            <td>${user.roleId}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${user.status == 1}">
                                        Đang hoạt động
                                    </c:when>
                                    <c:otherwise>
                                        Ngừng hoạt động
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${user.registrationDate}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${loggedInUser.accountId != user.accountId && user.roleId != 'ROL001'}">
                                        <button class="edit-btn" onclick="openPopup('${user.accountId}', '${user.username}', '${user.roleId}', '${user.status}')">Chỉnh sửa</button>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="text-muted">Không thể chỉnh sửa</span>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${user.status == 1}">
                                        <c:choose>
                                            <c:when test="${loggedInUser.accountId != user.accountId && user.roleId != 'ROL001'}">
                                                <form action="deactivateUser" method="post" style="display: inline;">
                                                    <input type="hidden" name="accountId" value="${user.accountId}" />
                                                    <a href="deleteUser?accountId=${user.accountId}&status=0" class="deactivate-btn" 
                                                       onclick="this.closest('form').submit(); return confirm('Bạn có chắc chắn muốn vô hiệu hóa người dùng này không?');">Vô hiệu hóa</a>
                                                </form>
                                            </c:when>
                                            <c:when test ="${loggedInUser.accountId == user.accountId || user.roleId == 'ROL001'}">
                                                <span class="text-muted">Không thể vô hiệu hóa</span>
                                            </c:when>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <form action="activateUser" method="post" style="display: inline;">
                                            <input type="hidden" name="accountId" value="${user.accountId}" />
                                            <a href="deleteUser?accountId=${user.accountId}&status=1" class="activate-btn" 
                                               onclick="this.closest('form').submit(); return confirm('Bạn có chắc chắn muốn kích hoạt người dùng này không?');">Kích hoạt</a>
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Popup chỉnh sửa -->
        <div id="editPopup" class="popup">
            <div class="popup-content">
                <h3>Chỉnh sửa người dùng</h3>
                <form action="editUser" method="post">
                    <input type="hidden" name="accountId" id="editAccountId" />

                    <div class="form-group">
                        <label for="editUsername">Tên đăng nhập:</label>
                        <input type="text" id="editUsername" name="username" class="form-control" required />
                    </div>

                    <div class="form-group">
                        <label for="editRoleId">Vai trò:</label>
                        <select id="editRoleId" name="roleId" class="form-control" required>
                            <option value="ROL001">ROL001</option>
                            <option value="ROL003">ROL003</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="editStatus">Trạng thái:</label>
                        <select id="editStatus" name="status" class="form-control" required>
                            <option value="1">Hoạt động</option>
                            <option value="0">Ngưng hoạt động</option>
                        </select>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn save-btn">Lưu thay đổi</button>
                        <button type="button" class="btn cancel-btn" onclick="closePopup()">Đóng</button>
                    </div>
                </form>
            </div>
        </div>

        <!-- JavaScript mở và đóng popup -->
        <script>
            function openPopup(accountId, username, roleId, status) {
                document.getElementById('editAccountId').value = accountId;
                document.getElementById('editUsername').value = username;
                document.getElementById('editRoleId').value = roleId;
                document.getElementById('editStatus').value = status;
                document.getElementById('editPopup').style.display = 'flex';
            }

            function closePopup() {
                document.getElementById('editPopup').style.display = 'none';
            }
        </script>
    </body>
</html>
