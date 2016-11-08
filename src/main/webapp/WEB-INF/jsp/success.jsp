<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<html>
<head>
    <title>成功页面</title>
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
