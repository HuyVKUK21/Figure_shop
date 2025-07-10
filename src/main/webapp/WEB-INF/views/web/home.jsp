<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chủ - Firgurin Shop</title>
    <!-- Thêm CSS của Swiper -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
</head>

<body>
	<div class="banner">
		<div class="banner-navigate">
			<div class="banner--button button__left li-text">
				<i class="fa-solid fa-chevron-left"></i>
			</div>
			<div class="banner--button button__right li-text">
				<i class="fa-solid fa-chevron-right"></i>
			</div>
		</div>

		<img class="banner-slide"
			src="${pageContext.request.contextPath}/template/web/img/home/slide_1_img.webp"
			style="display: block;" alt=""> <img class="banner-slide"
			src="${pageContext.request.contextPath}/template/web/img/home/slide_2_img.webp"
			alt=""> <img class="banner-slide"
			src="${pageContext.request.contextPath}/template/web/img/home/slide_3_img.webp"
			alt=""> <img class="banner-slide"
			src="${pageContext.request.contextPath}/template/web/img/home/slide_4_img.webp"
			alt=""> <img class="banner__img"
			src="${pageContext.request.contextPath}/template/web/img/home/slide_1_img.webp"
			alt="">
	</div>

	<div class="colection">
		<div class="collection__content li-text">
			<div class="collection__content__left">
				<b>Nedoroid</b> <span> Dòng chibi figure được yêu thích nhất,
					nhiều gương mặt, thoả sức tạo dáng</span>
				<button>
					<span>Xem ngay</span>
				</button>
			</div>
			<img
				src="${pageContext.request.contextPath}/template/web/img/home/categorybanner_1_img.webp">
		</div>
		<div class="collection__content li-text">
			<div class="collection__content__left">
				<span>Bộ sưu tập</span> <b>Nedoroid</b> <span> Dòng chibi
					figure được yêu thích nhất, nhiều gương mặt, thoả sức tạo dáng</span>
				<button>
					<span>Xem ngay</span>
				</button>
			</div>
			<img
				src="${pageContext.request.contextPath}/template/web/img/home/categorybanner_2_img.webp">
		</div>
	</div>

	<div class="content">
		<!-- Sản phẩm order -->
		<div class="catalog">
			<b>Sản phẩm order</b> <span>Những sản phẩm đã hoặc sắp phát
				hành & cần đặt trước</span>
			<div class="cata__box order">
				<img
					src="${pageContext.request.contextPath}/template/web/img/home/home_collection_1_banner.webp">
				<div class="cata__contain">
					<!-- Swiper -->
					<div class="swiper productSwiper order-swiper">
						<div class="swiper-wrapper">
							<!-- Trang sẽ được thêm bởi JavaScript -->
						</div>
					</div>
					<div class="cata__navigate">
						<div class="cata--button swiper-button-prev">
							<i class="fa-solid fa-chevron-left"></i>
						</div>
						<div class="cata--button swiper-button-next">
							<i class="fa-solid fa-chevron-right"></i>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Sản phẩm có sẵn -->
		<div class="catalog">
			<b>Sản phẩm có sẵn</b> <span>Sản phẩm đang có sẵn, bạn có thể
				mua ngay</span>
			<div class="cata__box">
				<img
					src="${pageContext.request.contextPath}/template/web/img/home/home_collection_1_banner.webp">
				<div class="cata__contain">
					<!-- Swiper -->
					<div class="swiper productSwiper available-swiper">
						<div class="swiper-wrapper">
							<!-- Trang sẽ được thêm bởi JavaScript -->
						</div>
					</div>
					<div class="cata__navigate">
						<div class="cata--button swiper-button-prev">
							<i class="fa-solid fa-chevron-left"></i>
						</div>
						<div class="cata--button swiper-button-next">
							<i class="fa-solid fa-chevron-right"></i>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="detail">
			<div class="detail__item">
				<i class="fa-solid fa-box"></i> <b>Sản phẩm chính hãng</b> <span>Nhập
					khâu trực tiếp từ Nhật Bản</span>
			</div>

			<div class="detail__item">
				<i class="fa-regular fa-credit-card"></i> <b>Thanh toán đơn giản</b>
				<span>Chuyển khoản hoặc COD</span>
			</div>

			<div class="detail__item">
				<i class="fa-solid fa-truck"></i> <b>Giao hàng nhanh chóng</b> <span>Miễn
					phí với đơn hàng >1000K</span>
			</div>
		</div>
	</div>
	
	
	
		
	<script>
		$(document).ready(function() {
			const contextPath = "${pageContext.request.contextPath}";
			// Khởi tạo Swiper
			const orderSwiper = new Swiper('.order-swiper', {
				navigation: {
					nextEl: '.order-swiper + .cata__navigate .swiper-button-next',
					prevEl: '.order-swiper + .cata__navigate .swiper-button-prev',
				},
				slidesPerView: 1,
				spaceBetween: 0,
				speed: 500,
			});
			
			const availableSwiper = new Swiper('.available-swiper', {
				navigation: {
					nextEl: '.available-swiper + .cata__navigate .swiper-button-next',
					prevEl: '.available-swiper + .cata__navigate .swiper-button-prev',
				},
				slidesPerView: 1,
				spaceBetween: 0,
				speed: 500,
			});
			
	
			$.ajax({
				type: "GET",
				url: "/api/productAll",
				success: function(response) {
					const products = response.data;
					
					createProductSlides(products, '.order-swiper .swiper-wrapper', 0, products.length);
					createProductSlides(products, '.available-swiper .swiper-wrapper', 0, products.length);
					
		
					orderSwiper.update();
					availableSwiper.update();
				},
				error: function(xhr) {
					console.error("Error:", xhr);
				}
			});
			
	
			function createProductSlides(products, containerSelector, startIndex, endIndex) {
				const container = $(containerSelector);
				const productsToShow = products.slice(startIndex, endIndex);
				
				for (let i = 0; i < productsToShow.length; i += 6) {
					const slideProducts = productsToShow.slice(i, i + 6);
					if (slideProducts.length > 0) {
						let slideHtml = '<div class="swiper-slide"><div class="product-grid">';		
						slideProducts.forEach(product => {
							const image = product.productImage.find(img => img.imageOrder === 1);							
							slideHtml += `
								<div class="product__item">
									<a href="/product/detail-product?productId=\${product.productId}" class="product__link">
									 <img src="${contextPath}/template/web/img/product/\${image ? image.productImage : 'default.jpg'}" alt="">
										
										<div class="product__item__price">
											<p>\${product.productName}</p>
											<span>\${product.productPrice.toLocaleString('vi-VN')}₫</span>
											<div class="price__button">
												<button class="price__button__add price__button--hover">
													<i class="fa-solid fa-cart-shopping"></i> 
												</button>
												<button class="price__button__buy price__button--hover">
													<i class="fa-solid fa-bag-shopping"></i> 
												</button>
											</div>
										</div>
									</a>
								</div>
							`;
						});
						
						slideHtml += '</div></div>';
						container.append(slideHtml);
					}
				}
			}
		});
	</script>
</body>
</html>