function getCookie(key) {
    var strCookie = document.cookie;
    var arrCookie = strCookie.split("; ");
    for (var i = 0; i < arrCookie.length; i++) {
        var arr = arrCookie[i].split("=");
        if (arr[0] == key)
            return arr[1];
    }
    return "";
}

function removeCookie() {
    document.cookie = "telephone=";
    document.cookie  = "userId=";
    document.cookie = "password=";
    window.location.reload(true);
}
function checkLoginStatus() {
    var islogin = getCookie("telephone");
    if(islogin!==''){
        $("#userNavMenu").show()
        $("#loginTip").hide()
        var userId = getCookie("userId")
        $.ajax({
            type:"get",
            url:"/user/nickName/"+userId,
            contentType: "application/json",
            cache: false,

            success: function(res){
                $("#userNav").text(res.data)
            }
        })
    }
    else{
        $("#userNavMenu").hide()
        $("#loginTip").show()
    }
}