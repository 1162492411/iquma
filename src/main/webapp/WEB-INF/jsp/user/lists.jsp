<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户主页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/iquma.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/obsidian.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/iquma.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/highlight.pack.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            initProHeaders(${user.id},'${topic.sec}');
            initLists(${topics});
            hljs.initHighlightingOnLoad();
        });
    </script>
</head>
<body class="Entry-body">
<input type="hidden" id="condition-topicType" value="${topic.sec}" />
<div id="root">
    <div>
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
                            <div class="List" id="Profile-${topic.sec}s">
                            </div><!-- 用户动态详细数据区结束 -->
                        </div><!-- 用户动态数据区左侧结束 -->
                    </div><!-- 用户动态数据区结束 -->
                </div>
            </div>
        </main>
        <jsp:include page="/common/footer.jsp" />
    </div>
</div>
</body>
</html>
