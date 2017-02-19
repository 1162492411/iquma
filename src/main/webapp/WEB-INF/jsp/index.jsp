<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>趣码网</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/qa.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/responsive.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/zhihu.css">
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/common/bannar.jsp" />

<jsp:include page="${pageContext.request.contextPath}/common/footer.jsp" />
</body>