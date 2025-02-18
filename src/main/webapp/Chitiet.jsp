<%-- 
    Document   : header
    Created on : Oct 12, 2024, 4:23:03 PM
    Author     : anhkc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
        <script src="https://kit.fontawesome.com/bfab6e6450.js" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous" />
        <link rel="stylesheet" href="css\style.css">
        <link rel="stylesheet" href="css\Chitiet.css">
        <link 
            </head>
    <body>
        <c:import url="header.jsp"/>
        <main class="mt-5 pt-4">
            <div class="container mt-5">
                <div class="row">
                    <div class="col-md-6">
                        <img alt="Placeholder image" class="img-fluid" height="660" src="${Chitiet.bookCover}" width="500"/>
                    </div>
                    <div class="col-md-6">
                        <h1 class="product-title">
                            ${Chitiet.bookTitle}
                        </h1>
                        <div class="d-flex flex-column align-items-start">
                            <span class="discount-badge">

                                Khuyến Mãi: ${Chitiet.bookDiscount}%

                            </span>
                            <span class="price">
                                <c:if test="${!isFlashed}">

                                    ${Chitiet.bookPrice * (1 - Chitiet.bookDiscount / 100)}đ
                                </c:if>
                                <c:if test="${isFlashed}">

                                    ${Chitiet.bookPrice * (1 - Chitiet.bookFlashSale / 100)}đ
                                </c:if>
                            </span>
                            <span class="original-price">
                                ${Chitiet.bookPrice}đ
                            </span>
                        </div>
                        <div class="mt-3">  
                            <div >

                                <c:forEach var="author" items="${Chitiet1}">
                                    <p style="font:" class="mt-3 "> <strong > Tác giả     </strong>: ${author.authorName}</p>
                                </c:forEach>

                            </div>
                            <p>
                                <strong>
                                    Hình thức:

                                </strong>
                                ${Chitiet.bookVersion}
                            </p>
                            <p>
                                <strong>
                                    Nhà xuất bản:
                                </strong>
                                ${Chitiet2.publisherName}</p>
                            </p>
                        </div>
                        <div class="product-details mt-3">
                            <p>
                                <strong>
                                    Nội dung:
                                </strong>
                            </p>
                            <p>
                                ${Chitiet.bookIntro}
                            </p>
                        </div>
                        <c:if test="${Chitiet.bookQuantity == 0}">

                            <div class="out-of-stock">
                                <strong>
                                    <h1>HẾT HÀNG</h1>
                                </strong>
                            </div>
                        </c:if>
                        <div class="d-flex align-items-center mt-3">
                            <form class="d-flex justify-content-left" action="cart" method ="post">
                                <input type="hidden" name="id" value="${Chitiet.bookID}">
                                <div class="form-outline me-1" style="width: 100px;">
                                    <input name="quantity"class="form-control" type="number" value="1" min="1" oninput="validity.valid || (value = 1)" />
                                </div>
                                <c:if test="${Chitiet.bookQuantity == 0}">

                                    <button class="btn btn-secondary ms-1" type="submit" disabled>
                                        THÊM VÀO GIỎ
                                        <i class="fas fa-shopping-cart ms-1">
                                        </i>
                                    </button>
                                </c:if>
                                <c:if test="${Chitiet.bookQuantity != 0}">

                                    <button class="btn btn-primary ms-1" type="submit" name="action" value="add">
                                        THÊM VÀO GIỎ
                                        <i class="fas fa-shopping-cart ms-1">
                                        </i>
                                    </button>
                                </c:if>
                            </form>
                        </div>
                        <div class="category-tags mt-3">
                            <p><strong>DANH MỤC:</strong>
                                <c:forEach var="category" items="${Chitiet3}" varStatus="status">
                                    ${category.categoryName}<c:if test="${!status.last}">, </c:if>
                                </c:forEach>
                            </p>
                        </div>
                    </div>
                </div>
                <hr/>



            </div>

        </main>



        <c:import url="footer.jsp"/>
    </footer>
    <script crossorigin="anonymous" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" src="https://code.jquery.com/jquery-3.3.1.slim.min.js">
    </script>
    <script crossorigin="anonymous" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa2d4H9m1FMRk6a5ld5I5w1TB" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js">
    </script>
    <script crossorigin="anonymous" integrity="sha384-smHYkdEdqU2uM3a0C5I5cc3mozpSxMZ6b2d6drsZ8g8y5ztQx2b4fRIv8y1cHf65" src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js">
    </script>
    <script>
        function scrollLeft() {
            document.querySelector('.product-carousel').scrollBy({
                left: -200,
                behavior: 'smooth'
            });
        }

        function scrollRight() {
            document.querySelector('.product-carousel').scrollBy({
                left: 200,
                behavior: 'smooth'
            });
        }
    </script>
</body>
</html>
</body>
</html>