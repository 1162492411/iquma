<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>趣码网</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/qa.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/responsive.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/zhihu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/iquma.css">
</head>
<body>
<jsp:include page="common/bannar.jsp" />

<a href="http://localhost:8080/user/loginValidator?id=14010101&pass=010101" >14010101账户登录</a>
<a href="http://localhost:8080/user/loginValidator?id=14010102&pass=010102" >14010102账户登录</a>

<jsp:include page="common/footer.jsp" />
</body>
</html>
