
//获取项目根目录
// var rootPath = function (){
//     var strFullPath=window.document.location.href;
//     var strPath=window.document.location.pathname;
//     var pos=strFullPath.indexOf(strPath);
//     var prePath=strFullPath.substring(0,pos);
//     var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
//     return(prePath+postPath);
// }

//本机测试用-设置项目根目录
var rootPath = function () {
    return 'http://localhost:8080';
}


//初始化版块下拉框
function initSid() {
    $.post(rootPath() + '/api/getAllSections',
        function (data) {
            var count = data.length;
            for(var i = 0;i < count;i++)
                document.getElementById('sidSelection').options[i] = new Option(data[i].name,data[i].id);
        },
        "json"
    );
}

//初始化类别下拉框
function initTypeId() {
    $.post(rootPath() + '/api/getAllTypes',
        function (data) {
            var count = data.length;
            for(var i = 0;i < count;i++)
                document.getElementById('typeIdSelection').options[i] = new Option(data[i].name,data[i].id);
        },
        "json"
    );
}

//初始化标签下拉框
function updateTidSelection(id){
    $.ajax({
        type: 'get',
        url:rootPath() + '/api/getTagsByPid/' + id,
        dataType:'json',
        success:function(data){
            var count = data.length;
            for(var i = 0;i < count;i++)
                document.getElementById('tidSelection').options[i] = new Option(data[i].name,data[i].id);
        },
        error:function(data){
        }
    });
}

//初始化角色下拉框
function initRid() {
    $.post(rootPath() + '/api/getAllRoles',
        function (data) {
            var count = data.length;
            for(var i = 0;i < count;i++)
                document.getElementById('ridSelection').options[i] = new Option(data[i].name,data[i].id);
        },
        "json"
    );
}




