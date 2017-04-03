//-----------------------------------------------------------

//初始化版块下拉框
function initSid() {
    $('#sidSelection').html('');
    $.post('/api/getAllSections',
        function (data) {
            document.getElementById('sidSelection').options[0] = new Option("请选择版块", 0);
            for (var i = 0; i < data.length; i++)
                document.getElementById('sidSelection').options[i + 1] = new Option(data[i].name, data[i].id);
        },
        "json"
    );
}

//初始化类别下拉框
function initTypeSelection() {
    $('#typeIdSelection').html('');
    $.post('/api/getFirstTags',
        function (data) {
            document.getElementById('typeIdSelection').options[0] = new Option("请选择类别", 0);
            for (var i = 0; i < data.length; i++)
                document.getElementById('typeIdSelection').options[i + 1] = new Option(data[i].name, data[i].id);
        },
        "json"
    );
}

//初始化标签下拉框
function updateTagSelection(id) {
    $('#tidSelection').html('');
    $.ajax({
        type: 'get',
        url: '/api/getTagsByPid/' + id,
        dataType: 'json',
        success: function (data) {
            document.getElementById('tidSelection').options[0] = new Option("请选择标签", 0);
            for (var i = 0; i < data.length; i++)
                document.getElementById('tidSelection').options[i + 1] = new Option(data[i].name, data[i].id);
        },
        error: function (data) {
        }
    });
}

//初始化角色下拉框
function initRid() {
    $('#ridSelection').html('');
    $.post('/api/getAllRoles',
        function (data) {
            document.getElementById('ridSelection').options[0] = "请选择角色";
            for (var i = 0; i < data.length; i++)
                document.getElementById('ridSelection').options[i + 1] = new Option(data[i].name, data[i].id);
        },
        "json"
    );
}

//------------------------------------------------------
//编辑器提交
function editorSubmit(type) {
    var editor = new wangEditor('contentDiv');
    editor.create();
    $("#subBtn").click(function () {
        var d = {
            "title" : $("#title").val(),
            "tid" : parseInt($("#tidSelection").val()),
            "aid" : $("#aid").val(),
            "addTime" : new Date(),
            "content" : filterXSS(editor.$txt.html())
        };
        $.ajax({
            type: 'PUT',
            url: '/' + type,
            data: JSON.stringify(d),
            contentType: 'application/json',
            success: function (data) {
                if (data == true) {
                    alert("成功发表！");
                }
            },
            error: function () {
                alert("提问失败，请稍后重试！");
            }
        });
    });
}


//------------------------------------------------------------------------------------------------

//初始化列表页面相关状态
function initListStatus() {
    //处理页面类型
    if ($("#type").val() != "") {//如果是按未回答和最热筛选
        $("#nav_new").removeAttr("class");
        $("#nav_" + $("#type").val()).attr("class", "active");
    }
    //处理筛选方式超链接
    var type = $("#type").val();
    if (type == "") {//如果按默认分类
        $("#nav_new_a").attr("href", "./new/1");
        $("#nav_hottest_a").attr("href", "./hottest/1");
        $("#nav_unanswered_a").attr("href", "./unanswered/1");
    }
    else {//如果按unanswered或hottest筛选或new
        $("#nav_new_a").attr("href", "../new/1");
        $("#nav_hottest_a").attr("href", "../hottest/1");
        $("#nav_unanswered_a").attr("href", "../unanswered/1");
    }
}

//初始化分页
function initPag(currentPage,totalPage){
    var c = parseInt(currentPage);
    var t = parseInt(totalPage);
    if(t <=1 ) $("#Pagination").remove();
    //绘制首页上一页和上上一页
    if(c -2 <= 1) $("#pagTop").remove();
    if(c -2 >= 1) $("#pagCurrent").before("<li><a href='./" + (c- 2) +  "'>" +(c-2) +  "</a></li>");
    if(c - 1 >= 1) $("#pagCurrent").before("<li><a href='./" + (c- 1) +  "'>" +(c-1) +  "</a></li>");
    //绘制尾页下一页和下下一页
    if(c +2 < t) $("#pagCurrent").after("<li><a href='./" + (c + 2) +  "'>" +(c + 2) +  "</a></li>");
    if(c + 1 <= t) $("#pagCurrent").after("<li><a href='./" + (c + 1) +  "'>" +(c + 1) +  "</a></li>");
    if(c + 1 > t) $("#pagBottom").remove();
}


//检测主贴相关状态
function initTopicStatus(topicType) {
    //处理顶部主贴类型文字
    if('tutorial' == topicType) $("#topicText").text('教');
    else if ('discuss' == topicType) $("#topicText").text('问');
    else if ('article' == topicType) $("#topicText").text('文');
    else $("#topicText").text('码');
    //处理被关闭的主贴
    if ($('#condition_isBlock').val() == "false" && $('#blockButton').length > 0)
        $('#blockButton').removeAttr('disabled');
    else if ($('#condition_isBlock').val() == "true")
        $('#blockTip').attr('type', 'text');
    //处理被关闭的评论
    $('article[id^=replyArticle-]').each(function () {
        var id = $(this).attr("id").substring(13);
        //对于被关闭的评论，隐藏它的内容
        if ($('#condition-replyStatus-' + id).val() == "true")
            $('#replyContent-' + id).html("该评论已被关闭");
        //对于未被关闭的评论
        else if ($('#condition-replyStatus-' + id).val() == "false") {
            //在关闭评论按钮存在的情况下，启用该按钮
            if ($('#blockReplyButton-' + id).length > 0)
                $('#blockReplyButton-' + id).removeAttr("disabled");
        }
    });
    //处理收藏主贴按钮
    var condition = {
        "uid": $('#condition_uid').val(),
        "obid": Number($("#condition_id").val())
    };
    //用户登录时查询收藏状态
    if ($('#condition_uid').val() != "") {
        $.ajax({
            type: 'POST',
            url: '/api/getIsFavorite',
            data: JSON.stringify(condition),
            contentType: 'application/json',
            async: false,
            success: function (data) {
                if (data == false) {
                    $('#favoriteButton').removeAttr('disabled');
                }
            },
            error: function () {
                alert("查看收藏状态失败！");
            }
        });
    }
    //处理附件大小
    if($("#attSize").length > 0){
        var bytes = parseInt($("#attSize").val());
        var k = 1024;
        var sizes = ['B','KB', 'MB', 'GB'];
        var i = Math.floor(Math.log(bytes) / Math.log(k));
        var size =  (bytes / Math.pow(k, i));
        $("#attHref").append("&emsp;&emsp;大小:" + parseFloat(size).toFixed(2) + sizes[i]);
    }
}

//查询投票信息
function initRateInfo(id,dom) {
    var d = {"opid"  :id};
    $.ajax({
        type: 'POST',
        url: '/api/getRateInfo',
        contentType: 'application/json',
        data: JSON.stringify(d),
        success: function (data) {
            if ("like" == data) {
                $("#like-" + dom).css("background-color","#A4D3EE");
            }
            else if("hate" == data){
                $("#hate-" + dom).css("background-color","#A4D3EE");
            }
        },
        error: function () {
            alert('发送请求失败！');
        }
    });

}


//赞同主贴
function likeTopic(topicType,id) {
    var da = {"id" : parseInt(id),"aid" : $("#condition_aid").val(), "section": {"name" : topicType}};
    var d = {"opid"  :id};
    $.ajax({
        type: 'POST',
        url: '/api/getRateInfo',
        contentType: 'application/json',
        data: JSON.stringify(d),
        success: function (data) {
            if(data == "err" && $('#condition_uid').val() != ""){
                $.ajax({
                    type: 'PUT',
                    url: '/' + topicType + '/' + id + '/like',
                    contentType: 'application/json',
                    data: JSON.stringify(da),
                    success: function (data) {
                        if(data == true){
                            $("#like-0").css("background-color","#A4D3EE");
                            $("#hate-0").attr("disabled","disabled");
                        }
                        else
                            alert('投票失败，请稍后重试！');
                    },
                    error: function () {
                        alert('发送请求失败！');
                    }
                });
            }
            else
                alert("已投票，请勿重复投票");

        },
        error: function () {
            alert('发送请求失败！');
        }
    });

}

//反对主贴
function hateTopic(topicType,id) {
    var da = {"id" : parseInt(id),"aid" : $("#condition_aid").val(),"section": {"name" : topicType}};
    var d = {"opid"  :id};
    $.ajax({
        type: 'POST',
        url: '/api/getRateInfo',
        contentType: 'application/json',
        data: JSON.stringify(d),
        success: function (data) {
            if(data == "err" && $('#condition_uid').val() != ""){
                $.ajax({
                    type: 'PUT',
                    url: '/' + topicType + '/' + id + '/hate',
                    data : JSON.stringify(da),
                    contentType: 'application/json',
                    success: function (data) {
                        if(data == true){
                            $("#hate-0").css("background-color","#A4D3EE");
                            $("#like-0").attr("disabled","disabled");
                        }
                        else
                            alert('投票失败，请稍后重试！');
                    },
                    error: function () {
                        alert('发送请求失败！');
                    }
                });
            }
            else
                alert("已投票，请勿重复投票");
        },
        error: function () {
            alert('发送请求失败！');
        }
    });
}


//删除主贴
function deleteTopic(topicType) {
    var d = {
        "id": $("#condition_id").val(),
        "aid": $("#condition_aid").val(),
        "title": $("#topicTitle").val(),
        "section": {"name": topicType}
    };
    $.ajax({
        type: 'delete',
        url: '/' + topicType + '/' + $("#condition_id").val(),
        data: JSON.stringify(d),
        contentType: 'application/json',
        success: function (data) {
            if (data == true) {
                alert("删除成功");
            }
            else {
                alert("未能成功删除");
            }
            location.reload();
        },
        error: function () {
            alert("提交删除请求失败");
        }
    });
}

//更新教程
function updateTopic(topicType) {
    var d = {
        "id": parseInt($("#id").val()),
        "aid": $("#aid").val(),
        "title": $("#title").val(),
        "content": $("#content").val(),
        "section": {"name": $("#sectionname").val()}
    };
    $.ajax({
        type: 'PUT',
        url: '/' + topicType + '/' + $("#condition_tid").val() + '/update',
        data: JSON.stringify(d),
        contentType: 'application/json',
        success: function (data) {
            if (data == true) {
                alert("成功修改该贴");
            }
            else {
                alert("未能修改该贴");
            }
        },
        error: function () {
            alert("发送更新主贴请求失败！");
        }
    });
}


//收藏话题
function favoriteTopic(topicType) {
    var d = {
        "id": parseInt($("#condition_id").val()),
        "aid": $("#condition_aid").val(),
        "title": $("#topicTitle").val(),
        "section": {"name": topicType}
    };
    $.ajax({
        type: 'POST',
        url: '/' + topicType + '/' + $('#condition_id').val() + '/favorite',
        data: JSON.stringify(d),
        contentType: 'application/json', //设置请求头信息
        success: function (data) {
            if (data == true) {
                alert("成功收藏该贴");
                location.reload();
            }
            else {
                alert("未能收藏该帖");
            }
        },
        error: function () {
            alert("发送收藏请求失败！");

        }
    });
}

//关闭话题
function blockTopic(resultType) {
    var d = {
        "id": $("#condition_id").val(),
        "aid": $("#condition_aid").val(),
        "title": $("#topicTitle").val(),
        "section": {"name": resultType}
    };
    $.ajax({
        type: 'POST',
        url: '/' + resultType + '/' + $("#condition_id").val() + '/block',
        data: JSON.stringify(d),
        contentType: 'application/json',
        success: function (data) {
            if (data == true) {
                alert('成功关闭该话题');
            }
            else {
                alert('未能关闭该话题');
            }
        },
        error: function () {
            alert('发送请求失败！');
        }
    });
}

//------------------------------------------------------------------------------------------------
//发表评论
function addReply() {
    var d = {
        "tid": Number($('#condition_id').val()),
        "title": $('#topicTitle').val(),
        "uid": $('#condition_uid').val(),
        "content": $('#replyContent').val(),
        "addTime": new Date()
    };
    alert(JSON.stringify(d));
    $.ajax({
        type: 'POST',
        url: '/reply',
        data: JSON.stringify(d),
        contentType: 'application/json',
        success: function (data) {
            if (data == true) {
                alert('成功发表评论');
                location.reload();
            }
            else {
                alert('未能发表评论，请稍后重试' +data);
            }
        },
        error: function () {
            alert('发送请求失败！');
        }
    });
}

//删除评论
function deleteReply(id, uid) {
    var d = {
        "id": Number(id),
        "tid": Number($('#condition_id').val()),
        "title": $("#topicTitle").val(),
        "uid": uid
    };
    $.ajax({
        type: 'DELETE',
        url: '/reply',
        data: JSON.stringify(d),
        contentType: 'application/json',
        success: function (data) {
            if (data == true) {
                alert('成功删除评论');
                location.reload();
            }
            else {
                alert('未能删除评论，请稍后重试');
            }
        },
        error: function () {
            alert('发送请求失败！');
        }
    });
}


//关闭评论
function blockReply(id, uid) {
    var d = {
        "id": Number(id),
        "tid": Number($('#condition_id').val()),
        "title": $("#topicTitle").val(),
        "uid": uid
    };
    $.ajax({
        type: 'PUT',
        url: '/reply',
        data: JSON.stringify(d),
        contentType: 'application/json',
        success: function (data) {
            if (data == true) {
                alert('成功关闭评论');
                location.reload();
            }
            else {
                alert('未能关闭评论，请稍后重试');
            }
        },
        error: function () {
            alert('发送请求失败！');
        }
    });
}

//采纳评论
function adoptReply(id, uid) {
    var d = {
        "id": Number(id),
        "tid": Number($('#condition_id').val()),
        "title": $("#topicTitle").val(),
        "uid": uid
    };
    $.ajax({
        type: 'POST',
        url: '/reply/adopt',
        data: JSON.stringify(d),
        contentType: 'application/json',
        success: function (data) {
            if (data == true) {
                alert('成功采纳评论');
                location.reload();
            }
            else {
                alert('未能采纳评论，请稍后重试');
            }
        },
        error: function () {
            alert('发送请求失败！');
        }
    });
}

//赞同回复
function likeReply(id,uid) {
    var da = {"id" : parseInt(id), "uid" : uid};
    var d = {"opid"  :id};
    $.ajax({
        type: 'POST',
        url: '/api/getRateInfo',
        contentType: 'application/json',
        data: JSON.stringify(d),
        success: function (data) {
            if(data == "err" && $('#condition_uid').val() != ""){
                $.ajax({
                    type: 'PUT',
                    url: '/reply/like',
                    data : JSON.stringify(da),
                    contentType: 'application/json',
                    success: function (data) {
                        if (data == false) {
                            alert('投票失败，请稍后重试！');
                        }
                    },
                    error: function () {
                        alert('发送请求失败！');
                    }
                });
            }
            else
                alert("已投票，请勿重复投票");
        },
        error: function () {
            alert('发送请求失败！');
        }
    });
}

//反对回复
function hateReply(id,uid) {
    var da = {"id" : parseInt(id), "uid" : uid};
    var d = {"opid"  :id};
    $.ajax({
        type: 'POST',
        url: '/api/getRateInfo',
        contentType: 'application/json',
        data: JSON.stringify(d),
        success: function (data) {
            if(data == "err" && $('#condition_uid').val() != ""){
                $.ajax({
                    type: 'PUT',
                    url: '/reply/hate',
                    data : JSON.stringify(da),
                    contentType: 'application/json',
                    success: function (data) {
                        if (data == false) {
                            alert('投票失败，请稍后重试！');
                        }
                    },
                    error: function () {
                        alert('发送请求失败！');
                    }
                });
            }
            else
                alert("已投票，请勿重复投票");
        },
        error: function () {
            alert('发送请求失败！');
        }
    });
}

//------------------
//将消息标记为已读
function signNtf(uid, id) {
    $.ajax({
        type: 'PUT',
        url: '/user/' + uid + '/ntfs/' + id,
        contentType: 'application/json',
        success: function (data) {
            if (data == true) {
                location.reload();
            }
            else if (data == false) {
                alert('未能标记，请稍后重试');
            }
        },
        error: function () {
            alert('发送请求失败！');
        }
    });
}

//--------------------

//加载用户主页分类样式
function initUserList(type) {
    $("#Tabs-" + type + "s").attr("class", "Tabs-link is-active");
}

//用户修改密码
function updateAccount(uid) {
    if ($('#newPass').val() != null && $('#newPass').val().split(' ').join('') != '' && $('#newPass').val() == $('#confirmPass').val()) {
        $.ajax({
            type: 'PUT',
            url: '/user/' + uid + '/account',
            contentType: 'application/json',
            data: $('#newPass').val(),
            success: function (data) {
                if (data == true) {
                    alert("您已成功修改密码!");
                    location.reload();
                }
                else if (data == false) {
                    alert("未能修改密码，请稍后重试!");
                }
            },
            error: function () {
                alert('发送请求失败！');
            }
        });
    }
    else {
        alert("密码不符合要求，请重新确认");
        window.location.reload();
    }
}

//用户上传头像
function uploadAvatar() {
    var formData = new FormData();
    formData.append('file', $('#avatarFile')[0].files[0]);
    $.ajax({
        url: '/file/upload/avatar',
        type: 'POST',
        cache: false,
        data: formData,
        processData: false,
        contentType: false,
        success: function (data) {
            if (data == "error") {
                $("#uploadAvatarButton").attr("class", "btn btn-error");
                $("#uploadAvatarButton").attr("value","上传失败");
            }
            else {
                $("#uploadAvatarButton").attr("class", "btn btn-success");
                $("#uploadAvatarButton").attr("value","上传成功");
                $("#avatarPanel").append("<input type='hidden' id='avatar' value=' " + data + "'>");
            }
        },
        error: function () {
            alert('发送请求失败！');
        }
    });
}

//用户修改信息
function updateProfile(uid) {
    var d = {
        "id" : uid,
        "name" : $("#name").val(),
        "description" : $("#description").val(),
        "email" : $("#email").val(),
        "avatar" : $("#avatar").val()
    };
    $.ajax({
        url: '/user/' + uid + '/profile',
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(d),
        success: function (data) {
            if (data == true) {
                alert('成功修改个人信息');
            }
            else {
                alert('未能修改个人信息！请稍后重试');
            }
        },
        error: function () {
            alert('发送请求失败！');
        }
    });


}

//用户上传文件
function uploadFile() {
    var formData = new FormData();
    formData.append('file', $('#codeFile')[0].files[0]);
    $("#uploadBtn").attr("value","上传中...");
    $.ajax({
        url: '/file/upload/file',
        type: 'POST',
        cache: false,
        data: formData,
        processData: false,
        contentType: false,
        success: function (data) {
            if (data == "error") {
                $("#uploadBtn").attr("class","btn btn-danger");
                $("#uploadBtn").attr("value","上传失败");
            }
            else {
                $("#uploadBtn").attr("class","btn btn-success");
                $("#uploadBtn").attr("value","上传成功");
                $("#attachmentDiv").append("<input type='hidden' name='attid' value=' " + data + " '>");

            }
        },
        error: function () {
            alert('发送请求失败！');
        }
    });
}

//----------
//扩展：用户上传头像实时预览
jQuery.fn.extend({
    uploadPreview: function (opts) {
        var _self = this,
            _this = $(this);
        opts = jQuery.extend({
            Img: "ImgPr",
            Width: 100,
            Height: 100,
            // ImgType: ["gif", "jpeg", "jpg", "bmp", "png"],
            Callback: function () {}
        }, opts || {});
        _self.getObjectURL = function (file) {
            var url = null;
            if (window.createObjectURL != undefined) {
                url = window.createObjectURL(file)
            } else if (window.URL != undefined) {
                url = window.URL.createObjectURL(file)
            } else if (window.webkitURL != undefined) {
                url = window.webkitURL.createObjectURL(file)
            }
            return url
        };
        _this.change(function () {
            if (this.value) {
                    try {
                        $("#" + opts.Img).attr('src', _self.getObjectURL(this.files[0]))
                    } catch (e) {
                        var src = "";
                        var obj = $("#" + opts.Img);
                        var div = obj.parent("div")[0];
                        _self.select();
                        if (top != self) {
                            window.parent.document.body.focus()
                        } else {
                            _self.blur()
                        }
                        src = document.selection.createRange().text;
                        document.selection.empty();
                        obj.hide();
                        obj.parent("div").css({
                            'filter': 'progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)',
                            'width': opts.Width + 'px',
                            'height': opts.Height + 'px'
                        });
                        div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = src;
                    }
                opts.Callback()
            }
        })
    }
});
