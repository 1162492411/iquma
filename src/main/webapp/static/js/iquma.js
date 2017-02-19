



//-----------------------------------------------------------

//初始化版块下拉框
function initSid() {
    $('#sidSelection').html('');
    $.post('/api/getAllSections',
        function (data) {
            document.getElementById('sidSelection').options[0] =new Option("请选择版块",0);
            for(var i = 0;i < data.length;i++)
                document.getElementById('sidSelection').options[i+1] = new Option(data[i].name,data[i].id);
        },
        "json"
    );
}

//初始化类别下拉框
function initTypeId() {
    $('#typeIdSelection').html('');
    $.post('/api/getFirstTags',
        function (data) {
            document.getElementById('typeIdSelection').options[0] = new Option("请选择类别",0);
            for(var i = 0;i < data.length;i++)
                document.getElementById('typeIdSelection').options[i+1] = new Option(data[i].name,data[i].id);
        },
        "json"
    );
}

//初始化标签下拉框
function updateTidSelection(id){
    $('#tidSelection').html('');
    $.ajax({
        type: 'get',
        url:'/api/getTagsByPid/' + id,
        dataType:'json',
        success:function(data){
            document.getElementById('tidSelection').options[0] = new Option("请选择标签",0);
            for(var i = 0;i < data.length;i++)
                document.getElementById('tidSelection').options[i+1] = new Option(data[i].name,data[i].id);
        },
        error:function(data){
        }
    });
}

//初始化角色下拉框
function initRid() {
    $('#ridSelection').html('');
    $.post('/api/getAllRoles',
        function (data) {
            document.getElementById('ridSelection').options[0] = "请选择角色";
            for(var i = 0;i < data.length;i++)
                document.getElementById('ridSelection').options[i+1] = new Option(data[i].name,data[i].id);
        },
        "json"
    );
}


//------------------------------------------------------------------------------------------------
//检测主贴相关状态
function initTopicStatus() {
    //处理被关闭的主贴
    if($('#condition_isBlock').val() == "false" && $('#blockButton').length > 0 )
        $('#blockButton').removeAttr('disabled');
    else if($('#condition_isBlock').val() == "true")
        $('#blockTip').attr('type','text');
    //处理被关闭的评论
    $('article[id^=replyArticle-]').each(function () {
        var id = $(this).attr("id").substring(13);
        //对于被关闭的评论，隐藏它的内容
        if($('#condition-replyStatus-' + id).val() == "true")
            $('#replyContent-' + id).html("该评论已被关闭");
        //对于未被关闭的评论
        else if($('#condition-replyStatus-' + id).val() == "false"){
            //在关闭评论按钮存在的情况下，启用该按钮
            if($('#blockReplyButton-' + id).length > 0)
                $('#blockReplyButton-' + id).removeAttr("disabled");
            //用户登录且不存在最佳评论且用户浏览的是自己的主贴时，为评论添加采纳按钮
            if($('input[id=condition-isBestReply]').length == 0 && $('#condition_uid').val() != null && $('#condition_aid').val() != null && $('#condition_uid').val() == $('#condition_aid').val())
                $('ul[id=replyUl-' + id+ ']').append("<li><Button class='btn btn-primary' id='adoptReplyButton- " + id + "' onclick='adoptReply(" + id + ")'>采纳</Button></li>");
        }
    });
    //处理收藏主贴按钮
    var condition = {
        "uid" : $('#condition_uid').val(),
        "obid" : Number($("#condition_tid").val())
    };
    if($('#condition_uid').val() != ""){
        $.ajax({
            type : 'POST',
            url : '/api/getIsFavorite',
            data : JSON.stringify(condition),
            contentType : 'application/json',
            async : false,
            success : function (data) {
                if(data == false){
                    $('#favoriteButton').removeAttr('disabled');
                }
            },
            error : function () {
                alert("查看收藏状态失败！");
            }
        });
    }
}

//赞同主贴
function likeTopic() {
    //TODO:该方法待补充
    alert("赞同了该帖");
}

//反对主贴
function hateTopic() {
    //TODO:该方法待补充
    alert("反对了该帖");
}


//删除主贴
function deleteTopic(topicType){
    $.ajax({
        type: 'delete',
        url:'/' + topicType + '/'+ $("#condition_tid").val(),
        dataType:'text',
        success:function(data){
            if(data=="suc"){
                alert("删除成功");
            }
            else if (data=="err"){
                alert("未能成功删除");
            }
            location.reload();
        },
        error:function(){
            alert("提交删除请求失败");
        }
    });
}

//更新教程
function updateTopic(topicType) {
    var d = {};
    var t = $('#updateTutorialForm').serializeArray();
    $.each(t, function() {
        d[this.name] = this.value;
    });
    $.ajax({
        type : 'PUT',
        url: '/' + topicType + '/' + $("#condition_tid").val() + '/update',
        data : JSON.stringify(d),
        contentType : 'application/json',
        success: function(data){
            if(data == "suc"){
                alert("成功修改该贴");
            }
            else if(data == "err"){
                alert("未能修改该贴")
            }
        },
        error:function(){
            alert("发送请求失败!");
        }
    });
}


//收藏话题
function favoriteTopic(resultType) {
    var d = {
        "uid" : $('#condition_uid').val(),
        "obid" : Number($("#condition_tid").val()),
        "favTime" : new Date()
    };
    $.ajax({
        type : 'POST',
        url : '/' + resultType + '/' + $('#condition_tid').val() + '/favorite',
        data : JSON.stringify(d),
        contentType : 'application/json',
        success : function (data) {
            if(data == "suc"){
                alert("成功收藏该贴");
                location.reload();
            }
            else if (data == "err"){
                alert("未能收藏该帖");
            }
        },
        error : function () {
            alert("发送收藏请求失败！");
        }
    });
}

//关闭话题
function blockTopic(resultType){
    var d = {
        "uid" : $('#condition_uid').val(),
        "opid" : $("#condition_tid").val(),
        "optime" : new Date()
    };
    $.ajax({
        type : 'POST',
        url : '/' + resultType + '/' + $("#condition_tid").val() + '/block',
        data : JSON.stringify(d),
        contentType : 'application/json',
        success : function (data){
            if(data == "suc"){
                alert('成功关闭该话题');
            }
            else if (data == "err"){
                alert('未能关闭该话题');
            }
        },
        error : function (){
            alert('发送请求失败！');
        }
    });
}

//------------------------------------------------------------------------------------------------
//发表评论
function addReply(){
    var d = {
        "tid" : Number($('#condition_tid').val()),
        "title" : $('#topicTitle').val(),
        "uid" : $('#condition_uid').val(),
        "content" : $('#replyContent').val(),
        "addTime" : new Date()
    };
    alert(JSON.stringify(d));
    $.ajax({
        type : 'POST',
        url : '/reply',
        data : JSON.stringify(d),
        contentType : 'application/json',
        success : function (data){
            if(data == true){
                alert('成功发表评论');
                location.reload();
            }
            else if (data == false){
                alert('未能发表评论，请稍后重试');
            }
        },
        error : function (){
            alert('发送请求失败！');
        }
    });
}

//删除评论
function deleteReply(id) {
    var d = Number(id);
    $.ajax({
        type : 'DELETE',
        url : '/reply',
        data : JSON.stringify(d),
        contentType : 'application/json',
        success : function (data){
            if(data == true){
                alert('成功删除评论');
                location.reload();
            }
            else if (data == false){
                alert('未能删除评论，请稍后重试');
            }
        },
        error : function (){
            alert('发送请求失败！');
        }
    });
}


//关闭评论
function blockReply(id) {
    var d = Number(id);
    $.ajax({
        type : 'PUT',
        url : '/reply',
        data : JSON.stringify(d),
        contentType : 'application/json',
        success : function (data){
            if(data == true){
                alert('成功关闭评论');
                location.reload();
            }
            else if (data == false){
                alert('未能关闭评论，请稍后重试');
            }
        },
        error : function (){
            alert('发送请求失败！');
        }
    });
}

//采纳评论
function adoptReply(id){
    var d = Number(id);
    $.ajax({
        type : 'POST',
        url : '/reply/adopt',
        data : JSON.stringify(d),
        contentType : 'application/json',
        success : function (data){
            if(data == true){
                alert('成功采纳评论');
                location.reload();
            }
            else if (data == false){
                alert('未能采纳评论，请稍后重试');
            }
        },
        error : function (){
            alert('发送请求失败！');
        }
    });
}

//赞同回复
function likeReply() {
    //TODO:该方法待补充
    alert("赞同了回复");
}

//反对回复
function hateReply() {
    //TODO:该方法待补充
    alert("反对了回复");
}

//------------------
//将消息标记为已读
function signNtf(uid,id) {
    //TODO:该方法待补充
    $.ajax({
        type : 'PUT',
        url : '/user/' + uid + '/ntfs/' + id,
        contentType : 'application/json',
        success : function (data){
            if(data == true){
                location.reload();
            }
            else if (data == false){
                alert('未能标记，请稍后重试');
            }
        },
        error : function (){
            alert('发送请求失败！');
        }
    });
}