<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>教程列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/qa.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/responsive.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/zhihu.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/common/bannar.jsp"/>
<main>
    <div class="wrap">
        <div class="container">
            <div class="row">
                <!-- 主数据区 -->
                <div class="col-xs-12 col-md-9 main">
                    <!-- 筛选方式 -->
                    <ul class="nav nav-tabs nav-tabs-zen mb10 mt30">
                        <li><a href="../new/1">最新的</a></li>
                        <li  class="active"><a href="../hottest/1">高评分的</a></li>
                        <li><a href="../unanswered/1">未回答的</a>
                        </li>
                    </ul>
                    <!-- 筛选方式结束 -->
                    <!-- 主贴列表 -->
                    <div class="stream-list question-stream">
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
                                            <a href="${pageContext.request.contextPath}/user/${topic.user.id}">${topic.user.name}</a>
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
                    </div>

                    <%-- 分页按钮 --%>
                    <nav>
                        <ul class="pagination">
                            <li class="disabled">
                      <span>
                        <span aria-hidden="true"> << </span>
                      </span>
                            </li>
                            <li class="active">
                            <li><span> <a href="./1">1</a></span></li>
                            </li>
                            <li><a href="./2">2</a></li>
                            <li><a href="./3">3</a></li>
                            <li><a href=./4>4</a></li>
                            <li><a href="./5">5</a></li>
                            <li >
                                <span aria-hidden="true"> >> </span>
                            </li>
                        </ul>
                    </nav>

                    <!-- 主贴列表结束 -->
                </div>

                <!--  主数据区结束 -->
                <!-- 主数据区右侧 -->
                <div class="col-xs-12 col-md-3 side">
                    <!-- 相似内容 -->
                    <div class="widget_headline widget-box mt20 mb30">
                        <h2 class="h4 widget-box__title">相似问题</h2>
                        <ul class="taglist--block pl20">
                            <!-- 单项相似数据 -->
                            <li class="">
                                <a href="${pageContext.request.contextPath}/p/1210000008300936"
                                   data-id="1210000008300936" data-original-title="">前端基础进阶：详细图解JavaScript内存空间</a>
                                <p class=""><span class="mr5 color_F5A623">43 赞</span> | <span class="ml5">8 评论</span>
                                </p>
                            </li><!-- 单项相似数据结束 -->
                            <a href="${pageContext.request.contextPath}/news" target="_blank" class="f12">更多→</a>
                        </ul>
                    </div><!-- 相似内容结束 -->
                </div><!-- 主数据区右侧结束 -->
            </div>
        </div>
    </div>
</main>

<jsp:include page="${pageContext.request.contextPath}/common/footer.jsp"/>
</body>
</html>
