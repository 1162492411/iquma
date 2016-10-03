<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>后台管理页面</title>
</head>
<frameset rows="80,*" cols="*" frameborder=0 border="0"
          framespacing="1">
    <!-- 显示顶部网页 -->
    <frame
            src="${pageContext.request.contextPath}/admin/top.jsp"
            name="topFrame" scrolling="No" noresize="noresize" id="topFrame"
            title="topFrame"/>

    <frameset cols="200,*" frameborder=0 border="0" framespacing="1">
        <!-- 显示左侧网页 -->
        <frame
                src="${pageContext.request.contextPath}/admin/left.jsp"
                name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame"
                title="leftFrame"/>
        <!-- 显示主体网页 -->
        <frame
                src=""
                name="mainFrame" noresize="noresize" id="mainFrame" title="mainFrame"
        />
    </frameset>
</frameset>
<body>

</body>
</html>