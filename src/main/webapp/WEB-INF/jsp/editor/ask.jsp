<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>提问</title>
    <jsp:include page="/common/base.jsp" />
    <jsp:include page="/common/editor.jsp" />
    <script type="text/javascript">
        $(function () {
            initTagSelection();
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
                <input type="text" class="form-control" id="title" placeholder="话题标题" required />
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
