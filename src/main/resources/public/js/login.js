$(document).ready(function(){
    $("#submit").on("click", function(){
        var tele = $("#telephone").val();
        var pswd = $("#password").val();

        var correctFormed = true;
        if(tele.length != 11){
            $("#telephoneTip").text("手机号长度不正确！");
            correctFormed = false;
        }
        else{
            $("#telephoneTip").text("");
        }
        if(pswd.length<6 || pswd.length > 16){

            $("#passwordTip").text("密码长度应在6位至16位之间");
            correctFormed = false;
        }
        else{
            $("#passwordTip").text("");
        }
        if(correctFormed == true){

            console.log(tele+"  "+pswd);
            $.ajax({
                type:"post",
                url:"./login",
                data: JSON.stringify({ 'telephone': $("#telephone").val(), 'password': $("#password").val() }),
                contentType: "application/json",
                cache: false,

                success: function(res){
                    console.log(res);
                    if(res){
                        if(res.code == 0){
                            sessionStorage.userId = res.data;
                            console.log("success");
                            //记住密码 未完成
                            // if ($(this).prop("checked")) {
                            //     alert("选中");
                            // } else {
                            //     alert("没有选中");
                            // }

                            window.location.href = "./index.html";
                            //保存cookie
                            var exp = new Date()
                            exp.setTime(exp.getTime()+60*1000*60*24)//24小时
                            document.cookie = "userId="+res.data+";expires="+exp.toUTCString();
                            document.cookie = "telephone="+$("#telephone").val()+";expires="+exp.toUTCString();
                            document.cookie = "password="+$("#password").val()+";expires="+exp.toUTCString();

                        } else if(res.code == -1){//手机号不存在
                            $("#telephoneTip").text("手机号没有注册")
                            $("#telephone").val("")
                            $("#password").val("")
                            $("#passwordTip").text("");
                        }else if(res.code == -2){//密码不正确
                            $("#passwordTip").text("密码不正确");
                            $("#password").val("");
                        }
                    }
                    else{
                        alert("fail")
                    }
                }

            });

        }

    })
})
