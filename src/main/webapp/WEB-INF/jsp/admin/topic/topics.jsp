<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>主贴列表</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/jqPagination.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jqPagination.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/jqPagination.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/iquma.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            initPage();
        });
    </script>
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
            <%-- 分页插件--%>
            <tbody id="Pagination">
            </tbody>


            <div class="pagination">
                <a href="#" class="first" data-action="first">&laquo;</a>
                <a href="#" class="previous" data-action="previous">&lsaquo;</a>
                <input type="text"  />
                <a href="#" class="next" data-action="next">&rsaquo;</a>
                <a href="#" class="last" data-action="last">&raquo;</a>
            </div>

    </table>
</div>
</body>
</html>
