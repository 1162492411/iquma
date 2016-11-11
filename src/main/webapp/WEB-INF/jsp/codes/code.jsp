<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>代码</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        function deleteCode(id){
            $.ajax({
                type: 'delete',
                url:'${pageContext.request.contextPath}/code/'+id,
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
        function checkStatus(userid){
            if(userid == null || userid== "")
                $("#replyButton").attr("value","请登录");
            else
                $("#replyForm").submit();
        }

    </script>
</head>
<body>
<%--  主贴区--%>
<div class="panel panel-default" style="width:60%;height:40%;position:absolute;left:20%;top:20%">
    <div class="panel-heading">
        <h2 class="panel-title">${code.title}</h2><br />
        作者:${code.aid} &emsp;&emsp;&emsp;&emsp;发表时间:${code.addTime}
    </div>
    <div class="panel-body">
        ${code.content}
        <br />
        <a href="/code/${code.id}/update">
            <Button class="btn btn-primary">修改</Button></a>
        <input type="button" class="btn btn-danger"  value="删除"
               onclick="deleteCode(${code.id})"/>
    </div>
</div>
<%-- 评论区--%>
<div class="panel panel-default" style="width:60%;height:30%;position:absolute;left:20%;top:65%">
    <div class="panel-heading">
        <h5>评论区</h5>
    </div>
    <div class="panel-body">
        <table>
            <c:forEach var="reply" items="${replies}">
                <tr id="${reply.id}">
                    <td>${reply.id}</td>
                    <td>${reply.content}</td>
                    <td>${reply.addTime}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<%-- 发表评论--%>
<div class="panel panel-default" style="position:absolute;left:20%;top:100%">
    <form action="/code/${code.id}/reply" method="post">
        <textarea name="content" cols="120" rows="5" placeholder="评论点什么吧"></textarea>
        <input type="hidden" name="_method" value="POST" />
        <input type="hidden" name="uid" value="${userid}"/>
        <input type="hidden" name="tid" value="${code.id}"/><br /><br />
        <input type="button" id="replyButton" class="btn btn-primary" value="评论" onclick="checkStatus('${userid}')" style="float:right"/>
    </form>
</div>
</body>
</html>
