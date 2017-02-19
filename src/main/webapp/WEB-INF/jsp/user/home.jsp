<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>用户主页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/qa.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/responsive.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/zhihu.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
</head>
<body class="Entry-body">
<div id="root">
    <!-- 导航栏  -->
    <div class="global-nav sf-header">
        <nav class="container nav">
            <header role="banner" class="Sticky AppHeader is-fixed" style="width: 1342px; top: 0px; left: 0px;">
                <div class="AppHeader-inner">
                    <a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/static/image/logo.png" style="width:44px;height:44px"></a>
                    <!-- 导航分类 -->
                    <nav role="navigation" class="AppHeader-nav">
                        <a class="AppHeader-navItem" href="${pageContext.request.contextPath}/">首页</a>
                        <a class="AppHeader-navItem" href="${pageContext.request.contextPath}/tutprials">教程</a>
                        <a class="AppHeader-navItem" href="${pageContext.request.contextPath}/discusses">话题</a>
                        <a class="AppHeader-navItem" href="${pageContext.request.contextPath}/articles">文章</a>
                        <a class="AppHeader-navItem" href="${pageContext.request.contextPath}/codes">代码</a>
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
        </nav>
    </div>
    <!-- 导航栏结束 -->
    <div>
        <main role="main" class="App-main">
            <div>
                <!-- 顶部个人资料卡 -->
                <div id="ProfileHeader" class="ProfileHeader">
                    <div class="Card">
                        <!-- 顶部个人资料卡卡片上部 -->
                        <div class="ProfileHeader-userCover">
                            <div class="UserCoverEditor">
                                <div class="UserCoverGuide">
                                    <div class="UserCoverGuide-inner"></div>
                                </div>
                                <div class="UserCover UserCover--colorBlock"></div>
                            </div>
                        </div><!-- 顶部个人资料卡卡片上部结束 -->
                        <!-- 顶部个人资料卡卡片下部 -->
                        <div class="ProfileHeader-wrapper">
                            <div class="ProfileHeader-main">
                                <div class="UserAvatarEditor ProfileHeader-avatar" style="top: -25px;">
                                    <div class="UserAvatar">
                                        <img class="Avatar Avatar--large UserAvatar-inner"
                                             src="${user.avatar}"
                                             style="width: 160px; height: 160px;">
                                    </div>
                                </div>
                                <div class="ProfileHeader-content">
                                    <div class="ProfileHeader-contentHead">
                                        <h1 class="ProfileHeader-title">
                                            <span class="ProfileHeader-name">${user.name}<img
                                                    src="${pageContext.request.contextPath}/static/image/level-${user.rid}.png"
                                                    style="width: 22px; height: 22px;margin-left:8px"></span><br/>
                                            <span class="RichText ProfileHeader-headline">${user.description}</span>
                                        </h1>
                                    </div>
                                    <div class="ProfileHeader-contentBody">
                                            <span>
                                                <div class="ProfileHeader-info">
                                                  <!-- 获赞总数 -->
                                                  <div class="ProfileHeader-infoItem">
                                                    <div class="ProfileHeader-iconWrapper">
                                                      <img src="${pageContext.request.contextPath}/static/image/appCount.png"
                                                           style="width: 22px; height: 22px;">
                                                    </div>
                                                    获赞: ${user.appCount}
                                                  </div>
                                                    <!-- 威望 -->
                                                  <div class="ProfileHeader-infoItem">
                                                    <div class="ProfileHeader-iconWrapper">
                                                      <img src="${pageContext.request.contextPath}/static/image/prestige.png"
                                                           style="width: 22px; height: 22px;">
                                                    </div>
                                                    威望: ${user.prestige}
                                                  </div>
                                                </div>
                                              </span>
                                    </div>
                                    <div class="ProfileHeader-contentFooter">
                                        <div class="ProfileButtonGroup ProfileHeader-buttons"><a
                                                class="Button Button--blue"
                                                href="${pageContext.request.contextPath}/user/${user.id}/profile"
                                                role="button">编辑个人资料</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div><!-- 顶部个人资料卡下部结束 -->
                    </div>
                </div><!-- 顶部个人资料卡结束 -->
                <!-- 用户动态数据区-->
                <div class="Profile-main">
                    <!-- 用户动态数据区左侧 -->
                    <div class="Profile-mainColumn" style="width: 1000px;">
                        <div class="Card ProfileMain" id="ProfileMain">
                            <div class="ProfileMain-header">
                                <ul role="tablist" class="Tabs ProfileMain-tabs">
                                    <li role="tab" class="Tabs-item">
                                        <a class="Tabs-link"
                                           href="${pageContext.request.contextPath}/user/${user.id}/answers">回答</a>
                                    </li>
                                    <li role="tab" class="Tabs-item">
                                        <a class="Tabs-link"
                                           href="${pageContext.request.contextPath}/user/${user.id}/asks">提问</a>
                                    </li>
                                    <li role="tab" class="Tabs-item">
                                        <a class="Tabs-link"
                                           href="${pageContext.request.contextPath}/user/${user.id}/articles">文章</a>
                                    </li>
                                    <li role="tab" class="Tabs-item">
                                        <a class="Tabs-link"
                                           href="${pageContext.request.contextPath}/user/${user.id}/codes">代码</a>
                                    </li>
                                    <li role="tab" class="Tabs-item">
                                        <a class="Tabs-link"
                                           href="${pageContext.request.contextPath}/user/${user.id}/collections">收藏</a>
                                    </li>
                                </ul>
                            </div>
                            <!-- 用户动态详细数据区 -->
                            <div class="List" id="Profile-activities">
                                <c:forEach var="topic" items="${topics}">
                                    <!-- 用户动态详细数据单项 -->
                                    <div class="List-item">
                                        <!-- 操作主贴信息 -->
                                        <div class="ContentItem">
                                            <h2 class="ContentItem-title">
                                                <a target="_blank"
                                                   href="${pageContext.request.contextPath}/tutorial/${topic.id}">${topic.title}</a>
                                            </h2>
                                            <div class="ContentItem-meta">
                                                <div class="AnswerItem-meta">
                                                    <div class="AuthorInfo">
                                                          <span class="UserLink AuthorInfo-avatarWrapper">
                                                            <div class="Popover">
                                                                <img
                                                                        class="Avatar AuthorInfo-avatar"
                                                                        src="${user.avatar}"
                                                                        style="width: 38px; height: 38px;">
                                                            </div>
                                                          </span>
                                                        <div class="AuthorInfo-content">
                                                            <div class="AuthorInfo-name"><span class="UserLink"><div
                                                                    class="Popover"><div><a
                                                                    class="UserLink-link"
                                                                    href="${pageContext.request.contextPath}/user/${user.id}/home">${user.name}</a></div>
                                                                    </div></span></div>
                                                            <div class="RichText AuthorInfo-badge">${user.description}</div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- 阅读全文按钮 -->
                                            <div class="ContentItem-content is-collapsed">
                                                <span class="RichText CopyrightRichText-richText">${topic.content}</span>
                                                <a class="Button ContentItem-more Button--plain"
                                                   type="button"
                                                   href="${pageContext.request.contextPath}/tutorial/${topic.id}">
                                                    阅读全文
                                                </a>
                                            </div><!-- 阅读全文按钮结束 -->
                                        </div> <!-- 操作主贴信息结束 -->
                                    </div><!-- 用户动态详细数据单项结束 -->
                                </c:forEach>
                            </div><!-- 用户动态详细数据区结束 -->
                        </div><!-- 用户动态数据区左侧结束 -->
                    </div><!-- 用户动态数据区结束 -->
                </div>
            </div>
        </main>
    </div>
</div>
</body>
</html>
