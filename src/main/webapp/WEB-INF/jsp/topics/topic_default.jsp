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
            initTopicStatus('${topic.section}');
            initPag(${currentPage},${totalPage});
            initRateInfo('${topic.id}',0);
        });
    </script>
</head>
<body>
<jsp:include page="${pageContext.request.contextPath}/common/bannar.jsp"/>
<!-- 隐藏数据区 -->
<div>
<input type="hidden" id="condition_id" value="${topic.id}"/>
<input type="hidden" id="topicTitle" value="${topic.title}"/>
<input type="hidden" id="condition_isBlock" value="${topic.isBlock}" />
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
                        <span class="post-topheader__title--icon-symbol" id="topicText"></span>
                        <div class="post-topheader__info">
                            <h1 class="h3 post-topheader__info--title" id="questionTitle">
                                <a href="${pageContext.request.contextPath}/${topic.section}/${topic.id}">${topic.title}</a>
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
                                <button class="like" id="like-0" onclick="likeTopic('${topic.section}','${topic.id}')"></button>
                                <span class="count">${topic.rateCount}</span>
                                <button class="hate" id="hate-0" onclick="hateTopic('${topic.section}','${topic.id}')"></button>
                            </div>
                            <!-- end .widget-vote -->
                        </div>
                        <!-- 数据区-主贴-底部信息 -->
                        <div class="post-offset">
                            <div class="question fmt">
                                ${topic.content}
                                <br />
                                <%-- 如果该主贴存在附件则显示 --%>
                                    <input type="hidden" id="att" value="${topic.attid}" />
                                <c:if test="${topic.attid ne null}">
                                    <input type="hidden" id="attSize" value="${topic.attachment.size}" />
                                    <span class="glyphicon glyphicon-file"><a href="${pageContext.request.contextPath}/download/${topic.attid}" id="attHref">${topic.attachment.name}</a></span>
                                </c:if>
                            </div>
                            <div class="row">
                                <div class="post-opt col-md-8">
                                    <ul class="list-inline mb0">
                                        <li><strong class="no-stress">${topic.viewCount}</strong> 浏览</li>
                                        <li><a>
                                            <fmt:formatDate value="${topic.addTime}"
                                            pattern="yyyy-MM-dd"/>
                                        </a></li>
                                        <shiro:hasPermission name="${topic.section}:update:${topic.id}">
                                            <a href="${pageContext.request.contextPath}/${topic.section}/${topic.id}/update">
                                                <Button class="btn btn-primary">编辑</Button>
                                            </a>
                                            </li>
                                        </shiro:hasPermission>
<shiro:hasPermission name="${topic.section}:block:${topic.id}">
                                        <li>
                                            <Button class="btn btn-primary" id="blockButton" onclick="blockTopic('${topic.section}')" disabled>关闭</Button>
                                        </li>
    </shiro:hasPermission>
<shiro:hasPermission name="${topic.section}:delete:${topic.id}">
                                        <li>
                                            <Button class="btn btn-danger"
                                                   onclick="deleteTopic('${topic.section}')">删除</Button>
                                        </li>
    </shiro:hasPermission>
                                        <shiro:user>
                                            <li>
                                                <Button class="btn btn-primary" id="favoriteButton" onclick="favoriteTopic('${topic.section}')" disabled>收藏</Button>
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
                            <a class="btn btn-default btn-xs active">默认排序</a>
                            <a href="${pageContext.request.contextPath}/${topic.section}/${topic.id}/time"
                               class="btn btn-default btn-xs">时间排序</a>
                        </div>
                        <!-- 回复排序方式结束 -->
                        <h2 class="title h4 mt30 mb20 post-title" id="answers-title">${total}个回答</h2>
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
                                        <button class="like" id="like-${reply.id}" onclick="likeReply('${reply.id}','${reply.uid}')"></button>
                                        <span class="count">${reply.rateCount}</span>
                                        <button class="hate" id="hate-${reply.id}" onclick="hateReply('${reply.id}','${reply.uid}')"></button>
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
                                    <input type="hidden" id="replyUid-${reply.id}" value="${reply.uid}" />
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
                                                    <li><Button class="btn btn-danger" onclick="deleteReply('${reply.id}','${reply.uid}')">删除</Button></li>
                                                </shiro:hasPermission>
                                                    <%-- 关闭按钮 --%>
                                                <shiro:hasPermission name="reply:block:${reply.id}">
                                                    <li>
                                                        <Button class="btn btn-primary" id="blockReplyButton-${reply.id}" onclick="blockReply('${reply.id}','${reply.uid}')" disabled>关闭</Button></li>
                                                </shiro:hasPermission>
                                                <%-- 采纳按钮 --%>
                                                <c:if test="${topic.hasBest eq false && topic.aid eq userid && topic.isBlock eq false }">
                                                 <li>
                                                     <Button class = "btn btn-primary" onclick="adoptReply('${reply.id}','${reply.uid}')" >采纳</Button>
                                                 </li>
                                                </c:if>
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
                        <%-- 分页按钮 --%>
                        <nav>
                            <ul class="pagination col-xs-12 col-md-9" id="Pagination">
                                <li id="pagTop"><a href="./1">首页</a></li>
                                <li id="pagCurrent" class="active"><a>${currentPage}</a></li>
                                <li id="pagBottom"><a href="./${totalPage}">尾页</a></li>
                            </ul>
                        </nav>
                    </div>
                    <!-- 回复列表结束 -->


                    <!-- 添加回复区 -->
                    <%-- 主贴未关闭时显示评论框 --%>
                    <c:if test="${topic.isBlock eq 'false'}">
                    <%-- 若用户未登录 --%>
                    <shiro:guest>
                        <a class="btn Button--blue" href="${pageContext.request.contextPath}/user/login" style="position:relative;top:10%">登录</a>
                            后回复
                    </shiro:guest>
                    <%-- 若用户已登录 --%>
                    <shiro:user>

                        <div class="panel panel-default" style="position:relative;top:10%">
                            <textarea id="replyContent" cols="120" rows="5" placeholder="评论点什么吧"></textarea>
                            <br/><br/>
                            <Button class="btn btn-primary" name="content" style="float:right" onclick="addReply()">评论
                            </Button>
                        </div>
                    </shiro:user>
                        </c:if>
                        <%-- 主贴关闭时显示主贴已关闭提示 --%>
                        <c:if test="${topic.isBlock eq 'true'}">
                            <div class="alert alert-warning" role="alert">该主贴已被关闭，禁止添加新回复</div>

                        </c:if>


                </div>
                <!-- 主数据区结束 -->
            </div>
        </div>
    </div>
</main>

<jsp:include page="${pageContext.request.contextPath}/common/footer.jsp"/>


</body>
</html>
