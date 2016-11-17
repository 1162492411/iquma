<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>主贴列表</title>
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
            <span class="glyphicon glyphicon-topic">主贴列表</span>
        </a>
    </div>
</nav>

<%-- 数据表格 --%>
<div class="table-responsive">
    <table class="table table-hover" id="topicTable">
        <%-- 设置表头 --%>
        <thead>
        <tr>
            <th>主贴id</th>
            <th>主贴标题</th>
            <th>版块id</th>
            <th>标签id</th>
            <th>作者id</th>
            <th>发布时间</th>
            <th>主贴内容</th>
            <th>最后回复</th>
            <th>浏览量</th>
            <th>评价分数</th>
            <th>主贴状态</th>
        </tr>
        </thead>

        <%-- 设置数据 --%>
        <tbody>
        <c:forEach var="topic" items="${topics}">
            <tr id="${topic.id}">
                <td><a href="topic/${topic.id}">${topic.id}</a></td>
                <td>${topic.title}</td>
                <td>${topic.sid}</td>
                <td>${topic.tid}</td>
                <td>${topic.aid}</td>
                <td>${topic.addTime}</td>
                <td>${topic.content}</td>
                <td>${topic.reTime}</td>
                <td>${topic.viewCount}</td>
                <td>${topic.rateCount}</td>
                <td>${topic.isBlock}</td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
</div>
</body>
</html>
