<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
</head>
<body>
<!-- 导航栏  -->
<div class="global-nav sf-header">
        <header role="banner" class="Sticky AppHeader">
            <div class="AppHeader-inner">
                <a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/static/image/logo.png" style="width:44px;height:44px"></a>
                <!-- 导航分类 -->
                <nav role="navigation" class="AppHeader-nav">
                    <a class="AppHeader-navItem" href="${pageContext.request.contextPath}/">首页</a>
                    <a class="AppHeader-navItem" href="${pageContext.request.contextPath}/tutorials/jse/new/1">教程</a>
                    <a class="AppHeader-navItem" href="${pageContext.request.contextPath}/discusses/jse/1">话题</a>
                    <a class="AppHeader-navItem" href="${pageContext.request.contextPath}/articles/jse/1">文章</a>
                    <a class="AppHeader-navItem" href="${pageContext.request.contextPath}/codes/jse/1">代码</a>
                </nav>
                <!-- 导航分类结束 -->
                <!-- 导航-搜索 -->
                <div class="SearchBar" role="search">
                    <div class="SearchBar-toolWrapper">
                        <form class="SearchBar-tool">
                            <div>
                                <div class="Popover">
                                    <div class="SearchBar-input Input-wrapper Input-wrapper--grey">
                                        <input maxlength="100" class="Input" placeholder="搜索" type="text"></div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <!-- 导航-搜索结束 -->
                <!-- 用户未登录时显示登录 -->
                <shiro:guest>
                    <a class="btn Button--blue pull-right" href="${pageContext.request.contextPath}/user/login"> 登 录 </a>
                </shiro:guest>
                <!-- 用户未登录时显示导航右侧 -->
                <!-- 导航-右侧-->
                <shiro:user>
                    <ul class="opts list-inline hidden-xs">
                        <!-- 导航-右侧-发布 -->
                        <li class="opts__item dropdown hoverDropdown write-btns">
                            <a class="dropdownBtn" data-toggle="dropdown" href="${pageContext.request.contextPath}/ask"><img src="${pageContext.request.contextPath}/static/image/add.png"><span class="caret"></span></a>
                            <ul class="dropdown-menu dropdown-menu-right">
                                <li><a href="${pageContext.request.contextPath}/ask">提问题</a></li>
                                <li><a href="${pageContext.request.contextPath}/write">写文章</a></li>
                                <li><a href="${pageContext.request.contextPath}/upload">传代码</a></li>
                            </ul>
                        </li>
                        <!-- 导航-右侧-发布结束 -->
                        <!-- 导航-右侧-用户中心 -->
                        <li class="opts__item user dropdown hoverDropdown">
                            <a class="dropdownBtn user-avatar" data-toggle="dropdown" style="background-image: url(${useravatar})" href="${pageContext.request.contextPath}/user/${userid}/home"></a>
                            <ul class="dropdown-menu dropdown-menu-right">
                                <li><a href="${pageContext.request.contextPath}/user/${userid}/home">我的主页</a></li>
                                <li><a href="${pageContext.request.contextPath}/user/${userid}/ntfs">我的消息</a></li>
                                <li><a href="${pageContext.request.contextPath}/user/${userid}/profile">我的档案</a></li>
                                <li><a href="${pageContext.request.contextPath}/user/${userid}/settings">账号设置</a></li>
                                <li class="divider"></li>
                                <li><a href="${pageContext.request.contextPath}/user/${userid}/logout">退出</a></li>
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
