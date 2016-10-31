<%--
  Created by IntelliJ IDEA.
  User: Mo
  Date: 2016/9/25
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>主贴</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
获取的数据为：<br/>
${topic} <br />

<c:forEach var="reply" items="${replies}">
    ${reply.content} <br />

</c:forEach>
<!-- 顶部信息栏 -->
<div class="center-block">


</div>

<!-- 中部主要内容区 -->
<div>


</div>


<!-- 下方评论区 -->


</body>
</html>
