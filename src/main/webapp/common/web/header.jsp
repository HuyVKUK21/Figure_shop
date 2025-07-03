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
				<c:forEach items="${tbl_category_product}" var="category">
			
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
				</c:forEach>
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
					<c:forEach items="${tbl_category_product}" var="category">
						<a href="/firgure-shop/product-portfolio/${category.category_id }">${ category.category_name }</a>
					</c:forEach>
				</div></li>

			<li><a class="li-text"
				href="https://t.me/joinchat/Gn7UwkXl5DwWH4brm8NQTA">Kết nối</a></li>
			<li><a class="li-text" href="/firgure-shop/contact">Liên lạc</a></li>

			<c:if test="${not empty LoginInfo }">
				<c:if test="${LoginInfo.role == 1 }">
					<li><a class="li-text" href="/spring-mvc/dashboard">Quyền
							truy cập Admin</a></li>
				</c:if>
			</c:if>
		</ul>


	</div>
</div>

