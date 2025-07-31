<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/template/img/our-damn-logo.ico"
	sizes="32x32" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/web/css/navbar.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/web/css/register.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/web/font/Quicksand/quicksand.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/web/font/Qicksand-Bold/Quicksand-Bold.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/web/fontawesome-free-6.2.0-web/css/all.css">

<title>Fingurin - Đăng ký tài khoản</title>
<script src="asset/js/login.js" defer></script>
</head>
<body>
	<div class="login">
		<div class="transit"></div>
		<div class="transit trans2"></div>
		<div class="transit trans3"></div>
		<div class="box">
		
			<div class="login__box register__part">
				<img src="${pageContext.request.contextPath}/template/web/img/logo.webp" alt="">
				 <b>Bạn là người mới
					sao?</b> <span class = "title__register">Tạo tài khoản để ngắm figure nào!</span> <input
					class="regis--user" type="text" placeholder="Tài khoản"> <input
					class="regis--pass" type="password" placeholder="Mật khẩu">
				<input class="regis--pass2" type="password"
					placeholder="Xác nhận mật khẩu"> <input
					class="regis--gmail" type="text" placeholder="Gmail" value="">

				<button
					class="price__button__add price__button--hover li-text regis--btn">
					<span class = "button__register">Đăng kí</span>
				</button>

				<div class="login-btn btn">
					Có tài khoản hả, <span> <a href="/login">
								đăng nhập</a>
						</span> đi!
				</div>
			</div>

		
		</div>
	</div>

</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(document).ready(function() {
  $('.regis--btn').on('click', function(e) {
    e.preventDefault(); 


    const username = $('.regis--user').val().trim();
    const password = $('.regis--pass').val();
    const confirmPassword = $('.regis--pass2').val();
    const gmail = $('.regis--gmail').val().trim();

    // Kiểm tra đơn giản
    if (!username || !password || !confirmPassword || !gmail) {
      alert("Vui lòng nhập đầy đủ thông tin!");
      return;
    }

    if (password !== confirmPassword) {
      alert("Mật khẩu và xác nhận không khớp!");
      return;
    }

   
    $.ajax({
      url: '/api/register',  
      type: 'POST',
      contentType: 'application/json',
      data: JSON.stringify({
        userName: username,
        userPassword: password,
        userEmail: gmail,
        userProvider: "LOCAL"
      }),
      success: function(response) {
        alert('Đăng ký thành công!');
        // Optional: window.location.href = '/login';
      },
      error: function(xhr) {
        alert('Đăng ký thất bại: ' + xhr.responseText);
      }
    });
  });
});
</script>
</html>