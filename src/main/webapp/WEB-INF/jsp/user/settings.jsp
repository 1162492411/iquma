<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">

	<title>My JSP 'settings.jsp' starting page</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet"
		  href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	<script src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</head>

<body>
<form action="${pageContext.request.contextPath}/user/settings/profile">
	<input type="hidden" name="uid"  value="${uid}" />
	<input type="submit" value="个人资料" />
</form>

<form action="${pageContext.request.contextPath}/user/settings/account">
	<input type="hidden" name="uid"  value="${uid}" />
	<input type="submit" value="帐号和密码" />
</form>

<form action="${pageContext.request.contextPath}/user/settings/forgot">
	<input type="hidden"  name="uid"  value="${uid}" />
	<input type="submit" value="重置密码" />
</form>
</body>
</html>
