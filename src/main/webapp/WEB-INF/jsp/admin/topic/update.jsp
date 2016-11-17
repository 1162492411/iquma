<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>主贴</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        //初始化版块下拉框
        function initSid() {
            $.post('/api/getAllSections',
                    function (data) {
                        var count = data.length;
                        for(var i = 0;i < count;i++)
                            document.getElementById('sidSelection').options[i] = new Option(data[i].name,data[i].id);
                    },
                    "json"
            );
        }

        //初始化类别下拉框
        function initTypeId() {
            $.post('/api/getAllTypes',
                    function (data) {
                        var count = data.length;
                        for(var i = 0;i < count;i++)
                            document.getElementById('typeIdSelection').options[i] = new Option(data[i].name,data[i].id);
                    },
                    "json"
            );
        }

        //初始化标签下拉框
        function updateTidSelection(id){
            $.ajax({
                type: 'get',
                url:'/api/getTagsByPid/' + id,
                dataType:'json',
                success:function(data){
                    var count = data.length;
                    for(var i = 0;i < count;i++)
                        document.getElementById('tidSelection').options[i] = new Option(data[i].name,data[i].id);
                },
                error:function(data){
                }
            });
        }

        $(function () {
            initSid();
            initTypeId();
            $("#typeIdSelection").change(function () {
                updateTidSelection($('#typeIdSelection option:selected').val());
            });
        });
    </script>
</head>
<body>
<%--  主贴区--%>
<form action="${pageContext.request.contextPath}/admin/topic/${topic.id}/update" method="post" id="topicForm">
    <input type="hidden" name="_method" id="method" value="PUT"/>
    <div class="panel panel-default">
        <div class="panel-heading">主贴</div>
        <div class="panel-body" id="TopicBody">
            <%--主贴标题 --%>
            <div class="form-group" id="titlePanel">
                <label>主贴标题</label>
                <input type="text" class="form-control" name="title" value="${topic.title}" placeholder="主贴标题"/>
            </div>
            <%--版块id --%>
            <div class="form-group" id="sidPanel">
                <label>版块</label>
                <select name="sid" id="sidSelection">
                </select>
            </div>
            <%--类别id --%>
            <div class="form-group" id="typeIdPanel">
                <label>类别</label>
                <select id="typeIdSelection">
                </select>
            </div>
            <%--标签id --%>
            <div class="form-group" id="tidPanel">
                <label>标签id</label>
                <select name="tid" id="tidSelection">
                </select>
            </div>
            <%-- 内容 --%>
            <div class="form-group" id="contentPanel">
                <label>内容</label><br/>
                <textarea name="content" cols="120" rows="5" placeholder="内容">
                    ${topic.content}
                </textarea>
            </div>
            <%--浏览量 --%>
            <div class="form-group" id="viewCountPanel">
                <label>浏览量</label>
                <input type="text" class="form-control" name="viewCount" value="${topic.viewCount}" placeholder="浏览量" readonly />
            </div>
            <%--评分 --%>
            <div class="form-group" id="rateCountPanel">
                <label>评分</label>
                <input type="text" class="form-control" name="rateCount" value="${topic.rateCount}" placeholder="评分" readonly />
            </div>
            <%--主贴状态 --%>
            <div class="form-group" id="isBlockPanel">
                <label>主贴状态</label>
                <input type="text" class="form-control" name="isBlock" value="${topic.isBlock}" placeholder="主贴状态" readonly />
            </div>
            <%-- 按钮 --%>
            <div class="form-group" id="buttonPanel">
                <input type="submit" class="btn btn-primary" value="更新"/>
                <input type="reset" class="btn btn-default" value="重置"/>
            </div>
        </div>
    </div>
    </div>
</form>
</body>
</html>
