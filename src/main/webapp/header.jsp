<%-- 
    Document   : header
    Created on : Oct 12, 2024, 4:23:03 PM
    Author     : anhkc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
        <script src="https://kit.fontawesome.com/bfab6e6450.js" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous" />
        <link rel="stylesheet" href="css/styleHeaderFooter.css"/>
    </head>
    <body>

        <div class="jumpToTop">
            <a href="#topOfPage">
                <i class="fa-solid fa-circle-chevron-up"></i>
            </a>
        </div>

        <section id="mobile-bottom-navigation" class="d-block d-md-none fixed-bottom">
            <div class="row mg-left-0">
                <div class="col-4">
                    <div class="mobile-nav-item">
                        <a href="contact.jsp" class="link mobile-quick-link">
                            <i class="fa-solid fa-phone"></i>Liên hệ
                        </a>
                    </div>
                </div>
                <div class="col-4">
                    <div class="mobile-nav-item">
                        <a href="SanPhamcontrol" class="link mobile-quick-link">
                            <i class="fas fa-gift"></i>Hot Deals
                        </a>
                    </div>
                </div>
                <div class="col-4">
                    <div class="mobile-nav-item x">
                        <c:if test="${sessionScope.LOGIN_USER != null}">
                                        <a class="link mobile-quick-link" href="accountServlet" title="${sessionScope.LOGIN_USER.getUsername()}">
                                            <i class="fas fa-user"></i>Chào ${sessionScope.LOGIN_USER.getUsername()}
                                        </a>
                                        <a class="link mobile-quick-link" href="Logout">Đăng xuất</a>
                                </c:if>
                                <c:if test="${sessionScope.LOGIN_USER == null}">
                                        <a class="link mobile-quick-link" href="login" title="Đăng nhập"> 
                                            <i class="fas fa-user"></i>Đăng nhập
                                        </a>
                                </c:if>
                        
<!--                        <a href="login" class="link mobile-quick-link">
                            <i class="fas fa-user"></i>Đăng nhập
                        </a>-->
                    </div>
                </div>
            </div>
        </section>


        <header>

            <div class="header-topbar d-none d-sm-block">
                <div class="container">
                    <div class="row clearfix">
                        <div class="col-sm-7">
                            <div class="title">
                                <div class="title-content">
                                    ICHIBOOKS
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-5">
                            <div class="account float-end">
                                                               
                                <c:if test="${sessionScope.LOGIN_USER != null}">
                                    <div>
                                        <a class="link" href="accountServlet" title="${sessionScope.LOGIN_USER.getUsername()}">Xin chào ${sessionScope.LOGIN_USER.getUsername()}</a>
                                        <a class="link" href="Logout">| Đăng xuất</a>
                                    </div>
                                </c:if>
                                <c:if test="${sessionScope.LOGIN_USER == null}">
                                    <div>
                                        <a class="link" href="register" title="Đăng ký">Đăng ký </a>

                                        <a class="link" href="login" title="Đăng nhập"> Đăng nhập</a>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="header-midbar">
                <div class="container">
                    <div class="row">
                        <div class="mobile-navigation col-3">
                            <a href="#" type="button" data-bs-toggle="offcanvas"
                               data-bs-target="#offcanvasScrolling" aria-controls="offcanvasScrolling">
                                <i class="fa fa-bars"></i>
                            </a>
                            <div class="offcanvas offcanvas-start" data-bs-scroll="true" data-bs-backdrop="false"
                                 tabindex="-1" id="offcanvasScrolling" aria-labelledby="offcanvasScrollingLabel">
                                <div class="offcanvas-header">
                                    <button type="button" class="btn-close" data-bs-dismiss="offcanvas"
                                            aria-label="Close"></button>
                                </div>
                                <div class="offcanvas-body">
                                    <ul class="nav flex-column">
                                        <li class="nav-item">
                                            <a class="nav-link" href="home">TRANG CHỦ</a>
                                        </li>
                                        <li class="nav-item dropdown" id="product-toggle">
                                            <a class="nav-link dropdown-toggle" href="SanPhamcontrol" data-bs-target="#sub-menu" role="button" data-bs-toggle="dropdown"
                                               aria-expanded="false"> 
                                                <span>SẢN PHẨM </span> 
                                            </a>
                                            <ul class="dropdown-menu" id="sub-menu">
                                                <c:set var="count" value="0" />
                                                <c:forEach items="${sessionScope.listC}" var="o">
                                                    <c:if test="${count < 7}">
                                                        <li>
                                                            <a class="dropdown-item" href="Phanloai?phanloai=${o.categoryID}">${o.categoryName}</a>
                                                        </li>
                                                        <c:set var="count" value="${count + 1}" />
                                                    </c:if>
                                                </c:forEach>
                                                <li>
                                                    <hr class="dropdown-divider">
                                                </li>
                                                <li><a class="dropdown-item" href="SanPhamcontrol">Tất cả</a></li>      
                                            </ul>
                                            <!--                                            <ul class="dropdown-menu" id="sub-menu">
                                                                                            <li><a class="dropdown-item" href="SanPhamcontrol?action=lightnovel">Light Novel</a></li>
                                                                                            <li><a class="dropdown-item" href="SanPhamcontrol?action=manga">Manga - Comic</a></li>
                                                                                            <li><a class="dropdown-item" href="SanPhamcontrol?action=hiendai">Văn học hiện đại</a></li>
                                                                                            <li><a class="dropdown-item" href="SanPhamcontrol?action=kinhdien">Văn học kinh điển</a></li>
                                                                                            <li><a class="dropdown-item" href="SanPhamcontrol?action=doisong">Triết lý - Đời sống</a></li>
                                                                                            <li><a class="dropdown-item" href="SanPhamcontrol?action=thieunhi">Thiếu nhi</a></li>
                                                                                            <li><a class="dropdown-item" href="SanPhamcontrol?action=ngoaingu">Ngoại ngữ</a></li>
                                                                                            <li>
                                                                                                <hr class="dropdown-divider">
                                                                                            </li>
                                                                                            <li><a class="dropdown-item" href="SanPhamcontrol">Tất cả</a></li>
                                                                                        </ul>-->
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="about-us.jsp">VỀ ICHIBOOKS</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="contact.jsp">LIÊN HỆ</a>
                                        </li>
                                        <c:if test="${sessionScope.LOGIN_USER.roleId == 'ROL001'}">
                                            <li class="nav-item dropdown" id="admin-toggle">
                                                <a class="nav-link dropdown-toggle" href="manangeUser" data-bs-target="#admin-menu" role="button" data-bs-toggle="dropdown"
                                                   aria-expanded="false"> 
                                                    <span>ADMIN DASHBOARD </span> 
                                                </a>
                                                <ul class="dropdown-menu" id="admin-menu">
                                                    <li><a class="dropdown-item" href="manageUser">Quản lý user</a></li>
                                                    <li><a class="dropdown-item" href="ListServlet">Quản lý sách</a></li>
                                                    <li><a class="dropdown-item" href="listOrder">Quản lý đơn hàng</a></li>
                                                </ul>
                                                <!--                                                <a class="nav-link" href="manageUser">ADMIN DASHBOARD</a>-->
                                            </li>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>




                        </div>

                        <div class="header-logo col-6" id="topOfPage">
                            <h1>
                                <a href="home" class="link logo-content">
                                    <i class="fa-brands fa-pagelines">
                                    </i>
                                    <p class="slogan">
                                        ICHIBAN NO<br />
                                        BOOK RETAILER
                                    </p>
                                </a>
                            </h1>

                        </div>

                        <div class="header-cart col-3">
                            <div class="midbar-cart">

                                <div class="shopping_cart">
                                    <h1 class="cart-brief-content">
                                        <a href="cart" title="Giỏ hàng" rel="nofollow" class="link-to-cart float-end">
                                            <i class="fa-solid fa-cart-shopping cart-icon"></i>
                                            <p class="cart-more-info d-none d-md-block">
                                                <span class="cart-icon-detail">Giỏ hàng</span>
                                                <span class="display_cart_quantity">${sessionScope.cart.totalQuantity}</span> sản phẩm
                                            </p>

                                        </a>
                                    </h1>
                                            
                                            
                                    <div class="cart-content">
                                        <div class="cart-container-header">
                                            <form action="cart" method="post" id="cart-form-mini">
                                                <c:forEach var="item" items="${sessionScope.cart.itemList}">
                                                    <div class="row" style="height: fit-content">
                                                        <div class="col-4">
                                                            <input type="hidden" name="pid" value="${item.getBook().getBookID()}"/>
                                                            <img src="${item.book.bookCover}" alt="Hình bìa ${item.book.bookTitle}" class="img-fluid">
                                                        </div>
                                                        <div class="col-8">
                                                            <div class="col-12 book-title">
                                                                <strong>${item.book.bookTitle}</strong>

                                                            </div>
                                                            <div class="col-12 book-info">
                                                                ${item.book.bookVersion}

                                                            </div>
                                                            <div class="col-12 book-info">
                                                                ${item.finalPrice} VNÐ x ${item.quantity}
                                                            </div>
                                                        </div>
                                                    </div>

                                                </c:forEach>
                                                <div class="row">
                                                    <div class="col-5 text-start" >
                                                        <div class="total">
                                                            <strong>Tổng cộng:</strong>
                                                        </div>
                                                    </div>
                                                    <div class="col-7 text-end" >
                                                        ${sessionScope.cart.totalAmount} VNÐ
                                                    </div>
                                                </div>

                                                <div class="pay-custom-header clear-fix">
                                                    <button type="submit" value="pay" name="action" form="cart-form-mini">Thanh toán</button>
                                                </div>
                                            </form>

                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>

                        <div class="header-search col-12">
                            <c:if test="${requestScope.query != null}">
                                <c:set var="query" value="${requestScope.query}"/>
                            </c:if>
                            <!--<form id="J_searchForm" class="search-form clearfix" action="search" method="post">-->
                            <form id="J_searchForm" class="search-form clearfix" action="search" method="get">
                                <label for="search" class="hide"></label>
                                <!-- <input type="hidden" name="type" value="product"> -->
                                <input class="search-text" name="query" type="search" id="search"
                                       placeholder="   Tìm kiếm..." value="${query}">
                                <button type="submit" class="search-btn">
                                    <i class="fa-solid fa-magnifying-glass"></i>
                                </button>
                            </form>
                        </div>




                    </div>
                </div>
            </div>

            <div class="header-navbar-main d-none d-lg-block">
                <div class="container">
                    <ul class="nav nav-fill">
                        <li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/home">TRANG CHỦ</a>
                        </li>

                        <li class="nav-item dropdown-center">
                            <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="<%=request.getContextPath()%>/SanPhamcontrol" role="button"
                               aria-expanded="false">SẢN PHẨM</a>
                            <ul class="dropdown-menu" id="sub-menu">
                                <c:set var="count" value="0" />
                                <c:forEach items="${sessionScope.listC}" var="o">
                                    <c:if test="${count < 7}">
                                        <li>
                                            <a class="dropdown-item" href="Phanloai?phanloai=${o.categoryID}">${o.categoryName}</a>
                                        </li>
                                        <c:set var="count" value="${count + 1}" />
                                    </c:if>
                                </c:forEach>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li><a class="dropdown-item" href="SanPhamcontrol">Tất cả</a></li>      
                            </ul>
                            <!--                            <ul class="dropdown-menu">
                                                            <li><a class="dropdown-item" href="SanPhamcontrol?action=lightNovel">Light Novel</a></li>
                                                            <li><a class="dropdown-item" href="SanPhamcontrol?action=manga">Manga - Comic</a></li>
                                                            <li><a class="dropdown-item" href="SanPhamcontrol?action=hiendai">Văn học hiện đại</a></li>
                                                            <li><a class="dropdown-item" href="SanPhamcontrol?action=kinhdien">Văn học kinh điển</a></li>
                                                            <li><a class="dropdown-item" href="SanPhamcontrol?action=doisong">Triết lý - Đời sống</a></li>
                                                            <li><a class="dropdown-item" href="SanPhamcontrol?action=thieunhi">Thiếu nhi</a></li>
                                                            <li><a class="dropdown-item" href="SanPhamcontrol?action=ngoaingu">Ngoại ngữ</a></li>
                                                            <li>
                                                                <hr class="dropdown-divider">
                                                            </li>
                                                            <li><a class="dropdown-item" href="SanPhamcontrol">Tất cả</a></li>
                            
                                                        </ul>-->
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/about-us.jsp">VỀ ICHIBOOKS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/contact.jsp">LIÊN HỆ</a>
                        </li>
                    </ul>
                </div>
            </div>

        </header>


        <!-- Bootstrap JavaScript Libraries -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
                integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>

        <script src="js/script.js"></script>

    </body>
</html>
