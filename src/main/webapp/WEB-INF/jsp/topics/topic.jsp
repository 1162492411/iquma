<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>主贴</title>
    <jsp:include page="/common/base.jsp"/>
    <jsp:include page="/common/editor.jsp"/>
    <script type="text/javascript">
        $(document).ready(function () {
            initAtt(${topic.attachment});
            initTopicInfo();
            initReplies(${replies}, ${topic.hasBest});
            handleBestReply();
            initPag(${currentPage}, ${totalPage}, "repliesPagination", getTopicPath());
        });
    </script>
</head>
<body>
<jsp:include page="/common/bannar.jsp"/>
<!-- 隐藏数据区 -->
<div>
    <input type="hidden" id="condition_sort" value="${sort}"/>
    <input type="hidden" id="condition_id" value="${topic.id}"/>
    <input type="hidden" id="condition_title" value="${topic.title}"/>
    <input type="hidden" id="condition_isBlock" value="${topic.isBlock}"/>
    <input type="hidden" id="condition_sec" value="${topic.sec}"/>
    <input type="hidden" id="condition_uid" value="${userid}"/>
    <input type="hidden" id="condition_aid" value="${topic.aid}"/>
</div>
<!-- 隐藏数据区结束 -->
<%--<!-- 主数据区 -->--%>
<main role="main">
    <div class="wrap">
        <!-- 数据区顶部 -->
        <div class="post-topheader">
            <div class="container">
                <div class="row">
                    <!-- 数据区顶部-主贴顶部 -->
                    <div class="col-md-9 col-sm-8 col-xs-12">
                        <span class="post-topheader__title--icon-symbol" id="topicText"></span>
                        <div class="post-topheader__info">
                            <h1 class="h3 post-topheader__info--title" id="questionTitle">
                                <a href="${pageContext.request.contextPath}/${topic.sec}/${topic.id}">${topic.title}</a>
                            </h1>
                            <ul class="taglist--inline inline-block question__title--tag mr10">
                                <li class="tagPopup mb5">
                                    <span class="tag">${topic.tag.name}</span>
                                </li>
                            </ul>
                            <div class="question__author">
                                <a href="${pageContext.request.contextPath}/user/${topic.aid}/home" class="mr5">
                                    <strong>${topic.user.name}</strong>
                                </a>
                                <span class="hidden-xs"></span>
                            </div>
                        </div>
                    </div>
                    <!-- 数据区顶部-主贴顶部结束 -->
                </div>
            </div>
        </div><!-- 数据区顶部结束 -->
        <!-- 数据区 主贴信息 -->
        <div class="container mt30">
            <div class="row">
                <div class="col-xs-12 col-md-9 main">
                    <article class="widget-question__item">
                        <div class="post-col">
                            <div class="widget-vote">
                                <shiro:hasPermission name="${topic.sec}:like">
                                    <button class="like" id="like-0"
                                            onclick="likeTopic('${topic.sec}','${topic.id}')"></button>
                                </shiro:hasPermission>
                                <span class="count">${topic.rateCount}</span>
                                <shiro:hasPermission name="${topic.sec}:hate:${topic.id}">
                                    <button class="hate" id="hate-0"
                                            onclick="hateTopic('${topic.sec}','${topic.id}')"></button>
                                </shiro:hasPermission>
                            </div><!-- end .widget-vote -->
                        </div>
                        <!-- 数据区-主贴-底部信息 -->
                        <div class="post-offset">
                            <div class="question fmt" id="topic-content">
                                ${topic.content}
                            </div>
                            <!-- 主贴底部操作信息 -->
                            <div class="row">
                                <div class="post-opt col-md-8">
                                    <ul class="list-inline mb0" id="topicUl">
                                        <li><strong class="no-stress">${topic.viewCount}</strong> 浏览</li>
                                        <li><a id="topicAddTime">
                                            <script type="text/javascript">$("#topicAddTime").append(formateTime('${topic.addTime}'));</script>
                                        </a></li>
                                        <shiro:hasPermission name="${topic.sec}:update:${topic.id}">
                                            <input type="hidden" id="hasTopicUpdatePermission" value="true"/>
                                        </shiro:hasPermission>
                                        <shiro:hasPermission name="${topic.sec}:block:${topic.id}">
                                            <input type="hidden" id="hasTopicBlockPermission" value="true"/>
                                        </shiro:hasPermission>
                                        <shiro:hasPermission name="${topic.sec}:delete:${topic.id}">
                                            <input type="hidden" id="hasTopicDeletePermission" value="true"/>
                                        </shiro:hasPermission>
                                    </ul>
                                </div>
                            </div>
                        </div><!-- 数据区-主贴-底部信息结束 -->
                    </article>
                    <!-- 回复列表 -->
                    <div class="widget-answers" id="repliesDiv">
                        <!-- 回复排序方式 -->
                        <div class="btn-group pull-right" role="group" id="replySortDiv">
                            <a class="btn btn-default btn-xs" id="defaultSort">默认排序</a>
                            <a class="btn btn-default btn-xs" id="timeSort">时间排序</a>
                        </div>
                        <!-- 回复排序方式结束 -->
                        <h2 class="title h4 mt30 mb20 post-title" id="answers-title">${total}个回答</h2>
                        <!-- 回复列表隐藏数据区 -->
                        <input type="hidden" id="bestReplyId" value="${topic.hasBest}"/>
                        <shiro:hasPermission name="reply:like:${topic.id}">
                            <input type="hidden" id="hasReplyLikePermission" value="true"/>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="reply:hate:${topic.id}">
                            <input type="hidden" id="hasReplyHatePermission" value="true"/>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="reply:delete:${topic.id}">
                            <input type="hidden" id="hasReplyDeletePermission" value="true"/>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="reply:block:${topic.id}">
                            <input type="hidden" id="hasReplyBlockPermission" value="true"/>
                        </shiro:hasPermission>
                        <c:if test="${topic.hasBest eq 0 && topic.aid eq userid && topic.isBlock eq false }">
                            <input type="hidden" id="hasReplyAdoptPermission" value="true"/>
                        </c:if>
                        <div id="replies"></div>
                        <!-- 回复列表隐藏数据区结束 -->
                        <nav>
                            <ul class="pagination" id="repliesPagination"></ul>
                        </nav>
                    </div>
                    <!-- 回复列表结束 -->
                    <!-- 添加回复区 -->
                </div>
                <!-- 主数据区结束 -->
            </div>
        </div>
    </div>
</main>
<jsp:include page="/common/footer.jsp"/>
</body>
</html>
