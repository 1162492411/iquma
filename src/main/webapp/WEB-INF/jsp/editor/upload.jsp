<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>代码共享</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/editor/css/wangEditor.min.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/editor/js/wangEditor.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/xss.min.js" ></script>
    <script src="${pageContext.request.contextPath}/static/js/iquma.js"></script>
    <script type="text/javascript">
        $(function () {
            initTypeSelection();
            $("#typeIdSelection").change(function () {
                updateTagSelection($('#typeIdSelection option:selected').val());
            });
            editorSubmit('upload');
        });
    </script>
</head>
<body>
<!-- 提问 -->
<form class="form-group">
    <div class="panel panel-default" id="uploadDiv">
        <div class="panel-heading">代码</div>
        <div class="panel-body" id="TopicBody">
            <%--话题标题 --%>
            <div class="form-group" id="titlePanel">
                <label>代码标题</label>
                <input type="text" class="form-control" id="title" placeholder="代码标题"/>
            </div>
            <%--类别id --%>
            <div class="form-group" id="typeIdPanel">
                <label>类别</label>
                <select id="typeIdSelection">
                </select>
            </div>
            <%--标签id --%>
            <div class="form-group" id="tidPanel">
                <label>标签</label>
                <select name="tid" id="tidSelection">
                </select>
            </div>
            <%--作者id --%>
            <input type="hidden" id="aid" value="${userid}"/>
            <%-- 内容--%>
            <div class="form-group" id="contentPanel">
                <label>内容</label>
                <div name="content" id="contentDiv" style="height: 45%"></div>
            </div>
            <%-- 附件 --%>
            <div class="form-group" id="attachmentDiv">
                <input class="btn btn-primary"  value="添加附件" id="uploadBtn" onclick="uploadFile()" />
                <input type="file" class="btn" accept=".zip,.rar,.7z" value="选择文件" id="codeFile"  /><br />
            </div>
            <%-- 按钮 --%>
            <div class="form-group" id="buttonPanel">
                <input type="button" class="btn btn-primary" value="上传" id="subBtn"/>
                <input type="reset" class="btn btn-default" value="重置"/>
            </div>
        </div>
    </div>
    </div>
</form>
</body>
</html>
