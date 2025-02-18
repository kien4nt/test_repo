<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit Product</title>
    <link rel="stylesheet" href="css/edit.css">
</head>
<body>

    
        <div class="header-child">
            <a href="ListServlet">Products</a>
        </div>
    </div>

    <div class="title">
        <h2>Edit Product</h2>
    </div>

    <div class="form-container">
        <c:set var="product" value="${requestScope.product}"/>
        <form action="Edit" method="post" id="f1" class="edit-form">
            <table>
                <tr>
                    <td><label for="txtID">ID</label></td>
                    <td><input value="${product.bookID}" id="txtID" type="text" name="txtID" readonly></td>
                </tr>
                <tr>
                    <td><label for="txtTitle">Title</label></td>
                    <td><input value="${product.bookTitle}" id="txtTitle" type="text" name="txtTitle" required></td>
                </tr>
                <tr>
                    <td><label for="txtCover">Book Cover URL</label></td>
                    <td><input value="${product.bookCover}" id="txtCover" type="text" name="txtCover"></td>
                </tr>
                <tr>
                    <td><label for="txtVersion">Book Version</label></td>
                    <td><input value="${product.bookVersion}" id="txtVersion" type="text" name="txtVersion" required></td>
                </tr>
                <tr>
                    <td><label for="txtPublisherID">Publisher ID</label></td>
                    <td><input value="${product.publisherID}" id="txtPublisherID" type="text" name="txtPublisherID" required></td>
                </tr>
                <tr>
                    <td><label for="txtPublishDate">Publish Date</label></td>
                    <td><input value="${product.bookPublishDate}" id="txtPublishDate" type="date" name="txtPublishDate" required></td>
                </tr>
                <tr>
                    <td><label for="txtImportDate">Import Date</label></td>
                    <td><input value="${product.bookImportDate}" id="txtImportDate" type="date" name="txtImportDate" required></td>
                </tr>
                <tr>
                    <td><label for="txtIntro">Introduction</label></td>
                    <td><textarea id="txtIntro" name="txtIntro" required>${product.bookIntro}</textarea></td>
                </tr>
                <tr>
                    <td><label for="txtQuantity">Quantity</label></td>
                    <td><input value="${product.bookQuantity}" id="txtQuantity" type="number" name="txtQuantity" required></td>
                </tr>
                <tr>
                    <td><label for="txtPrice">Price</label></td>
                    <td><input value="${product.bookPrice}" id="txtPrice" type="number" name="txtPrice" required></td>
                </tr>
                <tr>
                    <td><label for="txtDiscount">Discount</label></td>
                    <td><input value="${product.bookDiscount}" id="txtDiscount" type="number" name="txtDiscount" required></td>
                </tr>
                 <tr>
                    <td><label for="txtFlashsale">Flashsale</label></td>
                    <td><input value="${product.bookFlashSale}" id="txtFlashsale" type="number" name="txtFlashsale" required></td>
                </tr>
            </table>
            <div class="button-group">
                <button class="btn btn-primary" type="submit" name="btnSave" value="Save" form="f1">Save</button>
                <a class="btn btn-danger" href="ListServlet">Back to List</a>
            </div>
        </form>
    </div>

</body>
</html>
