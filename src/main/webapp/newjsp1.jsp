<%-- 
    Document   : header
    Created on : Oct 12, 2024, 4:23:03 PM
    Author     : anhkc
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
        <script src="https://kit.fontawesome.com/bfab6e6450.js" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous" />
        <link rel="stylesheet" href="css\style.css">
        <link rel="stylesheet" href="css\sanpham.css">
    </head>
    <body>
        <c:import url="header.jsp"/>
        <c:set var="thamsosapxep" value="${sessionScope.thamsosapxep.toString()}" />
        <div class="container">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item">
                        <a href="#">Trang chủ</a>
                    </li>
                    <li class="breadcrumb-item">
                        <a href="http://localhost:8080/SanPhamcontrol">Danh mục</a> <!-- Update this link -->
                    </li>
                    <li aria-current="page" class="breadcrumb-item active">
                        Tất cả sản phẩm
                    </li>
                </ol>
            </nav>
            <div class="row">
                <div class="col-md-3">
                    <h4>DANH MỤC</h4>
                    <ul class="category-list">
                        <c:set var="count" value="0" />
                        <c:forEach items="${listC}" var="o">
                            <c:if test="${count < 7}">
                                <li>
                                    <a href="Phanloai?phanloai=${o.categoryID}">${o.categoryName}</a>
                                </li>
                                <c:set var="count" value="${count + 1}" />
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>
                <div class="col-md-9">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h4>TẤT CẢ SẢN PHẨM</h4>
                        <div class="sort-by">
                            <form action="SanPhamcontrol" method="post"id="sortform">
                            <label for="sort">Sắp xếp theo:</label>
                            <select class="form-select" id="sort" name="sort"onchange="navigateToPage()">
                                <option value="newest">Mới nhất</option>
                                <option value="bestselling">Bán chạy</option>
                                <option value="hotdeal">Hot Deal</option>
                                <option value="a-z">A-Z</option>
                            </select>
                            </form>
                        </div>
                    </div>
                    
                    <c:choose>
                    <c:when test="${requestScope.listP != null && requestScope.booksOfThisPage == null}">
                    <!--<!-- TAT CA SAN PHAM -->
                        <div class="row">
                            <c:forEach var="book" items="${requestScope.listP}" >

                                <div class="product-container col-md-3">
                                    <div class="cover-container">
                                        <div class="discount">
                                            <span class="discount-amount">${book.bookDiscount}%</span>
                                        </div>
                                        <div class="cover">
                                            <a style="width:100%" href="detail?pid=${book.bookID}" title="${book.bookTitle}">
                                                <img style="width:100%;height: 77%" class="cover-image" src="${book.bookCover}"
                                                     alt="Hình bìa ${book.bookTitle}" />
                                            </a>

                                            <c:if test="${book.bookQuantity == 0}">
                                                <div class="out-of-stock">
                                                    Hết hàng
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="info-container">
                                        <h3 class="product-title">
                                            <a href="#" title="${book.bookTitle}">
                                                ${book.bookTitle}
                                            </a>
                                        </h3>
                                        <div class="product-price">
                                            <p class="product-price-discounted">${book.bookPrice*(100-book.bookDiscount)/100}
                                                VNÐ
                                            </p>
                                            <p class="product-price-original">
                                                <del>${book.bookPrice} VNÐ</del>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <ul class="pagination">
                            <c:if test="${requestScope.currentPage > 1}">


                                <li><a href="SanPhamcontrol?page=${requestScope.currentPage - 1}">Previous</a></li>
                                </c:if>

                            <!-- Page number links -->
                            <c:forEach var="i" begin="1" end="${requestScope.totalPages}">
                                <c:choose>
                                    <c:when test="${i == requestScope.currentPage}">
                                        <!-- Highlight the current page number -->
                                        <li><span>${i}</span></li>
                                            </c:when>
                                            <c:otherwise>
                                        <li><a href="SanPhamcontrol?page=${i}">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>

                            <!-- Next button (shown if not on the last page) -->
                            <c:if test="${requestScope.currentPage < requestScope.totalPages}">
                                <li><a href="SanPhamcontrol?page=${requestScope.currentPage + 1}">Next</a></li>
                                </c:if>
                        </ul>
                    </c:when>

                    <c:when test="${requestScope.listP == null && requestScope.booksOfThisPage != null}">
                    <!--<!-- PHAN LOAI THEO DANH MUC -->
                        <div class="row">
                            <c:forEach var="book" items="${requestScope.booksOfThisPage}" >
                                <div class="product-container col-md-3">
                                    <div class="cover-container">
                                        <div class="discount">
                                            <span class="discount-amount">${book.bookDiscount}%</span>
                                        </div>
                                        <div class="cover">
                                            <a style="width:100%" href="detail?pid=${book.bookID}" title="${book.bookTitle}">
                                                <img style="width:100%;height: 77%" class="cover-image" src="${book.bookCover}"
                                                     alt="Hình bìa ${book.bookTitle}" />
                                            </a>

                                            <c:if test="${book.bookQuantity == 0}">
                                                <div class="out-of-stock">
                                                    Hết hàng
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="info-container">
                                        <h3 class="product-title">
                                            <a href="#" title="${book.bookTitle}">
                                                ${book.bookTitle}
                                            </a>
                                        </h3>
                                        <div class="product-price">
                                            <c:if test="${book.bookFlashSale != 0}">
                                                <p class="product-price-discounted">${book.bookPrice*(100-book.bookFlashSale)/100}
                                                    VNÐ
                                                </p>

                                            </c:if>
                                            <c:if test="${book.bookFlashSale == 0}">

                                                <p class="product-price-discounted">${book.bookPrice*(100-book.bookDiscount)/100}
                                                    VNÐ
                                                </p>
                                            </c:if>
                                            <p class="product-price-original">
                                                <del>${book.bookPrice} VNÐ</del>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <ul class="pagination">
                            <c:if test="${requestScope.currentPage > 1}">
                                <li><a href="Phanloai?page=${requestScope.currentPage - 1}">Previous</a></li>
                                </c:if>

                            <!-- Page number links -->
                            <c:forEach var="i" begin="1" end="${sessionScope.totalPages}">
                                <c:choose>
                                    <c:when test="${i == requestScope.currentPage}">
                                        <!-- Highlight the current page number -->
                                        <li><span>${i}</span></li>
                                            </c:when>
                                            <c:otherwise>
                                        <li><a href="Phanloai?page=${i}">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>

                            <!-- Next button (shown if not on the last page) -->
                            <c:if test="${requestScope.currentPage < sessionScope.totalPages}">
                                <li><a href="Phanloai?page=${requestScope.currentPage + 1}">Next</a></li>
                                </c:if>                                
                        </ul>
                    </c:when>
                    </c:choose>
                </div>
                <div>



                </div>
            </div>
        </div>
        <div class="scroll-to-top">
            <i class="fas fa-arrow-up">
            </i>
        </div>

        <c:import url="footer.jsp"/>
      <script>
    document.querySelector('.scroll-to-top').addEventListener('click', function () {
        window.scrollTo({top: 0, behavior: 'smooth'});
    });

    function navigateToPage() {
        document.getElementById("sortform").submit();
    }

    window.onload = function() {
        var thamsosapxep = "${thamsosapxep}";
        var selectElement = document.getElementById("sort");
        selectElement.value = thamsosapxep;
    };
</script>
    </body>
</html>