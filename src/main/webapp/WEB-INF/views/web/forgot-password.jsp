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
	href="${pageContext.request.contextPath}/template/web/css/login.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/web/font/Quicksand/quicksand.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/web/font/Qicksand-Bold/Quicksand-Bold.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/template/web/fontawesome-free-6.2.0-web/css/all.css">

<title>Fingurin - Quên mật khẩu</title>
<script src="asset/js/login.js" defer></script>
</head>
<body>
	<div class="login">
		<div class="transit"></div>
		<div class="transit trans2"></div>
		<div class="transit trans3"></div>
		<div class="box">

			<div class="login__box forgot__part">
				<img src="${pageContext.request.contextPath}/template/web/img/logo.webp" alt="">
				 <b>Quên mật khẩu à?</b>
				<span>Nhạp vào đây gmail của bạn!</span> <input class="regis--gmail"
					type="text" placeholder="Gmail" value="">

				<button
					class="price__button__add price__button--hover li-text regis--btn">
					<span>Tiếp theo</span>
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
</html>