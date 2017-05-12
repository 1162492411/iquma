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
            initPag(${currentPage},${totalPage});
        });
    </script>
</head>
<body>
<jsp:include page="/common/bannar.jsp"/>
<main>
    <div class="wrap">
        <div class="container">
            <div class="row">
                <!-- 主数据区 -->
                <div class="col-xs-12 col-md-9 main">
                    <!-- 筛选方式 -->
                    <input type="hidden" id="type" value="${type}" />
                    <ul class="nav nav-tabs nav-tabs-zen mb10 mt30">
                        <li id="nav_new" class="active"><a id="nav_new_a">最新的</a></li>
                        <li id="nav_hottest"><a id="nav_hottest_a">高评分的</a></li>
                        <li id="nav_unanswered"><a id="nav_unanswered_a">未回答的</a>
                        </li>
                    </ul>
                    <!-- 筛选方式结束 -->
                    <!-- 主贴列表 -->
                    <div class="stream-list question-stream">
                        <input type="hidden" value="${searchEmpty}" />
                        <%-- 若查询结果为空 --%>
                        <c:if test="${searchEmpty eq true }">
                            查询的数据不存在！
                        </c:if>
                            <%-- 查询结果非空时将其遍历 --%>
                            <c:if test="${searchEmpty eq null}">
                        <c:forEach var = "topic" items="${topics}">
                            <!-- 单项主贴 -->
                            <section class="stream-list__item">
                                <!-- 主贴状态 -->
                                <div class="qa-rank">
                                    <div class="votes hidden-xs">
                                            ${topic.rateCount}<small>评分</small>
                                    </div>
                                    <c:set var="replyCount" value="${topic.replyCount}" />
                                    <c:choose>
                                        <c:when test="${replyCount > 0 }">
                                            <div class="answers answered">
                                                    ${topic.replyCount}<small>回复</small>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="answers">
                                                    ${topic.replyCount}<small>回复</small>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                    <div class="views hidden-xs viewswordgreater999">
                                            ${topic.viewCount}<small>浏览</small>
                                    </div>
                                </div><!-- 主贴状态结束 -->
                                <!-- 主贴简略信息 -->
                                <div class="summary">
                                    <ul class="author list-inline">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/user/${topic.user.id}/home">${topic.user.name}</a>
                                            <span class="split"></span>
                                            <span>3分钟前回答</span>
                                        </li>
                                    </ul>
                                    <h2 class="title"><a href="${pageContext.request.contextPath}/tutorial/${topic.id}">${topic.title}</a></h2>
                                    <ul class="taglist--inline ib">
                                        <li class="tagPopup"><a class="tag tag-sm" href="../new/1">${topic.tag.name}</a></li>
                                    </ul>
                                </div><!-- 主贴简略信息结束 -->
                            </section><!-- 单项主贴结束 -->
                        </c:forEach>
                            </c:if>
                    </div>

                    <%-- 分页按钮 --%>
                    <nav>
                        <ul class="pagination" id="topicsPagination">
                            <li id="pagTop"><a href="./1">首页</a></li>
                            <li id="pagLast"><span>上一页<span></span></li>
                            <li id="pagCurrent" class="active"></li>
                            <li id="pagNext"><span>下一页</span></li>
                            <li id="pagBottom"><a href="./${totalPage}">尾页</a></li>
                        </ul>
                    </nav>

                    <!-- 主贴列表结束 -->
                </div>

                <!--  主数据区结束 -->
            </div>
        </div>
    </div>
</main>

<jsp:include page="/common/footer.jsp"/>
</body>
</html>
