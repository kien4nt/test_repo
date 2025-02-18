<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List of Products</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
 
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="home">Home</a>
    </nav>
    
    <div class="container mt-5">
        <div class="row">
            <div class="col text-center">
                <h1>List of Products</h1>
            </div>
        </div>
        <div class="row my-3">
            <div class="col text-right">
                <a href="Create" class="btn btn-primary">Add New</a>
            </div>
        </div>

        <div class="table-responsive">
            <table class="table table-striped table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>BookID</th>
                        <th>BookTitle</th>
                        <th>BookVersion</th>
                        <th>PublisherID</th>
                        <th>Publish Date</th>
                        <th>Import Date</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Discount</th>
                        <th>Flashsale</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${productList}">
                        <c:set var="id" value="${product.bookID}" />
                        <tr>
                            <td>${product.bookID}</td>
                            <td>${product.bookTitle}</td>
                            <td>${product.bookVersion}</td>
                            <td>${product.publisherID}</td>
                            <td>${product.bookPublishDate}</td>
                            <td>${product.bookImportDate}</td>
                            <td>${product.bookQuantity}</td>
                            <td>${product.bookPrice}</td>
                            <td>${product.bookDiscount}</td>
                            <td>${product.bookFlashSale}</td>
                            <td>
                                <div class="btn-group" role="group">
                                    <a class="btn btn-success" href="Edit?id=${id}">Edit</a>
                                    <a class="btn btn-danger" href="Delete?id=${id}">Delete</a>
                                    <a class="btn btn-info" href="detail?pid=${id}">View</a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>