<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>修改教程</title>
    <jsp:include page="/common/base.jsp" />
    <jsp:include page="/common/editor.jsp" />
    <script type="text/javascript">
        $(function () {
            updateTopic();
        });
    </script>
</head>
<body>
<%-- 修改教程 --%>
<shiro:hasPermission name="${topic.sec}:update:${topic.id}">
    <form id="updateTopicForm">
        <div class="panel panel-default">
            <div class="panel-heading">修改教程</div>
            <div class="panel-body" id="topicPanel">
                <input type="hidden" id="id" value="${topic.id}"/>
                <input type="hidden" id="aid" value="${topic.aid}"/>
                <input type="hidden" id="sec" value="${topic.sec}"/>
                <%--主贴标题 --%>
                <div class="form-group" id="titlePanel">
                    <label>主贴标题</label>
                    <input type="text" class="form-control" id="title" value="${topic.title}"
                           placeholder="话题标题"/>
                </div>
                <%-- 内容--%>
                <div class="form-group" id="contentPanel">
                    <label>主贴内容</label>
                    <div id="contentDiv">${topic.content}</div>
                </div>
                <!-- 按钮 -->
                <div class="form-group" id="buttonPanel">
                    <input type="button" id="updateButton" class="btn btn-primary" value="修改"/>
                    <input type="reset" class="btn btn-default" value="重置"/>
                </div>
            </div>
        </div>
        </div>
    </form>
</shiro:hasPermission>
<%--</c:if>--%>
</body>
</html>
