<%@ page language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>用户主页</title>
    <jsp:include page="/common/base.jsp" />
    <script type="text/javascript">
        $(document).ready(function () {
            initNtfs(${ntfs},${userid});
            initPag(${currentPage},${totalPage},'ntfsPagination',getUserSecPath(${userid},'ntf'));
        });
    </script>
</head>
<body class="Entry-body">
<jsp:include page="/common/bannar.jsp" />
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
                    <div class="List" id="Profile-ntfs">
                    </div><!-- 用户动态详细数据区结束 -->
                    <%-- 分页按钮 --%>
                    <nav class="nav-pagination"><ul class="pagination" id="ntfsPagination"></ul></nav>
                    <%-- 分页按钮结束 --%>
                </div><!-- 用户动态数据区左侧结束 -->
            </div><!-- 用户动态数据区结束 -->
        </div>
    </div>
</main>
    <jsp:include page="/common/footer.jsp" />
</body>
</html>
