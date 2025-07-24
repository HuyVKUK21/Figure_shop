<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Thanh toán</title>
  <link rel="icon" type="image/png" href="template/img/our damn logo.png" sizes="32x32" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/template/web/css/purchase.css">
</head>
<body>
  <div class="checkout-container">
    <div class="checkout-left">
      <div class="section-box user-info">
        <div class="header-box">
          <h2>Thông tin giao hàng</h2>
          
          <button class="btn_address"><i class="fa-solid fa-plus"></i> Thêm địa chỉ giao hàng</button>
        </div>
        <select id="shipping_address" class="form-control">
          <option value="">-- Chọn địa chỉ giao hàng --</option>
        </select>
      </div>

      <div class="section-box payment-methods">
        <h2>Phương thức thanh toán</h2>
        <div class="payment-choice" data-method="wallet">
          <i class="fas fa-wallet"></i>
          <span>Ví điện tử</span>
        </div>
        <div class="payment-choice" data-method="cod">
          <i class="fas fa-truck"></i>
          <span>COD (Thanh toán khi nhận hàng)</span>
        </div>
      </div>

      <button class="btn-pay" onclick="submitPayment()">Xác nhận thanh toán</button>
    </div>

    <div class="checkout-right">
      <div class="section-box">
        <h2>Đơn hàng của bạn</h2>
        <div class="product-container"></div>
        <div class="total">
          <span>Tổng cộng:</span>
          <span class="total__price"></span>
        </div>
      </div>
    </div>
  </div>

  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>
    const contextPath = "${pageContext.request.contextPath}";
    let cartItems = [];

    $(document).ready(function () {                     
        $.ajax({
            type: "GET",
            url: "/api/cart",
            success: function (response) {
            	cartItems = response.data.map(product => ({
            		    productId: product.productId,
            		    quantity: product.quantity
            		  }));
                const products = response.data;
                const container = $(".product-container");
                let totalPrice = 0;

                container.empty();

                if (response && Array.isArray(response.data) && response.data.length > 0) {
                    products.forEach(product => {
                        const image = product.productImage.find(img => img.imageOrder === 1);
                        const imagePath = image ? image.productImage : 'default.jpg';
                        const itemPrice = product.productPrice * product.quantity;
                        totalPrice += itemPrice;

                        const cartItemHtml = `
                            <div class="cart-item">
                                <div class="item-detail">
                                    <img class="img-list li-text"
                                        src="\${contextPath}/template/web/img/product/\${imagePath}"
                                        alt="\${product.productName}"> 
                                    <span class="product__text">\${product.productName}</span>
                                    <span class="product-quanity">x \${product.quantity}</span>
                                </div>
                                <div class="product__price">
                                    \${itemPrice.toLocaleString('vi-VN')} ₫
                                </div>
                            </div>
                        `;
                        container.append(cartItemHtml);
                    });

                    $(".total__price").text(totalPrice.toLocaleString('vi-VN') + ' ₫');
                } else {
                    container.append('<p class="empty-cart">Giỏ hàng của bạn đang trống.</p>');
                    $(".total__price").text('0 ₫');
                }
            },
            error: function (xhr) {
                console.error("Lỗi khi tải giỏ hàng:", xhr);
                $(".product-container").empty().append('<p class="empty-cart">Không thể tải giỏ hàng. Vui lòng thử lại sau.</p>');
                $(".total__price").text('0 ₫');
            }
        });
        
        $.ajax({
            type: "GET",
            url: "/api/shipping",
            success: function (response) {
                const shippingInfo = response.data;
                const formShipping = $(".form-control");                           
                shippingInfo.forEach(info => {                     
                    const shippingItemHtml = `
                        <option value="\${info.shippingId}">\${info.shippingName} - \${info.shippingAddress} - \${info.shippingPhone}</option>
                    `;
                    formShipping.append(shippingItemHtml);
                });                     
            },
            error: function (xhr) {
                console.error("Lỗi khi tải thông tin:", xhr);              
            }
        });

        $('.payment-choice').click(function() {
            $('.payment-choice').removeClass('selected');
            $(this).addClass('selected');
            $('.payment-details').hide();
            const method = $(this).data('method');
            $(`#detail-${method}`).slideDown(300);
        });
    });
    
    function submitPayment() {
    	
    	  const shippingId = $("#shipping_address").val();
    	  const selectedMethod = $(".payment-choice.selected").data("method");

    	  if (!shippingId) {
    	    alert("Vui lòng chọn địa chỉ giao hàng");
    	    return;
    	  }

    	  if (!selectedMethod) {
    	    alert("Vui lòng chọn phương thức thanh toán");
    	    return;
    	  }

    	  const button = $(".btn-pay");
    	  button.prop("disabled", true);
    	  
    	  const payload = {
    	    shippingId: parseInt(shippingId),
    	    items: cartItems
    	  };

	    	  $.ajax({
	    	    url: "/api/payment",
	    	    type: "POST",
	    	    contentType: "application/json",
	    	    data: JSON.stringify(payload),
	    	    success: function (res) {
	    	      const orderId = res.data.orderId;
	    	      $.ajax({
	    	          url: `/api/payment/vnpay/\${orderId}`,
	    	          type: "GET",
	    	          success: function (response) {
	    	            const vnpayUrl = response.data;	    	           	               	            
	    	             	window.location.href = vnpayUrl; 	    	          
	    	          },
	    	          error: function (err2) {
	    	            console.error(err2);
	    	          }
	    	        });
	 
	    	    },
	    	    error: function (err) {
	    	      
	    	    }
	    	  });
	    	}

  </script>
</body>
</html>