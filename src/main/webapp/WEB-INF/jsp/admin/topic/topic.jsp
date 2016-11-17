<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>主贴</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/iquma.js"></script>
    <script type="text/javascript">

        function deleteTopic(id){
            $.ajax({
                type: 'delete',
                url:'${pageContext.request.contextPath}/admin/topic/'+id,
                dataType:'text',
                success:function(data){
                    if(data=="suc"){
                        alert("删除成功");
                        location.reload();
                    }
                    else if (data=="err"){
                        alert("未能成功删除");
                        location.reload();
                    }
                },
                error:function(data){
                }
            });
        }

        function blockReply(id){
            $.ajax({
                type: 'POST',
                url:'${pageContext.request.contextPath}/admin/topic/1/reply/'+id,
                dataType:'text',
                success:function(data){
                    if(data=="suc"){
                        alert("成功封闭");
                        location.reload();
                    }
                    else if (data=="err"){
                        alert("未能成功封闭");
                        location.reload();
                    }
                },
                error:function(data){
                }
            });
        }

        function deleteReply(id){
            $.ajax({
                type: 'delete',
                url:'${pageContext.request.contextPath}/admin/topic/1/reply/'+id,
                dataType:'text',
                success:function(data){
                    if(data=="suc"){
                        alert("删除成功");
                        location.reload();
                    }
                    else if (data=="err"){
                        alert("未能成功删除");
                        location.reload();
                    }
                },
                error:function(data){
                }
            });
        }

    </script>
</head>
<body>
<%--  主贴区--%>
<form action="${pageContext.request.contextPath}/admin/topic/${topic.id}" method="post" id="topicForm">
    <input type="hidden" name="_method" id="method" value="POST"/>
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
                    <label>版块id</label>
                    <input type="text" class="form-control" value="${topic.sid}"/>
                </div>
                <%--标签id --%>
                <div class="form-group" id="tidPanel">
                    <label>标签id</label>
                    <input type="text" class="form-control" value="${topic.tid}"/>
                </div>
                <%--作者id --%>
                <div class="form-group" id="tidPanel">
                    <label>作者id</label>
                    <input type="text" class="form-control" value="${topic.aid}"/>
                </div>
            <%-- 内容--%>
            <div class="form-group" id="contentPanel">
                <label>内容</label><br />
                <textarea name="content" cols="120" rows="5"  placeholder="内容">
                    ${topic.content}
                </textarea>
            </div>
                <%--浏览量 --%>
                <div class="form-group" id="viewCountPanel">
                    <label>浏览量</label>
                    <input type="text" class="form-control" name="viewCount" value="${topic.viewCount}" placeholder="浏览量" />
                </div>
                <%--评分 --%>
                <div class="form-group" id="rateCountPanel">
                    <label>评分</label>
                    <input type="text" class="form-control" name="rateCount" value="${topic.rateCount}" placeholder="评分"  />
                </div>
                <%--主贴状态 --%>
                <div class="form-group" id="isBlockPanel">
                    <label>主贴状态</label>
                    <input type="text" class="form-control" name="isBlock" value="${topic.isBlock}" placeholder="主贴状态"/>
                </div>
            <%-- 按钮 --%>
            <div class="form-group" id="buttonPanel">
                <a href="/admin/topic/${topic.id}/update">
                    <input type="button" class="btn btn-primary" value="更新"/></a>
                <input type="submit" id="blockButton" class="btn btn-primary" value="关闭"/>
                <input type="button" id="deleteButton" class="btn btn-danger" onclick="deleteTopic('${topic.id}')" value="删除"/>
                <input type="reset" class="btn btn-default" value="重置"/>
            </div>
        </div>
    </div>
    </div>
</form>
<%-- 评论区--%>
<form id="replyForm" method="post">
    <div class="panel panel-default" style="width:60%;height:30%;position:absolute;left:20%;top:130%">
        <div class="panel-heading">
            <h5>评论区</h5>
        </div>
        <div class="panel-body">
            <table>
                <c:forEach var="reply" items="${replies}">
                    <tr>
                        <td>${reply.id}</td>
                        <td>${reply.content}</td>
                        <td>${reply.addTime}</td>
                        <td><input type="button" class="btn btn-primary" value="关闭" onclick="blockReply('${reply.id}')" /></td>
                        <td><input type="button" class="btn btn-danger" value="删除" onclick="deleteReply('${reply.id}')" /></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</form>
</body>
</html>
