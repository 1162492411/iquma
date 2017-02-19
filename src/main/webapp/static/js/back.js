
//获取项目根目录
// var rootPath = function (){
//     var strFullPath=window.document.location.href;
//     var strPath=window.document.location.pathname;
//     var pos=strFullPath.indexOf(strPath);
//     var prePath=strFullPath.substring(0,pos);
//     var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
//     return(prePath+postPath);
// }

// 本机测试用-设置项目根目录
// var testRootPath = function () {
//     return 'http://localhost:8080';
// }

//---------------------------------------------------------------------------------------------------
// //将查询到的主贴结果数据加载到表格--通用
// function topicDataHandler (data,resultType) {
//     $("#topicTable").html("");//首先清空页面数据
//     var count = data.length;
//     for(var i = 0;i < count;i++){//遍历结果集
//         $("#topicTable").append(
//             '<tr id = ' + data[i].id + '>'
//             +"<td><a href='/"  +  resultType +  "/" + data[i].id + "'>" + data[i].title +"</a></td>"
//             + "<td>" + data[i].user.name + "</td>"
//             + "<td>" + data[i].rateCount + "</td>"
//             + "<td>" + data[i].viewCount + "</td>"
//             + "<td>" + data[i].tag.name + "</td>"
//             + "<td>" + new Date(data[i].reTime).toLocaleString().split(' ')[0].replace(/年|月/g, '-').replace(/日/g, '')  + "</td>"
//             + "</tr>");
//     }
// }
//
// //查询符合条件的主贴集合--通用
// function getNewTopics(page,condition,resultType) {
//     $.ajax({
//         type : 'POST',
//         url: '/api/getTopicByConditionAndPage/' + page,
//         data : JSON.stringify(condition),
//         contentType : 'application/json',
//         success: function(data){
//             topicDataHandler(data,resultType);
//         },
//         error:function(){
//             alert("获取新的查询结果失败!");
//         }
//     });
// }
//
// //获取符合条件的教程主贴集合--未完成
// function getNewTutorials(tagname,page,resultType) {
//     $.ajax({
//         type : 'GET',
//         url: '/' + resultType + '/' + tagname + '/' + page,
//         success: function(data){
//             topicDataHandler(data,resultType);
//         },
//         error:function(){
//             alert("获取新的查询结果失败!");
//         }
//     });
// }
//
// //页面中初始化分页插件--通用
// function initPages (tagname,resultType) {
//     var maxPage ;
//     $.ajax({
//         type : 'GET',
//         url: '/api/getTopicCountByTag/' + tagname,
//         async : false,
//         success: function(data){
//             maxPage = data;
//         },
//         error:function(){
//             alert("页面初始化时查询总页数失败!");
//         }
//     });
//
//     $('#topicPagination').jqPagination({
//         //进行一些初始化
//         current_page : 1,//当前页数
//         max_page : maxPage,
//         page_string : 'Page{current_page} of {max_page}',
//         paged: function (page) {
//             alert("即将翻页");
//             getNewTutorials(tagname,page,resultType);
//         }
//     });
// }


//-----------------------------------------------------------------------------------------------------
// //将查询到的评论结果数据加载到表格--通用
// function replyDataHandler (data) {
//     $("#replyTable").html("");//首先清空页面数据
//     var count = data.length;
//     for(var i = 0;i < count;i++){//遍历结果集
//         $("#replyTable").append(
//             '<tr id = ' + data[i].id + '>'
//             + "<td>" + data[i].user.name + "</td>"
//             + "<td>" + data[i].content + "</td>"
//             + "<td>" + data[i].rateCount + "</td>"
//             + "<td>" + new Date(data[i].addTime).toLocaleString().split(' ')[0].replace(/年|月/g, '-').replace(/日/g, '')  + "</td>"
//             + "</tr>");
//     }
// }
//
// //查询符合条件的评论集合--通用
// function getNewReplies(page,condition) {
//     $.ajax({
//         type : 'POST',
//         url: '/api/getReplyByConditionAndPage/' + page,
//         data : JSON.stringify(condition),
//         contentType : 'application/json',
//         success: function(data){
//             replyDataHandler(data);
//         },
//         error:function(){
//             alert("获取新的评论失败!");
//         }
//     });
// }
//
// //页面中初始化评论分页插件--通用
// function initReplyPages (condition) {
//     var maxReplyPage ;
//     $.ajax({
//         type : 'POST',
//         url: '/api/getReplyCountByCondition',
//         data : JSON.stringify(condition),
//         contentType : 'application/json',
//         async : false,
//         success: function(data){
//             maxReplyPage = data;
//         },
//         error:function(){
//             alert("评论初始化时查询总页数失败!");
//         }
//     });
//
//     $('#replyPagination').jqPagination({
//         //进行一些初始化
//         current_page : 1,//当前页数
//         max_page : maxReplyPage,
//         page_string : 'Page{current_page} of {max_page}',
//         paged: function (page) {
//             getNewReplies(page,condition);
//         }
//     });
//     getNewReplies(1,condition);
// }
