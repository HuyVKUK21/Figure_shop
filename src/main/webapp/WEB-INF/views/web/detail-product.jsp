<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>


<title>${detail_product.product_name }</title>



<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/template/web/css/product.css">

<div class="directory">
	<a href="/firgure-shop">Trang chủ</a> <a
		href="/firgure-shop/product-portfolio/${detail_product.category.category_id }">
		${detail_product.category.category_name }</a> <span class="product__name">
		${detail_product.product_name }</span>


</div>

<div class="main">
	<div class="product">
		<div class="product__image slider" data-speed="0">
			<div class="image__left">
				<img class="img-list li-text"
					src="<c:url value = '/template/web/img/product/${detail_product.product_image }'/>"
					alt=""> <img class="img-list li-text"
					src="<c:url value = '/template/web/img/product/${detail_product.product_image2 }'/>"
					alt=""> <img class="img-list li-text"
					src="<c:url value = '/template/web/img/product/${detail_product.product_image3 }'/>"
					alt=""> <img class="img-list li-text"
					src="<c:url value = '/template/web/img/product/${detail_product.product_image4 }'/>"
					alt="">
			</div>



			<div class="image__right">
				<div class="image__slide">
					<img class="img-list li-text"
						src="<c:url value = '/template/web/img/product/${detail_product.product_image }'/>"
						alt=""> <img class="img-list li-text"
						src="<c:url value = '/template/web/img/product/${detail_product.product_image2 }'/>"
						alt=""> <img class="img-list li-text"
						src="<c:url value = '/template/web/img/product/${detail_product.product_image3 }'/>"
						alt=""> <img class="img-list li-text"
						src="<c:url value = '/template/web/img/product/${detail_product.product_image4 }'/>"
						alt="">
				</div>
			</div>
		</div>
		<div class="product__right">
			<div class="product__info">
                      
                    </div>


			<div class="product__buy">
                        <div class="buy__ammount li-text">
                            <button class="ammount-sub">-</button>
                            <input class="ammount-input" type="tel" value="1">
                            <button class="ammount-add">+</button>
                        </div>

                        <button class="price__button__add2 price__button--hover2  li-text">
                            <i class="fa-solid fa-cart-shopping"></i>
                            <span>Thêm vào giỏ</span>
                        </button>
                    </div>





			<div class="product__rule">
				<div class="rule">
					<span><b>✔ Sản phẩm chính hãng từ Nhật Bản.</b></span> <span><b>✔
							Trước khi bạn đặt mua :</b> vui lòng check lại giá hiện tại với
						admin, vì khả năng giá đã thay đổi so với lần cập nhật gần nhất,
						hoặc hết hàng, hết suất order. Do giới hạn số lượng, figure Nhật
						sẽ hiếm dần theo thời gian, dẫn tới giá tăng.</span>
				</div>
				<div class="rule">
					<span>✔ Với sản phẩm <b>CÓ SẴN, bạn sẽ được giao ngay.</b></span> <span>✔
						Với sản phẩm <b>ĐẶT TRƯỚC, bạn cần cọc 50% giá trị sản phẩm.</b>
						Hàng về VN khoảng 2-3 tuần sau khi phát hành. Lịch phát hành dự
						kiến như thông tin chi tiết bên dưới. Với sản phẩm CÓ SẴN, bạn sẽ
						được giao ngay.
					</span>
				</div>
				<div class="rule">
					<span>✔ Giao hàng tận nơi</span> <span>✔ Miễn phí ship với
						các đơn hàng >1000K </span> <span>✔ Vui lòng kiểm tra sản phẩm khi
						nhận bưu kiện Giao hàng tận nơi</span>
				</div>
			</div>

			<div class="product__detail">
				
			</div>
		</div>
	</div>
</div>

<div class="related">
	<span class="related__span">Sản phẩm liên quan</span>
	<div class="cata__contain">
		<div class="product2">
			
		</div>
	</div>
</div>


	<script src="${pageContext.request.contextPath}/template/web/js/product.js"></script>
	<script>
	$(document).ready(function () {	   
	    const productId = new URLSearchParams(window.location.search).get("productId");
	
	    $.ajax({
	        url: "http://localhost:8080/api/product/detail-product",
	        type: "GET",
	        data: { productId: productId },
	        success: function (response) {
	                const product = response.data;
	                const nameHTML = `<f><b>\${product.productName}</b></f>`;
	                const priceHTML = `<f>\${product.productPrice.toLocaleString()}₫</f>`;	                
	                const detailHtml = `
	                    <span><b>Thông tin sản phẩm</b></span>
	                    <span class="product__highlight">\${product.productPriceLog} \${product.productPrice.toLocaleString()}₫</span>
	                    <span>Danh mục: \${product.categoryName ?? "Không rõ"}</span>
	                    <span>Hãng sản xuất: \${product.brandName ?? "Không rõ"}</span>
	                    <span>Nhân vật: \${product.productDesc}</span>
	                    <span>Series: \${product.productSeries}</span>
	                    <span>Tỷ lệ: \${product.productProportion}</span>
	                    <span>Kích thước: \${product.productSize}</span>
	                    <span>Ngày phát hành: \${product.productDate}</span>
	                `;

	                $(".product__detail")
	                    .empty() 
	                    .append(detailHtml);
	                
	                $(".product__info")
	                    .empty() 
	                    .append(nameHTML)
	                    .append(priceHTML);
	            
	        },
	        error: function (xhr) {
	            $(".product__info").text("Không thể tải sản phẩm.");
	        }
	    });
	    
	 
	        const contextPath = "${pageContext.request.contextPath}";
	        $.ajax({
	            type: "GET",
	            url: "/api/productAll",
	            success: function (response) {
	                const products = response.data;
	                const productContainer = $(".product2");
	                productContainer.empty();
	                
	                products.forEach(product => {
	                    const productHtml = `
	                        <div class="product__item">
	                            <a href="/product/detail-product?productId=\${product.productId}"  class="product__link">
	                                <img src="${contextPath}/template/web/img/product/\${product.productId}.jpg" alt="">
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
	                            </a>
	                        </div>
	                    `;
	                    productContainer.append(productHtml);
	                });
	            },
	            error: function (xhr) {
	                console.error("Error:", xhr);
	            }
	        });
	});

	</script>

