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
      href="${pageContext.request.contextPath}/template/img/our-damn-logo.ico" sizes="32x32" />

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

<title>Fingurin - Đăng nhập</title>

</head>
<body>
	<div class="login">
		<div class="box">
			
				<div class="login__box login__part">
				<p>${ status_login }</p>
					<img src="${pageContext.request.contextPath}/template/web/img/logo.webp" alt="">
					<b>Chào mừng quay lại</b> <span>Chúng tôi nhớ bạn lắm rồi đấy!</span> 
						<input type="text" placeholder="Nhập Email người dùng" />
						<input type="password" placeholder="Mật khẩu"/>
					<a href="/firgure-shop/forgot-password" class="forgot__btn">Quên
						mật khẩu?</a>
					<button type = "submit" class="price__button__add price__button--hover li-text">
						<span>Đăng nhập</span>
					</button>
					<div class="register-btn btn">
						Không có tài khoản, <span> <a href="/firgure-shop/sign-up">
								đăng kí</a>
						</span> nào!
					</div>
				</div>
		
		</div>
	</div>

</body>
<script src="<c:url value = '/template/web/js/login.js'/>"></script>
</html>