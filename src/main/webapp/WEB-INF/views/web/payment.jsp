<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Thanh toán</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png" href="template/img/our damn logo.png"
	sizes="32x32" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/template/web/css/navbar.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/template/web/css/purchase.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/template/web/font/Quicksand/quicksand.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/template/web/fontawesome-free-6.2.0-web/css/all.css">
</head>

<body>
	<div class="cursor-round"></div>

	<div class="main"></div>
	<div data-speed="-1" class="slider">
		<div class="mainn">
			<div class="lefty">
				<b class="title">Figurin</b>

				<div class="directory">
					<a href="/firgure-shop/trang-chu">Trang chủ</a> <a
						href="/firgure-shop/cart?user_id=${LoginInfo.user_id }">Giỏ
						hàng</a>
				</div>
				<b>Thông tin giao hàng</b>
				<div class="item">
					<i class="fa-solid fa-user"></i>
					<div class="item__text"></div>
				</div>
				<form action="/" method="POST">
					<input type="text" name="shipping_name" placeholder="Họ và tên"
						value="${LoginInfo.user_name }"> <input type="text"
						name="shipping_address" placeholder="Địa chỉ"
						value="${LoginInfo.user_address }"> <input type="text"
						name="shipping_phone" placeholder="Số điện thoại"
						value="${LoginInfo.user_phone }">

					<div class="payment__title">Phương thức thanh toán</div>
					<div class="payment__method">
						<div class="payment__option option1 li-text">
							<i class="fa-solid fa-credit-card"></i> Chuyển khoản đến Figurin
						</div>

						<div class="payment__option option1--open option-detail">
							Chủ TK: Trần Phạm Quốc Bảo<br> Nội dung chuyển khoản: Mã Đơn
							Hàng của bạn.<br> Vd: Cọc 50 phan tram 012345, Thanh toán
							100 phan tram 012345 <br> 1. Techcombank Số TK:
							19032672750016 Chi nhánh: Kỳ Hòa<br> 2. VIB Số TK: 005181007
							Chi nhánh VIB Gia Định<br> 3. Vietcombank Số TK:
							0421000442816 Chi nhánh Phú Thọ<br> 4. ACB Số TK: 143091319
							Chi nhánh: Phòng giao dịch Vạn Hạnh<br> 5. VPbank Số TK:
							655580999 Chi nhánh Phòng giao dịch Quận 10<br>
						</div>

						<div class="payment__option option2 li-text">
							<i class="fa-solid fa-wallet"></i> Chuyển tiền đến ví điện tử
						</div>

						<div class="payment__option option2--open option-detail">
							Momo / Zalo Pay / Viettel Money <br> SĐT: 0908268007<br>
							Người nhận: Trần Phạm Quốc Bảo<br> Nội dung chuyển khoản: Mã
							Đơn Hàng của bạn.<br> Vd: Cọc 50 phan tram 012345, Thanh
							toán 100 phan tram 012345<br>
						</div>

						<div class="payment__option option3 li-text">
							<i class="fa-solid fa-truck-fast"></i> COD - Chỉ áp dụng với sản
							phẩm có sẵn
						</div>

						<div class="payment__option option3--open option-detail">
							Đặc biệt lưu ý: Hãy chắc chắn bạn đã liên hệ với admin để xác
							định sản phẩm này có sẵn hay cần đặt trước. Thời gian ship từ 1-4
							ngày tuỳ vào địa chỉ của bạn, bạn có thể thanh toán 100% cho
							shipper. Shipper sẽ gọi điện cho bạn trước khi đến. Đơn hàng sẽ
							tự động huỷ nếu bạn lựa chọn COD cho sản phẩm cần đặt trước.</div>

						<div class="payment__btn">
							<button name="payment_option"
								value="Thanh toán bằng chuyển khoản"
								class="purchase li-text btn1">Thanh toán bằng chuyển
								khoản</button>
							<button name="payment_option" value="Thanh toán bằng ví điện tử"
								class="purchase li-text btn2">Thanh toán bằng ví điện
								tử</button>
							<button name="payment_option" value="Thanh toán COD"
								class="purchase li-text btn3">Thanh toán COD</button>
						</div>
				</form>


			</div>

		</div>

		<div class="righty">

			<div class="product-container">
			
			</div>

			<div class="total">

				<span>Tổng cộng</span> <span class="total__price"> 
				</span>
			</div>
		</div>
	</div>
</body>

</html>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="<c:url value = '/template/web/js/cursor.js'/>"></script>
<script src="<c:url value = '/template/web/js/responsive.js'/>"></script>
<script src="<c:url value = '/template/web/js/purchase.js'/>"></script>
<script>
const contextPath = "${pageContext.request.contextPath}";
    $(document).ready(function () {             
        $.ajax({
            type: "GET",
            url: "/api/userProfile",
            success: function (response) {
                const user = response.data;
                const html = `
                	<b>\${user.userName} (<span>\${user.userEmail}</span>)</b>
                	<a class="li-text" href="/firgure-shop/logout">Đăng xuất</a>
                `;
                $(".item__text").html(html);
            },
            error: function () {
                const html = `
                	<b>Vui lòng <a href = "/login">Đăng nhập</a> để tiếp tục</b>
                `;
                $(".item__text").html(html);
            }
        });
        
        $.ajax({
        	type: "GET",
        	url: "/api/cart",
        	success: function (response) {
        		const products = response.data;
        		const container = $(".product-container");
        		let totalPrice = 0;

        		container.empty();

        		products.forEach(product => {
        			const image = product.productImage.find(img => img.imageOrder === 1);
        			const imagePath = image ? image.productImage : 'default.jpg';
        			const itemPrice = product.productPrice * product.quantity;
        			totalPrice += itemPrice;

        			const productHtml = `
        				<div class="product">
        					<div class="product__detail">
        						<img class="img-list li-text"
        							src="\${contextPath}/template/web/img/product/\${imagePath}"
        							alt=""> 
        						<span class="product__text">\${product.productName}</span>
        						<span class="product-quanity">x \${product.quantity}</span>
        					</div>
        					<div class="product__price">
        						\${itemPrice.toLocaleString('vi-VN')} ₫
        					</div>
        				</div>
        			`;

        			container.append(productHtml);
        		});

        		$(".total__price").text(totalPrice.toLocaleString('vi-VN') + ' ₫');
        	},
        	error: function (xhr) {
        		console.error("Lỗi khi tải giỏ hàng:", xhr);
        	}
        });

    });
    
</script>

