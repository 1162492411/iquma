
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>SegmentFault</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://sf-static.b0.upaiyun.com/v-5812e736/global/css/global.css">
    <link rel="stylesheet" href="https://sf-static.b0.upaiyun.com/v-5812e736/qa/css/qa.css">
    <link rel="stylesheet" href="https://sf-static.b0.upaiyun.com/v-5812e736/global/css/responsive.css">
    <!--[if lt IE 9]><link rel="stylesheet" href="https://sf-static.b0.upaiyun.com/v-5812e736/global/css/ie.css"/><script src="https://sf-static.b0.upaiyun.com/v-5812e736/global/script/html5shiv.js"></script><script src="https://sf-static.b0.upaiyun.com/v-5812e736/global/script/respond.js"></script><![endif]-->
    <script src="//hm.baidu.com/hm.js?e23800c454aa573c0ccb16b52665ac26"></script>
    <script type="text/javascript" async="" src="https://dn-growing.qbox.me/vds.js"></script>
    <script async="" src="//www.google-analytics.com/analytics.js"></script>
    <script src="https://sf-static.b0.upaiyun.com/v-5812e736/global/script/debug.js"></script>
    <script type="text/javascript" charset="utf-8" async="" data-requirecontext="_" data-requiremodule="https://sf-static.b0.upaiyun.com/v-5812e736/3rd/socket/socket.io.js" src="https://sf-static.b0.upaiyun.com/v-5812e736/3rd/socket/socket.io.js"></script>
</head>
<body data-mod="qa" class="qa-index ">
<img id="icon4weChat" style="height: 0;width: 0;" data-src="https://sf-static.b0.upaiyun.com/v-5812e736/global/img/touch-icon-512.png">
<div class="global-nav sf-header">
    <nav class="container nav">
        <div class="row hidden-xs">
            <div class="col-sm-9 col-md-7 col-lg-6">
                <div class="sf-header__logo">
                    <h1>
                        <a href="/">爱趣码</a>
                    </h1>
                </div>
                <ul class="menu list-inline pull-left hidden-xs">
                    <li class="menu__item menu__item--new">
                        <a href="/tutorials">教程</a>
                    </li>
                    <li class="menu__item">
                        <a href="/discusses">问答</a>
                    </li>
                    <li class="menu__item">
                        <a href="/articles">经验</a>
                    </li>
                    <li class="menu__item">
                        <a href="/codes">代码</a>
                    </li>
                </ul>
            </div>
            <div class="col-sm-3 col-md-5 col-lg-6 text-right">
                <form action="/search" class="header-search  hidden-sm hidden-xs mr20">
                    <button class="btn btn-link">
                        <span class="sr-only">搜索</span>
                        <span class="glyphicon glyphicon-search"></span>
                    </button>
                    <input id="searchBox" name="q" placeholder="输入关键字搜索" class="form-control" value="" type="text"></form>

                <ul class="opts list-inline hidden-xs">
                    <li class="opts__item dropdown hoverDropdown write-btns">
                        <a class="dropdownBtn" data-toggle="dropdown" href="/ask">发布<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-right ">
                            <li>
                                <a href="/ask">提问题</a>
                            </li>
                            <li>
                                <a href="/write">写经验</a>
                            </li>
                            <li>
                                <a href="/upload">传代码</a>
                            </li>
                        </ul>
                    </li>


                    <li class="opts__item user dropdown hoverDropdown">
                        <a class="dropdownBtn user-avatar" data-toggle="dropdown" style="background-image: url('https://sf-static.b0.upaiyun.com/v-5812e736/global/img/user-64.png')" href="/user/home/"></a>
                        <ul class="dropdown-menu dropdown-menu-right">
                            <li>
                                <a href="/user/home/${userid}">我的主页</a>
                            </li>
                            <li>
                                <a href="/user/profile/${userid}">我的档案</a>
                            </li>
                            <li>
                                <a href="/user/account/${userid}">安全设置</a>
                            </li>
                            <li>
                                <a href="/user/forgot/${userid}">忘记密码</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="/login" >登录</a>
                                <a href="/user/logout">退出</a>
                            </li>
                        </ul>
                    </li>

                </ul>

            </div>
        </div>
    </nav>
</div>
</body>
</html>

