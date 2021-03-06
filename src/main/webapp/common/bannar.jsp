<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="rootPath" value="${rootPath}"/>
<html>
<head>
</head>
<body>
<!-- 导航栏-->
<div class="global-nav">
    <header role="banner" class="Sticky AppHeader">
        <div class="AppHeader-inner">
            <img src="${rootPath}/static/image/logo.png" width="75px" height="100%">
            <!-- 导航分类 -->
            <nav role="navigation" class="AppHeader-nav">
                <a class="AppHeader-navItem" href="${rootPath}/tutorials">教程</a>
                <a class="AppHeader-navItem" href="${rootPath}/questions">话题</a>
                <a class="AppHeader-navItem" href="${rootPath}/articles">文章</a>
                <a class="AppHeader-navItem" href="${rootPath}/codes">代码</a>
                <shiro:user>
                    <a class="AppHeader-navItem" href="${rootPath}/user/${userid}/ntfs">通知
                        <c:if test="${ntfCount != null && ntfCount != 0 }">
                            <div class="ntf_img"><span class="ntf_text">${ntfCount}</span></div>
                        </c:if>
                    </a>
                </shiro:user>

                <%-- 搜索框 --%>
                <form action="https://segmentfault.com/search"
                      class="header-search  hidden-sm hidden-xs pull-right">
                    <input id="searchBox" name="q" type="text" placeholder="搜索问题或关键字" class="form-control" value="">
                </form>
            </nav>
            <!-- 导航分类结束 -->

            <!-- 用户未登录时显示登录 -->
            <shiro:guest>
                <a class="btn Button--blue pull-right" href="${rootPath}/user/login"> 登 录 </a>
            </shiro:guest>
            <!-- 用户未登录时显示导航右侧 -->
            <!-- 用户登录时显示导航栏右侧操作按钮组 -->
            <!-- 导航-右侧-->
            <shiro:user>
                <ul class="opts list-inline hidden-xs">
                    <!-- 导航-右侧-发布 -->
                    <li class="opts__item dropdown hoverDropdown write-btns">
                        <a class="dropdownBtn" data-toggle="dropdown" href="${rootPath}/user/ask"><img
                                src="${rootPath}/static/image/add.png"><span class="caret"></span></a>
                        <ul class="dropdown-menu dropdown-menu-right">
                            <shiro:hasPermission name="question:create">
                                <li><a href="${rootPath}/user/ask">提问题</a></li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="article:create">
                                <li><a href="${rootPath}/user/write">写文章</a></li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="code:create">
                                <li><a href="${rootPath}/user/upload">传代码</a></li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="tutorial:create">
                                <li><a href="${rootPath}/user/teach">发教程</a></li>
                            </shiro:hasPermission>
                        </ul>
                    </li>
                    <!-- 导航-右侧-发布结束 -->
                    <!-- 导航-右侧-用户中心 -->
                    <li class="opts__item user dropdown hoverDropdown">
                        <a class="dropdownBtn user-avatar" data-toggle="dropdown"
                           style="background-image: url(${useravatar})" href="${rootPath}/user/${userid}/home"></a>
                        <ul class="dropdown-menu dropdown-menu-right">
                            <li><a href="${rootPath}/user/${userid}/home">我的主页</a></li>
                            <li><a href="${rootPath}/user/${userid}/profile">我的档案</a></li>
                            <li><a href="${rootPath}/user/${userid}/account">修改密码</a></li>
                            <!-- 若账户具有添加学生账户的权限，则显示添加账户按钮 -->
                            <shiro:hasPermission name="suser:create">
                                <li><a href="${rootPath}/user/addStudent">添加账户</a></li>
                            </shiro:hasPermission>
                            <li class="divider"></li>
                            <li><a href="${rootPath}/user/${userid}/logout">退出</a></li>
                        </ul>
                    </li>
                    <!-- 导航-右侧-用户中心结束 -->
                </ul>
                <!-- 导航-右侧结束 -->
            </shiro:user>
        </div>
    </header>


</div>
<!-- 导航栏结束 -->
</body>
</html>
