<%--
  Created by IntelliJ IDEA.
  User: Mo
  Date: 2016/10/1
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>搜索话题</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>

</head>
<body>
<!-- 搜索话题 -->
<form action="${pageContext.request.contextPath}/search/showTopic">
    <div class="panel panel-default">
        <div class="panel-heading">查询话题</div>
        <div class="panel-body" id="searchTopicBody">
            <%--话题标题 --%>
            <div class="form-group" id="titlePanel">
                <label>话题标题</label>
                <input type="text" class="form-control" name="title" placeholder="话题标题"/>
            </div>

             <%--<!-- 需要对个别输入进行格式转换 -->--%>
            <%--版块id --%>
            <div class="form-group" id="sidPanel">
                <label>版块id</label>
                <input type="text" class="form-control" name="sid" placeholder="版块id"/>
            </div>
            <%--标签id --%>
            <div class="form-group" id="tidPanel">
                <label>标签id</label>
                <input type="text" class="form-control" name="tid" placeholder="标签id"/>
            </div>
            <%--作者id --%>
            <div class="form-group" id="aidPanel">
                <label>作者id</label>
                <input type="text" class="form-control" name="aid" placeholder="作者id"/>
            </div>


            <%-- 发表时间--%>
            <div class="form-group" id="addTimePanel">
                <label>发表时间</label>
                <input type="time" class="form-control" name="addTime" placeholder="发表时间"/>
            </div>

                <%-- 最后回复时间--%>
                <div class="form-group" id="reTimePanel">
                    <label>最后回复时间</label>
                    <input type="time" class="form-control" name="reTime" placeholder="最后回复时间"/>
                </div>
            <%-- 内容--%>
            <div class="form-group" id="contentPanel">
                <label>内容</label>
                <input type="text" class="form-control" name="content" placeholder="内容"/>
            </div>
            <!-- 按钮 -->
            <div class="form-group" id="buttonPanel">
                <input type="submit" class="btn btn-primary" onclick="" value="查询"/>
                <input type="reset" class="btn btn-default" value="重置"/>
            </div>
        </div>
    </div>
    </div>
</form>
</body>
</html>
