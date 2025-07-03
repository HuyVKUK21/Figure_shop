<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Trang chủ - Firgurin Shop</title>

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
		<div class="catalog">
			<b>Sản phẩm order</b> <span>Những sản phẩm đã hoặc sắp phát
				hành & cần đặt trước</span>
			<div class="cata__box order">
				<img
					src="${pageContext.request.contextPath}/template/web/img/home/home_collection_1_banner.webp">
				<div class="cata__contain">
					<div class="cata__navigate">
						<div class="cata--button cata--left">
							<i class="fa-solid fa-chevron-left"></i>
						</div>
						<div class="cata--button cata--left">
							<i class="fa-solid fa-chevron-right"></i>
						</div>
					</div>
					<div class="product"></div>
				</div>
			</div>
		</div>

		<!-- Có sẵn -->

		<div class="catalog">
			<b>Sản phẩm có sẵn</b> <span>Sản phẩm đang có sẵn, bạn có thể
				mua ngay</span>
			<div class="cata__box">
				<img
					src="${pageContext.request.contextPath}/template/web/img/home/home_collection_1_banner.webp">


				<div class="cata__contain">
					<div class="cata__navigate">
						<div class="cata--button cata--left">
							<i class="fa-solid fa-chevron-left"></i>
						</div>
						<div class="cata--button cata--left">
							<i class="fa-solid fa-chevron-right"></i>
						</div>
					</div>
					<div class="product">
					
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


</body>