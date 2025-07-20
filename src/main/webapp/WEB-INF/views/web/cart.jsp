<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/template/web/css/cart.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
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


			<div class="item__bot"></div>


		</div>
	</div>
	<div class="cart__right">
		<span>Thông tin đơn hàng</span>
		<div class="right__total">
			<b>Thành tiền :</b> <b class="order__total"></b>
		</div>
		<ul>
			<li>Phí vận chuyển sẽ được tính ở trang thanh toán.</li>
			<li>Bạn cũng có thể nhập mã giảm giá ở trang thanh toán.</li>
		</ul>

		<a href="/payment"
			class="price__button__add price__button--hover li-text"> <span>Thanh
				toán</span>
		</a>

	</div>
</div>
<script>
	const contextPath = "${pageContext.request.contextPath}";

	$(document).ready(function () {
		loadCart(); 
	});

	function loadCart() {
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
					const imagePath = image ? image.productImage : 'default.jpg';

					const productHtml = `
						<div class="item__top">
							<div class="item__info">
								<img src="\${contextPath}/template/web/img/product/\${imagePath}" alt="">
								<div class="details">
									<input type="hidden" class="cart-id" value="\${product.cartId}">
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

				const formattedTotal = totalPrice.toLocaleString('vi-VN') + '₫';

				totalPriceContainer.html(`
					<b>Thành tiền :</b> <span class="total">\${formattedTotal}</span>
				`);
				$('.order__total').text(formattedTotal); 
				$('.ammount').text(products.length); 
			},
			error: function (xhr) {
				console.error("Lỗi khi tải giỏ hàng:", xhr);
			}
		});
	}

	
	let debounceTimer = null;

	$(document).on('click', '.ammount-add, .ammount-sub', function () {
		const isAdd = $(this).hasClass('ammount-add');
		const detail = $(this).closest('.details');
		const input = detail.find('.ammount-input');
		const cartId = detail.find('.cart-id').val();
		const priceText = detail.find('.price').text();
		const price = parseInt(priceText.replace(/[^\d]/g, ''));

		let quantity = parseInt(input.val()) || 1;
		quantity = isAdd ? quantity + 1 : quantity - 1;

		if (quantity < 1) return;

		input.val(quantity);

	
		if (debounceTimer) clearTimeout(debounceTimer);

		
		debounceTimer = setTimeout(() => {
			$.ajax({
				url: '/api/updatecart',
				method: 'PUT',
				contentType: 'application/json',
				data: JSON.stringify({ cartId, quantity }),
				success: function () {
					updateTotal(); 
				},
				error: function (xhr) {
					console.error("Lỗi khi cập nhật giỏ hàng:", xhr);
				}
			});
		}, 500);
	});


	function updateTotal() {
		let total = 0;

		$('.details').each(function () {
			const quantity = parseInt($(this).find('.ammount-input').val()) || 1;
			const priceText = $(this).find('.price').text();
			const price = parseInt(priceText.replace(/[^\d]/g, ''));
			total += quantity * price;
		});

		const formattedTotal = total.toLocaleString('vi-VN') + '₫';

		$('.total').text(formattedTotal);        
		$('.order__total').text(formattedTotal); 
		$('.ammount').text($('.details').length); 
	}
	
	$(document).on('click', '.fa-trash-can', function () {
		const itemTop = $(this).closest('.item__top');
		const cartId = itemTop.find('.cart-id').val();
	
		$.ajax({
		    url: '/api/delete-item-cart?cartId=' + cartId,  
		    method: 'DELETE',
		    success: function (response) {		      
		        loadCart(); 
		    },
		    error: function (xhr) {
		        console.error("Lỗi khi xoá:", xhr);
		    }
		});

	});


</script>


