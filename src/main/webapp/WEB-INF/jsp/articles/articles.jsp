<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>经验列表</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<%--顶部 --%>
<nav class="navbar navbar-default" role="navigation">
    <%--顶部导航栏 --%>
    <div class="navbar-header">
        <a class="navbar-brand" href="#">
            <span class="glyphicon glyphicon-article">经验列表</span>
        </a>
    </div>

</nav>
<%-- 数据表格 --%>
<div class="table-responsive">
    <table class="table table-hover" id="articleTable">
        <%-- 设置表头 --%>
        <thead>
        <tr>
            <th>经验标题</th>
            <th>所属标签</th>
            <th>作者id</th>
            <th>发表时间</th>
            <th>内容</th>
            <th>回复时间</th>
            <th>浏览量</th>
        </tr>
        </thead>

        <%-- 设置数据 --%>
        <tbody>
        <c:forEach var="article" items="${articles}">
            <tr id="${article.id}">
                <td><a href="/article/${article.id}">${article.title}</a></td>
                <td>${article.tid}</td>
                <td>${article.aid}</td>
                <td>${article.addTime}</td>
                <td>${article.content}</td>
                <td>${article.reTime}</td>
                <td>${article.viewCount}</td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
    </div>
</body>
</html>
