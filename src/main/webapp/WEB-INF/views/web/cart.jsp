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

			<div class="cart__item"></div>


			<div class="item__bot">
				
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

<script>
	$(document).ready(function () {	   	   	 	        
	        $.ajax({
	            type: "GET",
	            url: "/api/cart",
	            success: function (response) {
	                const products = response.data;
	                const productContainer = $(".cart__item");
	                const totalPriceContainer = $(".item__bot");
	                let totalPrice = 0;
	                productContainer.empty();
	                
	                products.forEach(product => {
	                	
	                	const image = product.productImage.find(img => img.imageOrder === 1);
	                    const productHtml = `
	                    	<div class="item__top">
	    					<div class="item__info">
	    						<img
	    							src="${contextPath}/template/web/img/product/\${image ? image.productImage : 'default.jpg'}"
	    							alt="">
	    						<div class="details">
	    							<b>\${product.productName}</b>
	    							<div class="buy__ammount li-text">
	    								<button class="ammount-sub">-</button>
	    								<input class="ammount-input" type="tel" value="\${product.quantity}">
	    								<button class="ammount-add">+</button>
	    							</div>
	    							<b class="price">\${product.productPrice.toLocaleString('vi-VN')}₫</b>
	    						</div>
	    					</div>
	    					<i class="fa-regular fa-trash-can li-text"></i>
	    				</div>
	    				
	                    `;
	                 	totalPrice += product.productPrice * product.quantity;
	                    productContainer.append(productHtml);
	                });
	                const totalPriceProduct = `
	                    <b>Thành tiền :</b> <span class="total">\${totalPrice.toLocaleString('vi-VN')}₫</span>
	                `;
	                totalPriceContainer.append(totalPriceProduct);
	                
	            },
	            error: function (xhr) {
	                console.error("Error:", xhr);
	            }
	        });
	});

	</script>