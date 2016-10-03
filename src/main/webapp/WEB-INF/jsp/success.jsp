<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'success.jsp' starting page</title>


</head>

<body>
这里是操作成功页面
<form action="${pageContext.request.contextPath}/user/home/${uid}">
    <input type="hidden" name="uid" value="${uid }"/>
    <input type="submit" value="前往个人页面"/>
</form>

<form action="${pageContext.request.contextPath}/editor">
    <input type="submit" value="前往编辑器页面"/>
</form>
</body>
</html>
