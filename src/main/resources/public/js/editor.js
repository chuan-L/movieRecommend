$(document).ready(function () {
    //https://www.kancloud.cn/wangfupeng/wangeditor3/332599  编辑器文档
    //获得参数
    var s = window.location.search
    s = s.substring(s.lastIndexOf("?")+1,s.length)
    var userId = s.substring(s.indexOf('=')+1,s.indexOf('&'))
    s = s.substring(s.indexOf('&')+1,s.length)
    var topicId = s.substring(s.indexOf('=')+1,s.length)

    //loadEditor()
    var E = window.wangEditor
    var editor = new E('#editor')
    editor.create()

    $("#submit").on("click",function () {
        var html = editor.txt.html()
        $.ajax({
            type:'post',
            url:'/review/add',
            data: JSON.stringify({
                'userId': userId,
                'topicId':topicId,
                'content':html,
            }),
            contentType: "application/json",
            success:function (res) {
                window.location.href  ='/topic.html?'+topicId;
            }
        })
    })


    function loadEditor() {
        var E = window.wangEditor
        var editor = new E('#editor')
        editor.create()

        document.getElementById('btn1').addEventListener('click', function () {
            // 读取 html
            alert(editor.txt.html())
        }, false)

        document.getElementById('btn2').addEventListener('click', function () {
            // 读取 text
            alert(editor.txt.text())
        }, false)
    }
})
