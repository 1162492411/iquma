<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>后台默认页面</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body>
<ul class="nav nav-pills nav-stacked">
    <li class="active">
        <a href="#">管理功能菜单</a>
    </li>
    <li><a href="users/users.jsp" target="mainFrame">查看用户</a></li>
    <li><a href="users/addUsers.jsp" target="mainFrame">添加用户</a></li>
    <li><a href="courses/showCourses.jsp" target="mainFrame">查看教程</a></li>
    <li><a href="courses/addCourses.jsp" target="mainFrame">添加教程</a></li>
    <li><a href="questions/showQuestions.jsp" target="mainFrame">查看问答</a></li>
    <li><a href="questions/addQuestions.jsp" target="mainFrame">添加问答</a></li>
    <li><a href="tutorials/showTutorials.jsp" target="mainFrame">查看经验</a></li>
    <li><a href="tutorials/addTutorials.jsp" target="mainFrame">添加经验</a></li>
    <li><a href="codes/showCodes.jsp" target="mainFrame">查看代码</a></li>
    <li><a href="codes/addCodes.jsp" target="mainFrame">添加代码</a></li>
    <li><a href="news/showNews" target="mainFrame">查看资讯</a></li>
    <li><a href="news/addNews" target="mainFrame">添加资讯</a></li>
</ul>
</body>
</html>