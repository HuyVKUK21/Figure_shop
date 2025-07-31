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

<!-- Styles -->
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
				<div class="login__header">
					<img
						src="${pageContext.request.contextPath}/template/web/img/logo.webp"
						alt="Logo">
					<div class="login__title">
						<b>Chào mừng quay lại</b> <span>Chúng tôi nhớ bạn lắm rồi
							đấy!</span>
					</div>
				</div>

				<form id="loginForm" method="post" action="#">
					<div class="form-group">
						<input type="text" id="username" name="username"
							placeholder="Nhập Email người dùng" required />
					</div>
					<div class="form-group">
						<input type="password" id="password" name="password"
							placeholder="Mật khẩu" required />
					</div>

					<a href="/forgot-password" class="forgot__btn">Quên mật khẩu?</a>

					<div class="login__buttons">
						<button type="submit" class="btn primary price__button__add">
							<span>Đăng nhập</span>
						</button>

						<div class="separator">Hoặc</div>

						<a class="google-login-btn" href="/oauth2/authorization/google">
							<i class="fab fa-google"></i> <span>Đăng nhập bằng Google</span>
						</a>

					</div>
				</form>

				<div id="error-message"></div>

				<div class="register-btn">
					Không có tài khoản, <span><a href="/register">đăng kí</a></span>
					nào!
				</div>
			</div>
		</div>
	</div>

</body>

<!-- JS -->
<script src="<c:url value='/template/web/js/login.js'/>"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document)
			.ready(
					function() {
						$("#loginForm")
								.submit(
										function(event) {
											event.preventDefault();

											var userData = {
												username : $("#username").val(),
												password : $("#password").val()
											};

											$
													.ajax({
														type : "POST",
														contentType : "application/json",
														url : "/api/login",
														data : JSON
																.stringify(userData),
														dataType : 'json',
														success : function(
																response) {
							
															window.location.href = "/user/home";
														},
														error : function(xhr) {
															$("#error-message")
																	.text(
																			"Đăng nhập thất bại: "
																					+ (xhr.responseJSON ? xhr.responseJSON.message
																							: "Lỗi không xác định"));
														}
													});
										});

					});
</script>
</html>
