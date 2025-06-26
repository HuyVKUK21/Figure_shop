<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/template/web/img/our damn logo.png" sizes="32x32" />
    
    <title><sitemesh:write property="title"/></title>

    <!-- CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/web/css/navbar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/web/css/home.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/web/font/Quicksand/quicksand.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/web/font/Qicksand-Bold/Quicksand-Bold.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/web/fontawesome-free-6.2.0-web/css/all.css">
</head>

<body>
    <div class="cursor-round"></div>
    <div class="main"></div>
    <div class="slider" data-speed="">
        <!-- Header -->
        <%@ include file="/common/web/header.jsp" %>

        <!-- Page Content injected here -->
        <sitemesh:write property="body"/>

        <!-- Footer -->
        <%@ include file="/common/web/footer.jsp" %>
    </div>

    <!-- JavaScript -->
    <script src="${pageContext.request.contextPath}/template/web/js/home.js"></script>
    <script src="${pageContext.request.contextPath}/template/web/js/parallax.js"></script>
    <script src="${pageContext.request.contextPath}/template/web/js/cursor.js"></script>
    <script src="${pageContext.request.contextPath}/template/web/js/responsive.js"></script>
</body>
</html>
