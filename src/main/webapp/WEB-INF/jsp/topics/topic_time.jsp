<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>教程</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/qa.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/responsive.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/zhihu.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/iquma.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            initTopicStatus();
        });
    </script>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/common/bannar.jsp"/>
<!-- 隐藏数据区 -->
<div>
    <input type="hidden" id="condition_tid" value="${topic.id}"/>
    <input type="hidden" id="condition_isBlock" value="${topic.isBlock}"/>
    <input type="hidden" id="topicTitle" value="${topic.title}"/>
    <input type="hidden" id="condition_uid" value="${userid}" />
    <input type="hidden" id="condition_aid" value="${topic.aid}"/>
</div><!-- 隐藏数据区结束 -->
<%--<!-- 主数据区 -->--%>
<main role="main">
    <div class="wrap">
        <!-- 数据区顶部 -->
        <div class="post-topheader">
            <div class="container">
                <div class="row">
                    <!-- 数据区顶部-主贴顶部 -->
                    <div class="col-md-9 col-sm-8 col-xs-12">
                        <span class="post-topheader__title--icon-symbol">
                            <c:if test="${topic.section.name eq 'tutorial'}">
                                教
                            </c:if>
                            <c:if test="${topic.section.name eq 'discuss'}">
                                问
                            </c:if>
                            <c:if test="${topic.section.name eq 'article'}">
                                文
                            </c:if>
                            <c:if test="${topic.section.name eq 'code'}">
                                码
                            </c:if>
                        </span>

                        <div class="post-topheader__info">
                            <h1 class="h3 post-topheader__info--title" id="questionTitle">
                                <a href="${pageContext.request.contextPath}/${topic.section.name}/${topic.id}">${topic.title}</a>
                            </h1>
                            <ul class="taglist--inline inline-block question__title--tag mr10">
                                <li class="tagPopup mb5">
                                    <span class="tag">javascript</span>
                                </li>
                            </ul>
                            <div class="question__author">
                                <a href="${pageContext.request.contextPath}/user/${topic.aid}/home" class="mr5">
                                    <strong>${topic.user.name}</strong>
                                </a>
                                <span class="hidden-xs"></span>
                            </div>
                            <br />
                            <input type="hidden" style="color: #990055" id="blockTip" value="该帖已被关闭！！" disabled />
                        </div>
                    </div><!-- 数据区顶部-主贴顶部结束 -->
                </div>
            </div>
        </div><!-- 数据区顶部结束 -->

        <!-- 数据区 主贴信息 -->
        <div class="container mt30">
            <div class="row">
                <div class="col-xs-12 col-md-9 main">
                    <article class="widget-question__item">
                        <div class="post-col">
                            <div class="widget-vote  ">
                                <button class="like" onclick="likeTopic()"></button>
                                <span class="count">${topic.rateCount}</span>
                                <button class="hate" onclick="hateTopic()"></button>
                            </div>
                            <!-- end .widget-vote -->
                        </div>
                        <!-- 数据区-主贴-底部信息 -->
                        <div class="post-offset">
                            <div class="question fmt">
                                ${topic.content}
                            </div>
                            <div class="row">
                                <div class="post-opt col-md-8">
                                    <ul class="list-inline mb0">
                                        <li><strong class="no-stress">${topic.viewCount}</strong> 浏览</li>
                                        <li><a>
                                            <fmt:formatDate value="${topic.addTime}"
                                                            pattern="yyyy-MM-dd"/>
                                        </a></li>
                                        <shiro:hasPermission name="${topic.section.name}:update:${topic.id}">
                                            <a href="${pageContext.request.contextPath}/${topic.section.name}/${topic.id}/update">
                                                <Button class="btn btn-primary">编辑</Button>
                                            </a>
                                            </li>
                                        </shiro:hasPermission>
                                        <shiro:hasPermission name="${topic.section.name}:block:${topic.id}">
                                            <li>
                                                <Button class="btn btn-primary" id="blockButton" onclick="blockTopic(${topic.section.name})" disabled>关闭</Button>
                                            </li>
                                        </shiro:hasPermission>
                                        <shiro:hasPermission name="${topic.section.name}:delete:${topic.id}">
                                            <li>
                                                <Button class="btn btn-danger"
                                                        onclick="deleteTopic(${topic.section.name})">删除</Button>
                                            </li>
                                        </shiro:hasPermission>
                                        <shiro:user>
                                            <li>
                                                <Button class="btn btn-primary" id="favoriteButton" onclick="favoriteTopic(${topic.section.name})" disabled>收藏</Button>
                                            </li>
                                        </shiro:user>
                                    </ul>
                                </div>
                            </div>
                        </div><!-- 数据区-主贴-底部信息结束 -->
                    </article>
                    <!-- 回复列表 -->
                    <!-- TODO:若存在最佳回复，则应将其置于回复列表顶部，目前未实现 -->
                    <div class="widget-answers" id="repliesDiv">
                        <!-- 回复排序方式 -->
                        <div class="btn-group pull-right" role="group">
                            <a class="btn btn-default btn-xs" href="${pageContext.request.contextPath}/${topic.section.name}/${topic.id}">默认排序</a>
                            <a class="btn btn-default btn-xs active">时间排序</a>
                        </div>
                        <!-- 回复排序方式结束 -->
                        <h2 class="title h4 mt30 mb20 post-title" id="answers-title">${replies.size()}个回答</h2>
                        <c:forEach var="reply" items="${replies}">
                            <!-- 单项回复 -->
                            <article class="clearfix widget-answers__item accepted" id="replyArticle-${reply.id}">
                                <!-- 单项回复-隐藏数据区 -->
                                <input type="hidden" id="condition-replyStatus-${reply.id}" value="${reply.isBlock}" />
                                <c:if test="${reply.isBest eq 'true' }">
                                    <input type="hidden" id="condition-isBestReply" />
                                </c:if>
                                <!-- 单项回复-隐藏数据区结束 -->
                                <!-- 回复-操作按钮 -->
                                <div class="post-col">
                                    <div class="widget-vote ">
                                        <button class="like" onclick="likeReply()"></button>
                                        <span class="count">${reply.rateCount}</span>
                                        <button class="hate" onclick="hateReply()"></button>
                                    </div>
                                    <!-- 回复-操作按钮结束 -->
                                    <!-- 该答案被采纳 -->
                                    <c:if test="${reply.isBest eq 'true' }">
                                        <div class="text-center accepted-check cancel-cursor-pointer mt15" style="white-space:nowrap">
                                            <i class="fa fa-check-circle accepted-check-icon" aria-hidden="true"></i><br>
                                            <span class="fontsize13">已采纳</span>
                                        </div>
                                    </c:if>
                                </div>
                                <!-- 回答-数据区 -->
                                <div class="post-offset">
                                    <div class="answer fmt" id="replyContent-${reply.id}">
                                            ${reply.content}
                                    </div>
                                    <div class="row answer__info--row">
                                        <!-- 回复-数据区-底部按钮-->
                                        <div class="post-opt col-md-8 col-sm-8 col-xs-10">
                                            <ul class="list-inline mb0" id="replyUl-${reply.id}">
                                                <li>
                                                    <a>
                                                        <fmt:formatDate value="${reply.addTime}"
                                                                        pattern="yyyy-MM-dd hh:mm:ss"/>
                                                    </a>
                                                </li>
                                                    <%-- 删除按钮 --%>
                                                <shiro:hasPermission name="reply:delete:${reply.id}">
                                                    <li><Button class="btn btn-danger" onclick="deleteReply(${reply.id})">删除</Button></li>
                                                </shiro:hasPermission>
                                                    <%-- 关闭按钮 --%>
                                                <shiro:hasPermission name="reply:block:${reply.id}">
                                                    <li>
                                                        <Button class="btn btn-primary" id="blockReplyButton-${reply.id}" onclick="blockReply(${reply.id})" disabled>关闭</Button></li>
                                                </shiro:hasPermission>
                                            </ul>
                                        </div>
                                        <!-- 回复-数据区-底部按钮结束-->
                                        <!-- 回复-数据区-右侧用户信息-->
                                        <div class="col-md-2 col-sm-2 col-xs-2 answer__info--author-avatar">
                                            <a class="mr10"
                                               href="${pageContext.request.contextPath}/user/${reply.uid}/home"><img
                                                    class="avatar-32" src="${reply.user.avatar}" alt="用户头像"></a>
                                        </div>
                                        <div class="col-md-2 col-sm-2 hidden-xs answer__info--author">
                                            <div class=" answer__info--author-warp">
                                                <a class="answer__info--author-name"
                                                   href="${pageContext.request.contextPath}/user/${reply.uid}/home">${reply.user.name}</a>
                                                <span class="answer__info--author-rank">${reply.user.prestige}声望</span>
                                            </div>
                                        </div>
                                        <!-- 回复-数据区-右侧用户信息结束-->
                                    </div>
                                </div>
                            </article>
                            <!-- 单项回复结束 -->
                        </c:forEach>
                    </div>
                    <!-- 回复列表结束 -->


                    <!-- 添加回复区 -->
                    <%-- 用户未登录时 --%>
                    <shiro:guest>
                        <a class="btn Button--blue" href="${pageContext.request.contextPath}/user/login" style="position:relative;top:10%">登录</a>
                        后回复
                    </shiro:guest>
                    <%-- 用户已登录时 --%>
                    <shiro:user>
                        <div class="panel panel-default" style="position:relative;top:10%">
                            <textarea id="replyContent" cols="120" rows="5" placeholder="评论点什么吧"></textarea>
                            <br/><br/>
                            <Button class="btn btn-primary" name="content" style="float:right" onclick="addReply()">评论
                            </Button>
                        </div>
                    </shiro:user>


                </div>
                <!-- 主数据区结束 -->
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
