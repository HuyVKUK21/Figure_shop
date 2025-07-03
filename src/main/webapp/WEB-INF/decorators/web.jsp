<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png"
	href="${pageContext.request.contextPath}/template/web/img/our damn logo.png"
	sizes="32x32" />

<title><sitemesh:write property="title" /></title>

<!-- CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/web/css/navbar.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/web/css/home.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/web/font/Quicksand/quicksand.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/web/font/Qicksand-Bold/Quicksand-Bold.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/web/fontawesome-free-6.2.0-web/css/all.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
	<div class="cursor-round"></div>
	<div class="main"></div>
	<div class="slider" data-speed="">
		<!-- Header -->
		<%@ include file="/common/web/header.jsp"%>

		<!-- Page Content injected here -->
		<sitemesh:write property="body" />

		<!-- Footer -->
		<%@ include file="/common/web/footer.jsp"%>
	</div>

	<!-- JavaScript -->

	<script
		src="${pageContext.request.contextPath}/template/web/js/home.js"></script>
	<script
		src="${pageContext.request.contextPath}/template/web/js/parallax.js"></script>
	<script
		src="${pageContext.request.contextPath}/template/web/js/cursor.js"></script>
	<script
		src="${pageContext.request.contextPath}/template/web/js/responsive.js"></script>
		<script>
    $(document).ready(function () {
        const contextPath = "${pageContext.request.contextPath}";
        $.ajax({
            type: "GET",
            url: "/api/productAll",
            success: function (response) {
                const products = response.data;
                const productContainer = $(".product");
                productContainer.empty();
                
                products.forEach(product => {
                    const productHtml = `
                        <div class="product__item">
                            <a href="/firgure-shop/detail-product/\${product.productId}">
      
                                <img src="${contextPath}/template/web/img/product/\${product.productId}.jpg" alt="">
                            </a>
                            <div class="product__item__price">
                                <p>\${product.productName}</p>
                                <span>\${product.productPrice.toLocaleString('vi-VN')}₫</span>
                                <div class="price__button">
                                    <button class="price__button__add price__button--hover">
                                        <i class="fa-solid fa-cart-shopping"></i> <span>Thêm vào giỏ</span>
                                    </button>
                                    <button class="price__button__buy price__button--hover">
                                        <i class="fa-solid fa-bag-shopping"></i> <span>Mua ngay</span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    `;
                    productContainer.append(productHtml);
                });
            },
            error: function (xhr) {
                console.error("Error:", xhr);
            }
        });
        
        
        $.ajax({
            type: "GET",
            url: "/api/userProfile",
            success: function (response) {
                const user = response.data;

                const html = `
                    <div class="navbar__top__acc li-text">
                        <a href="/firgure-shop/infomation">
                            <i class="fa-regular fa-user"></i>
                            <span class="navbar__item-span">Xin chào: \${user.userName}</span>
                        </a>
                        <a href="/logout">
                            <i class="fa-solid fa-right-from-bracket"></i>
                            <span class="navbar__item-span">Đăng xuất</span>
                        </a>
                    </div>
                    <div class="navbar__top__cart li-text">
                        <form action="/firgure-shop/cart" method="GET">
                            <i class="fa-solid fa-cart-shopping">
                                <div class="count_holder"><f></f></div>
                                <input type="hidden" name="user_id" value="${user.user_id}">
                            </i>
                            <button class="cartButton" type="submit">
                                <span class="navbar__item-span">Giỏ Hàng</span>
                            </button>
                        </form>
                    </div>
                `;
                $("#navbar-user-section").html(html);
            },
            error: function () {
                const html = `
                    <div class="navbar__top__acc li-text">
                        <a href="/firgure-shop/login">
                            <i class="fa-regular fa-user"></i>
                            <span class="navbar__item-span">Đăng nhập / Đăng kí</span>
                        </a>
                    </div>
                    <div class="navbar__top__cart li-text">
                        <a href="/firgure-shop/login">
                            <i class="fa-solid fa-cart-shopping">
                                <div class="count_holder"><f></f></div>
                            </i>
                            <span class="navbar__item-span">Giỏ hàng</span>
                        </a>
                    </div>
                `;
                $("#navbar-user-section").html(html);
            }
        });
    });
    
</script>


</body>
</html>
