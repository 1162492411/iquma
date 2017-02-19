<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>提问列表</title>
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
            var condition = {
                "sid" : Number($("#condition_sid").val()),
                "tid" : Number($("#condition_tid").val())
            };
            initPages(condition,"discuss");
        });
    </script>
</head>
<body>
<input type="hidden" id="condition_sid" value="${topic.sid}" />
<input type="hidden" id="condition_tid" value="${topic.tid}" />
<%--顶部 --%>
<nav class="navbar navbar-default" role="navigation">
    <%--顶部导航栏 --%>
    <div class="navbar-header">
        <a class="navbar-brand" href="#">
            <span class="glyphicon glyphicon-discuss">提问列表</span>
        </a>
    </div>

</nav>
<%-- 数据表格 --%>
<div class="table-responsive">
    <table class="table table-hover" id="discussTable">
        <%-- 设置表头 --%>
        <thead>
        <tr>
            <th>标题</th>
            <th>作者</th>
            <th>投票</th>
            <th>浏览</th>
            <th>标签</th>
            <th>最后回复</th>
        </tr>
        </thead>

        <%-- 测试分页插件--%>
        <tbody id="Pagination">
        </tbody>

        <%-- 分页按钮 --%>
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
