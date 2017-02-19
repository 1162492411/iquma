<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>用户主页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/qa.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/responsive.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/zhihu.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/iquma.js"></script>
</head>
<body class="Entry-body">

<jsp:include page="${pageContext.request.contextPath}/common/bannar.jsp"/>
<main role="main" class="App-main">
    <div>
        <!-- 用户动态数据区-->
        <div class="Profile-main">
            <!-- 用户动态数据区左侧 -->
            <div class="Profile-mainColumn" style="width: 1000px;">
                <div class="Card ProfileMain" id="ProfileMain">
                    <div class="ProfileMain-header">
                        <ul role="tablist" class="Tabs ProfileMain-tabs">
                            <li role="tab" class="Tabs-item">
                                <span class="Tabs-link">消息列表</span>
                            </li>
                        </ul>
                    </div>
                    <!-- 用户动态详细数据区 -->
                    <div class="List" id="Profile-activities">
                        <c:forEach var="ntf" items="${ntfs}">
                            <!-- 用户动态详细数据单项 -->
                            <div class="List-item">
                                <div class="ContentItem">
                                    <div class="ContentItem-content is-collapsed">
                                        <span class="RichText CopyrightRichText-richText">${ntf.content}</span><br />
                                        <fmt:formatDate value="${ntf.ntftime}" pattern="yyyy-MM-dd hh:mm:ss"/>
                                        <c:if test="${ntf.isnew eq true}">
                                        <a class="Button ContentItem-more Button--plain"
                                           type="button" onclick="signNtf(${userid},${ntf.id})"
                                           >
                                            标记为已读
                                        </a>
                                        </c:if>
                                        <c:if test="${ntf.isnew eq false}">
                                        <a class="Button ContentItem-more Button--plain"
                                           type="button"
                                        >已读</a>
                                        </c:if>
                                    </div><!-- 阅读按钮结束 -->
                                </div> <!-- 回复信息结束 -->
                            </div><!-- 用户动态详细数据单项结束 -->
                        </c:forEach>
                    </div><!-- 用户动态详细数据区结束 -->
                </div><!-- 用户动态数据区左侧结束 -->
            </div><!-- 用户动态数据区结束 -->
        </div>
    </div>
</main>

<jsp:include page="${pageContext.request.contextPath}/common/footer.jsp"/>


</body>
</html>
