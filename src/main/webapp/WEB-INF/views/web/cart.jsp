<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/template/web/css/cart.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="<c:url value = '/template/web/js/cart.js'/>"></script>
<title>Giỏ hàng của bạn</title>

<div class="title">Giỏ hàng của bạn</div>

<div class="cart">
	<div class="cart__left">
		<div class="cart__head">
			<span>Bạn đang có </span> <b class="ammount">0</b> <span> sản
				phẩm trong giỏ</span>
		</div>

		<div class="cart__product">

			<div class="cart__item">
				<div class="item__top">
					<div class="item__info">
						<img
							src="asset/img/product/17Cm-Genshin-T-c-ng-Klee-Hibana-Hi-p-S-Anime-H-nh-1-7-Quy.jpg_Q90.jpg_.jpg"
							alt="">
						<div class="details">
							<b>Genshin Impact Klee Summer</b>
							<div class="buy__ammount li-text">
								<button class="ammount-sub">-</button>
								<input class="ammount-input" type="tel" value="1">
								<button class="ammount-add">+</button>
							</div>
							<b class="price">9.800.000đ</b>
						</div>
					</div>
					<i class="fa-regular fa-trash-can li-text"></i>
				</div>
				<div class="item__bot">
					<b>Thành tiền :</b> <span class="total">9.800.000đ</span>
				</div>
			</div>





		</div>
	</div>
	<div class="cart__right">
		<span>Thông tin đơn hàng</span>
		<div class="right__total">
			<b>Thành tiền :</b> <b class="order__total">0đ</b>
		</div>
		<ul>
			<li>Phí vận chuyển sẽ được tính ở trang thanh toán.</li>
			<li>Bạn cũng có thể nhập mã giảm giá ở trang thanh toán.</li>
		</ul>

		<button class="price__button__add price__button--hover li-text">
			<span>Thanh toán</span>
		</button>
	</div>
</div>