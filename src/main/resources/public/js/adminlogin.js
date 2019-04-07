$(document).ready(function () {
    $("#loginBtn").onclick(function () {
        var name = $("#name").val();
        var pswd = $("#password").val()
        var flag = true; //格式是否正确
        if(name.length < 4 || name.length > 16){
            $("#nameTip").text("用户名长度应在4-16位之间")
            $("#name").val("")
            flag = false
        }else{
            $("#nameTip").text("")
        }

        if(pswd.length < 4 || pswd.length > 16){
            $("#passwordTip").text("密码长度应在4-16位之间")
            $("#password").val("")
            flag = false
        }else{
            $("#passwordTip").text("")
        }
        if(flag == true){
            $.ajax({
                type:"post",
                url:"/admin/login",
                data: JSON.stringify({ 'name':name, 'password': pswd }),
                contentType: "application/json",
                cache: false,

                success: function(res){
                    console.log(res);
                    if(res){
                        if(res.code == 0){

                            window.location.href = "./admin.html";
                        } else if(res.code == -1){//用户不存在
                            $("#nameTip").text("用户不存在")
                            $("#name").val("")
                            $("#password").val("")
                            $("#passwordTip").text("");
                        }else if(res.code == -2){//
                            $("#nameTip").text("")
                            $("#name").val(name)
                            $("#password").val("")
                            $("#passwordTip").text("密码错误");
                        }
                    }
                }
            })
        }
    })
})