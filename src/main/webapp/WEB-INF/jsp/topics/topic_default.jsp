<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>主贴</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/editor/css/wangEditor.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/iquma.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/obsidian.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/editor/js/wangEditor.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/xss.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/iquma.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/highlight.pack.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            hljs.initHighlightingOnLoad();
            initTopicStatus('${topic.sec}');
            initPag(${currentPage},${totalPage},"repliesPagination",getTopicPath());
            initRateInfo('${topic.id}');
        });
    </script>
</head>
<body>
<jsp:include page="/common/bannar.jsp"/>
<!-- 隐藏数据区 -->
<div>
    <input type="hidden" id="condition_type" value="${type}"/>
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
                            <div class="widget-vote  ">
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
                            <div class="question fmt">
                                ${topic.content}
                                <br/>
                                <%-- 如果该主贴存在附件则显示 --%>
                                <c:if test="${topic.attid ne null}">
                                    <input type="hidden" id="attSize" value="${topic.attachment.size}"/>
                                    <span class="glyphicon glyphicon-file"><a
                                            href="${pageContext.request.contextPath}/file/download/${topic.attid}"
                                            id="attHref">${topic.attachment.name}</a></span>
                                </c:if>
                            </div>
                            <!-- 主贴底部操作信息 -->
                            <div class="row">
                                <div class="post-opt col-md-8">
                                    <ul class="list-inline mb0">
                                        <li><strong class="no-stress">${topic.viewCount}</strong> 浏览</li>
                                        <li><a id="topicAddTime">
                                            <script type="text/javascript">$("#topicAddTime").append(formateTime('${topic.addTime}'));</script>
                                        </a>
                                            <shiro:hasPermission name="${topic.sec}:update:${topic.id}">
                                                </li>
                                                <a href="${pageContext.request.contextPath}/${topic.sec}/${topic.id}/update">
                                                    <Button class="btn btn-primary">编辑</Button>
                                                </a>
                                                </li>
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="${topic.sec}:block:${topic.id}">
                                                <li>
                                                    <Button class="btn btn-primary" id="blockButton"
                                                            onclick="blockTopic('${topic.sec}')" disabled>关闭
                                                    </Button>
                                                </li>
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="${topic.sec}:delete:${topic.id}">
                                                <li>
                                                    <Button class="btn btn-danger"
                                                            onclick="deleteTopic('${topic.sec}')">删除
                                                    </Button>
                                                </li>
                                            </shiro:hasPermission>
                                            <shiro:user>
                                                <li>
                                                    <Button class="btn btn-primary" id="favoriteButton"
                                                            onclick="favoriteTopic('${topic.sec}')" disabled>收藏
                                                    </Button>
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
                        <div class="btn-group pull-right" role="group" id="testDiv">
                            <a class="btn btn-default btn-xs active">默认排序</a>
                            <a href="${pageContext.request.contextPath}/${topic.sec}/${topic.id}/time"
                               class="btn btn-default btn-xs">时间排序</a>
                        </div>
                        <!-- 回复排序方式结束 -->
                        <h2 class="title h4 mt30 mb20 post-title" id="answers-title">${total}个回答</h2>
                        <!-- 回复列表隐藏数据区 -->
                        <input type="hidden" id="bestReplyId" value="${topic.hasBest}" />
                        <shiro:hasPermission name="reply:like:${topic.id}">
                            <input type="hidden" id="hasReplyLikePermission" value="true" />
                        </shiro:hasPermission>
                        <shiro:hasPermission name="reply:hate:${topic.id}">
                            <input type="hidden" id="hasReplyHatePermission" value="true" />
                        </shiro:hasPermission>
                        <shiro:hasPermission name="reply:delete:${topic.id}">
                            <input type="hidden" id="hasReplyDeletePermission" value="true" />
                        </shiro:hasPermission>
                        <shiro:hasPermission name="reply:block:${topic.id}">
                            <input type="hidden" id="hasReplyBlockPermission" value="true" />
                        </shiro:hasPermission>
                        <c:if test="${topic.hasBest eq 0 && topic.aid eq userid && topic.isBlock eq false }">
                            <input type="hidden" id="hasReplyAdoptPermission" value="true" />
                        </c:if>
                        <div id="replies"></div>
                        <!-- 回复列表隐藏数据区结束 -->
                        <!-- 显示最佳回复 -->
                        <script type="text/javascript">
                            $(document).ready(function () {
                                initReplies(${replies},${topic.hasBest});
                            });
                        </script>
                        <nav><ul class="pagination" id="repliesPagination"></ul></nav>
                    </div>
                    <!-- 回复列表结束 -->

                    <!-- 添加回复区 -->
                    <%-- 主贴未关闭时显示评论框 --%>
                    <c:if test="${topic.isBlock eq 'false'}">
                        <%-- 若用户未登录 --%>
                        <shiro:guest>
                            <a class="btn Button--blue" href="${pageContext.request.contextPath}/user/login"
                               style="position:relative;top:10%">登录</a>
                            后回复
                        </shiro:guest>
                        <%-- 若用户已登录 --%>
                        <shiro:user>
                            <div class="panel panel-default">
                                 <div name="content" id="replyContent" ></div>
                                <Button class="btn btn-primary" name="content" style="float:right" onclick="addReply()">评论</Button>
                            </div>
                            <script type="text/javascript">
                                $(document).ready(function () {
                                    var editor = new wangEditor('replyContent');
                                    editor.create();
                                });
                            </script>
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

<jsp:include page="/common/footer.jsp"/>


</body>
</html>
