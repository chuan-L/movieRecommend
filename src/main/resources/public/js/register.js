$(document).ready(function () {
    $("#registerBtn").on("click",function () {
        var tele = $("#telephone").val();
        var nickName = $("#nickName").val();
        var pswd1 = $("#password1").val();
        var pswd2 = $("#password2").val();
        var correctFormed = true;
        //判断格式
        if(tele.length != 11){
            $("#teleTip").text ("手机号长度不正确");
            $("#telephone").value("");
            correctFormed = false;
        }else{
            $("#teleTip").text("");
        }
        if(nickName.length < 2 || nickName.length > 16){
            $("#nickNameTip").text( "昵称长度应在2到16之间");
            correctFormed = false;
        }else{
            $("#nickNameTip").text("");
        }
        if(pswd1.length <6 || pswd1.length > 16){
            $("#password1Tip").text("密码应为长度在6到16之间的数字和字母组合");
            correctFormed = false;
        }else{
            $("#password1Tip").text(" ");
        }
        if(pswd1 != pswd2){

            $("#password2Tip").text("两次输入的密码不一致");
            correctFormed = false;

        }
        else{
            $("#password2Tip").text("");
        }

        if(correctFormed == true){
            $.ajax({
                type:"post",
                url:"http://localhost:8080/register",
                data:JSON.stringify({'telephone':tele,
                'nickName':nickName,'password':pswd1}),

                contentType:"application/json",
                dataType:"json",
                cache:false,
                success:function (res) {
                    if(res){
                        if(res.code == 0){
                            sessionStorage.userId = res.data;
                            //注册成功转到首页
                            window.location.href="./login.html";
                        }
                        else{
                            //注册失败
                            alert(res.message);
                            $("#telephone").val("");
                            if(res.code == -1){
                                $("#teleTip").val(res.message);
                            }
                        }
                    }
                }
            })
        }


    })
})