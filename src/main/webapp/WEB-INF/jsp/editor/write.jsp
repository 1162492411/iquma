<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>经验分享</title>
    <jsp:include page="/common/base.jsp" />
    <jsp:include page="/common/editor.jsp" />
    <script type="text/javascript">
        $(function () {
            initTagSelection();
            editorSubmit('write');
        });
    </script>
</head>
<body>
<shiro:hasPermission name="article:create">
<form class="form-group">
    <div class="panel panel-default">
        <div class="panel-heading">经验</div>
        <div class="panel-body" id="TopicBody">
            <%--话题标题 --%>
            <div class="form-group" id="titlePanel">
                <label>经验标题</label>
                <input type="text" class="form-control" id="title" placeholder="经验标题"/>
            </div>
             <%--标签id --%>
             <div class="form-group" id="tagPanel">
                 <label>标签</label>
                 <input type="hidden" id="tid" value="" />
                 <select id="firstLevel"></select>
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
                <input type="button" class="btn btn-primary" value="分享" id="subBtn" />
                <input type="reset" class="btn btn-default" value="重置"/>
            </div>
        </div>
    </div>
    </div>
</form>
</shiro:hasPermission>
</body>
</html>
