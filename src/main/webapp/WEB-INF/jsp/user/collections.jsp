<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户主页</title>
    <jsp:include page="/common/base.jsp" />
    <script type="text/javascript">
        $(document).ready(function () {
            initProHeaders('${user.id}','collection');
            initCollections(${collections});
            initPag(${currentPage},${totalPage},'collectionsPagination',getCollectionsPath('${user.id}'));
        });
    </script>
</head>
<body class="Entry-body">
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
                    <div class="List" id="Profile-collections"></div>
                    <!-- 用户动态详细数据区结束 -->
                    <%-- 分页按钮 --%>
                    <nav><ul class="pagination" id="collectionsPagination"></ul></nav>
                    <%-- 分页按钮结束 --%>
                </div><!-- 用户动态数据区左侧结束 -->
            </div><!-- 用户动态数据区结束 -->
        </div>
    </div>
</main>
<jsp:include page="/common/footer.jsp" />
</body>
</html>
