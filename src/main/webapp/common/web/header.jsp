<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<div class="navbar">
	<div class="navbar__top">
		<a href="/firgure-shop/trang-chu"><img class="li-text"
			src="//theme.hstatic.net/1000160337/1000885200/14/logo.png?v=288"
			alt=""></a>

		<form style="display: contents" action="/firgure-shop/search" method="GET">
			<div class="navbar__search li-text">
				<input name="keywords_submit" type="text">
				<button>
					<i class="fa-solid fa-magnifying-glass"></i>
				</button>
			</div>
		</form>
		<div class="navbar__top__item">
			<button class="button is-text" id="menu-button">
				<div class="button-inner-wrapper">
					<span class="icon menu-icon"></span>
				</div>
			</button>
			<div class="navbar__mobile">		
					<ul>
						<li><a class="li-text" href="/firgure-shop/trang-chu">Trang
								chủ</a></li>
						<li class="nav__product__mobile"><a class="li-text">Sản
								phẩm</a>
							<div class="product__drop__mobile">
								<a href="/firgure-shop/product-portfolio/2">Tất cả sản phẩm</a>
								<a
									href="/firgure-shop/product-portfolio/${category.category_id }">${ category.category_name }</a>

							</div></li>
						<li><a class="li-text"
							href="https://t.me/joinchat/Gn7UwkXl5DwWH4brm8NQTA">Kết nối</a></li>
						<li><a class="li-text" href="/firgure-shop/contact">Liên
								lạc</a></li>
					</ul>
			</div>


				<div class="navbar__top__item__right" id="navbar-user-section">
					
				</div>

		</div>
	</div>
	<!-- nav - mobile -->
	<div class="navbar__bot">
		<ul>
			<li><a class="li-text" href="/firgure-shop/trang-chu">Trang
					chủ</a></li>

			<li class="nav__product"><a class="bot__item li-text">Sản
					phẩm</a> <i class="fa-solid fa-chevron-down arrow--down"></i>

				<div class="product__drop">
					<a href="/firgure-shop/product-portfolio/all">Tất cả sản phẩm</a>
					
				</div></li>

			<li><a class="li-text"
				href="https://t.me/joinchat/Gn7UwkXl5DwWH4brm8NQTA">Kết nối</a></li>
			<li><a class="li-text" href="/firgure-shop/contact">Liên lạc</a></li>		
					<li><a class="li-text" href="/spring-mvc/dashboard">Quyền
							truy cập Admin</a></li>
				
		</ul>


	</div>
</div>
		<script>
    $(document).ready(function () {             
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
                        <a href="/login">
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
