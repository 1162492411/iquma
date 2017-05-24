<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>用户主页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/iquma.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/obsidian.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/highlight.pack.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/iquma.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            initProHeaders(${user.id},'collection');
            if($("#searchStatus").val() == 'true')
                $("#Profile-collections").append("不存在相关内容~");
            else
                initCollections(${collections});
            hljs.initHighlightingOnLoad();
        });
    </script>
</head>
<body class="Entry-body">
<jsp:include page="/common/bannar.jsp" />
<main role="main" class="App-main">
    <div>
        <jsp:include page="/common/profileCard.jsp" />
        <!-- 用户动态数据区-->
        <div class="Profile-main">
            <!-- 用户动态数据区左侧 -->
            <div class="Profile-mainColumn" style="width: 1000px;">
                <div class="Card ProfileMain" id="ProfileMain">
                    <!-- 用户动态详细数据区 -->
                    <div class="List" id="Profile-collections">
                        <input type="hidden" id="searchStatus" value="${emptyResult}"/>
                    </div><!-- 用户动态详细数据区结束 -->
                </div><!-- 用户动态数据区左侧结束 -->
            </div><!-- 用户动态数据区结束 -->
        </div>
    </div>
</main>
<jsp:include page="/common/footer.jsp" />
</body>
</html>
