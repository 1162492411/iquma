<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>教程列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/iquma.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/obsidian.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/iquma.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/highlight.pack.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            initListStatus();
            initListPag(${currentPage},${totalPage},getTypesPath());
            initTags(${tags});
        });
    </script>
</head>
<body>
<jsp:include page="/common/bannar.jsp"/>
<main>
    <!-- 隐藏数据区 -->
    <input type="hidden" id="sec" value="${sec}" />
    <input type="hidden" id="tag" value="${tag}" />
    <input type="hidden" id="type" value="${type}" />
    <!-- 隐藏数据区结束 -->
    <div class="wrap">
        <div class="container">
            <%--<div class="row">--%>
                <!-- 主数据区 -->
                <div class="col-xs-12 col-md-9 main">
                    <!-- 筛选方式 -->
                    <ul class="nav nav-tabs nav-tabs-zen mb10 mt30">
                            <li id="nav_new" class="active"><a id="nav_new_a">最新的</a></li>
                            <li id="nav_hottest"><a id="nav_hottest_a">高评分的</a></li>
                            <li id="nav_unanswered"><a id="nav_unanswered_a">未回答的</a>
                        </li>
                    </ul>
                    <!-- 筛选方式结束 -->
                    <!-- 主贴列表 -->
                    <div class="stream-list question-stream" id="topic-list">
                        <input type="hidden" id="searchEmpty" value="${searchEmpty}" />
                            <script type="text/javascript">
                                $(document).ready(function () {
                                    if($("#searchEmpty").val() == 'true') $("#topic-list").append("查询的数据不存在~");
                                    else initTopics(${topics});
                                });
                            </script>
                    </div>
                    <%-- 分页按钮 --%>
                    <nav><ul class="pagination" id="topicsPagination"></ul></nav>
                    <!-- 主贴列表结束 -->
                </div>
                <!-- 主贴列表右侧 -->
                <div class="col-xs-12 col-md-3 side">
                    <div class="weight-box">
                        <h2 class="h4 widget-box__title">相关标签</h2>
                        <ul class="taglist--inline multi" id="tag-list"></ul>
                    </div>
                </div>
                <!-- 主贴列表右侧结束 -->
                <!--  主数据区结束 -->
            <%--</div>--%>
        </div>
    </div>
</main>

<jsp:include page="/common/footer.jsp"/>
</body>
</html>
