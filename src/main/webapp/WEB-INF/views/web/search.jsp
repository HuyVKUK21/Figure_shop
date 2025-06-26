<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<div class="content">
	<div class="collection">
		<span>Tìm kiếm</span>
	</div>
	<div class="filter">Kết quả tìm kiếm</div>
	<div class="product">
		<c:forEach items="${search_Product }" var="product">
			<div class="product__item">
				<a href="/firgure-shop/detail-product/${ product.product_id }">
					<img
					src="<c:url value = '/template/web/img/product/${product.product_image}  '/>"
					alt="">
				</a>
				<div class="product__item__price">
					<f>${product.product_name }</f>
					<span><fmt:formatNumber value="${product.product_price}"
											type="currency" currencyCode="VND" maxFractionDigits="0"
											pattern="#,### ₫" /></span>
					<div class="price__button">
										<button class="price__button__add price__button--hover">
											<i class="fa-solid fa-cart-shopping"></i> <span>Thêm
												vào giỏ</span>
										</button>
										<button class="price__button__buy price__button--hover">
											<i class="fa-solid fa-bag-shopping"></i> <span>Mua
												ngay</span>
										</button>
									</div>
				</div>
			</div>

		</c:forEach>
	</div>
</div>