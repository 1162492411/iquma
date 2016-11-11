
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>教程列表</title>
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
            <span class="glyphicon glyphicon-tutorial">教程列表</span>
        </a>
    </div>

</nav>
<%-- 数据表格 --%>
<div class="table-responsive">
    <table class="table table-hover" id="tutorialTable">
        <%-- 设置表头 --%>
        <thead>
        <tr>
            <th>教程标题</th>
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
        <c:forEach var="tutorial" items="${tutorials}">
            <tr id="${tutorial.id}">
                <td><a href="/tutorial/${tutorial.id}">${tutorial.title}</a></td>
                <td>${tutorial.tid}</td>
                <td>${tutorial.aid}</td>
                <td>${tutorial.addTime}</td>
                <td>${tutorial.content}</td>
                <td>${tutorial.reTime}</td>
                <td>${tutorial.viewCount}</td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
    </div>
</body>
</html>
