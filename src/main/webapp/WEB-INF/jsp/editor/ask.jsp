<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>提问</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/editor/css/wangEditor.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/editor/js/wangEditor.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/xss.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/iquma.js"></script>
    <script type="text/javascript">
        $(function () {
            initTypeSelection();
            $("#typeIdSelection").change(function () {
                updateTagSelection($('#typeIdSelection option:selected').val());
            });
            editorSubmit('ask');
        });
    </script>
</head>
<body>
<!-- 提问 -->
<shiro:hasPermission name="question:create">
<form class="form-group">
    <input type="hidden" name="_method" value="PUT"/>
    <div class="panel panel-default">
        <div class="panel-heading">提问</div>
        <div class="panel-body" id="TopicBody">
            <%--话题标题 --%>
            <div class="form-group" id="titlePanel">
                <label>话题标题</label>
                <input type="text" class="form-control" id="title" placeholder="话题标题"/>
            </div>
            <%--类别id --%>
            <div class="form-group" id="typeIdPanel">
                <label>类别</label>
                <select id="typeIdSelection">
                </select>
            </div>
            <%--标签id --%>
            <div class="form-group" id="tidPanel">
                <label>标签</label>
                <select name="tid" id="tidSelection">
                </select>
            </div>
            <%--作者id --%>
            <input type="hidden" id="aid" value="${userid}"/>
            <%-- 内容--%>
            <div class="form-group" id="contentPanel">
                <label>内容</label>
                <div name="content" id="contentDiv" style="height: 45%"></div>
            </div>
            <%-- 按钮 --%>
            <div class="form-group" id="buttonPanel">
                <input type="button" class="btn btn-primary" value="提问" id="subBtn"/>
                <input type="reset" class="btn btn-default" value="重置"/>
            </div>
        </div>
    </div>
    </div>
</form>
</shiro:hasPermission>
</body>
</html>
