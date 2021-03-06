<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人资料卡</title>
    <script type="text/javascript">
        $(document).ready(function () {
            initProCard(${user.isBlock});
        });
    </script>
</head>
<body>
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
                        <div class="ProfileButtonGroup ProfileHeader-buttons">
                            <!-- 若登录账户查看的是自己的主页，则显示编辑资料按钮 -->
                            <c:if test="${userid eq user.id}">
                            <a class="Button Button--blue"
                                href="${pageContext.request.contextPath}/user/${user.id}/profile"
                                role="button">编辑资料</a>
                            </c:if>
                            <!-- 若账户具有关闭账户的权限则显示关闭按钮 -->
                            <shiro:hasPermission name="suser:block">
                                <button class="Button Button--primary Button--blue"
                                   role="button" id="blockUserButton" onclick="blockUser(${user.id})">封禁</button>
                            </shiro:hasPermission>
                            <!-- 若账户具有删除账户的权限则显示删除账户按钮 -->
                            <shiro:hasPermission name="suser:delete">
                                <button class="Button Button--primary Button--red"
                                   role="button" onclick="deleteUser(${user.id})">删除</button>
                            </shiro:hasPermission>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- 顶部个人资料卡下部结束 -->
    </div>
</div><!-- 顶部个人资料卡结束 -->
</body>
</html>
