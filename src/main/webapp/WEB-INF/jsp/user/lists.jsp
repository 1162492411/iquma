<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户主页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/zhihu.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/iquma.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            initUserList($("#condition-topicType").val());
        });
    </script>
</head>
<body class="Entry-body">
<input type="hidden" id="condition-topicType" value="${topicType}" />
<div id="root">
    <div>
        <jsp:include page="${pageContext.request.contextPath}/common/bannar.jsp"/>
        <main role="main" class="App-main">
            <div>
                <jsp:include page="${pageContext.request.contextPath}/common/profileCard.jsp" />
                <!-- 用户动态数据区-->
                <div class="Profile-main">
                    <!-- 用户动态数据区左侧 -->
                    <div class="Profile-mainColumn" style="width: 1000px;">
                        <div class="Card ProfileMain" id="ProfileMain">
                            <div class="ProfileMain-header">
                                <ul role="tablist" class="Tabs ProfileMain-tabs">
                                    <li role="tab" class="Tabs-item">
                                        <a class="Tabs-link" id="Tabs-tutorials"
                                           href="${pageContext.request.contextPath}/user/${user.id}/tutorials">教程</a>
                                    </li>
                                    <li role="tab" class="Tabs-item">
                                        <a class="Tabs-link" id="Tabs-answers"
                                           href="${pageContext.request.contextPath}/user/${user.id}/answers">回答</a>
                                    </li>
                                    <li role="tab" class="Tabs-item">
                                        <a class="Tabs-link" id="Tabs-discusss"
                                           href="${pageContext.request.contextPath}/user/${user.id}/discuss">提问</a>
                                    </li>
                                    <li role="tab" class="Tabs-item">
                                        <a class="Tabs-link" id="Tabs-articles"
                                           href="${pageContext.request.contextPath}/user/${user.id}/articles">文章</a>
                                    </li>
                                    <li role="tab" class="Tabs-item">
                                        <a class="Tabs-link" id="Tabs-codes"
                                           href="${pageContext.request.contextPath}/user/${user.id}/codes">代码</a>
                                    </li>
                                    <li role="tab" class="Tabs-item">
                                        <a class="Tabs-link" id="Tabs-collections"
                                           href="${pageContext.request.contextPath}/user/${user.id}/collections">收藏</a>
                                    </li>
                                </ul>
                            </div>
                            <!-- 用户动态详细数据区 -->
                            <div class="List" id="Profile-activities">
                                <c:if test="${emptyResult eq true }">
                                    查询的数据不存在！
                                </c:if>
                                <%-- 查询结果非空时将其遍历 --%>
                                <c:if test="${emptyResult eq null}">
                                <c:forEach var="topic" items="${topics}">
                                    <!-- 用户动态详细数据单项 -->
                                    <div class="List-item">
                                        <!-- 操作主贴信息 -->
                                        <div class="ContentItem">
                                            <h2 class="ContentItem-title">
                                                <a target="_blank"
                                                   href="${pageContext.request.contextPath}/discuss/${topic.id}">${topic.title}</a>
                                            </h2>
                                            <!-- 阅读全文按钮 -->
                                            <div class="ContentItem-content is-collapsed">
                                                <span class="RichText CopyrightRichText-richText">${topic.content}</span>
                                                <a class="Button ContentItem-more Button--plain"
                                                   type="button"
                                                   href="${pageContext.request.contextPath}/discuss/${topic.id}">
                                                    阅读全文
                                                </a>
                                            </div><!-- 阅读全文按钮结束 -->
                                        </div> <!-- 操作主贴信息结束 -->
                                    </div><!-- 用户动态详细数据单项结束 -->
                                </c:forEach>
                                    </c:if>
                            </div><!-- 用户动态详细数据区结束 -->
                        </div><!-- 用户动态数据区左侧结束 -->
                    </div><!-- 用户动态数据区结束 -->
                </div>
            </div>
        </main>
    </div>
</div>
</body>
</html>
