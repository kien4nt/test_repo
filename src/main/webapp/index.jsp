<%-- 
    Document   : index
    Created on : Oct 29, 2024, 9:58:58 AM
    Author     : anhkc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome page</title>
    </head>
    <body>
        <h1>Welcome!</h1>
        <%response.sendRedirect("home");%>
    </body>
</html>
