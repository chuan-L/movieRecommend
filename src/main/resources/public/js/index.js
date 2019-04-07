$(document).ready(function(){



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
    var ratingPage =1;
    var pageSize = 8;

    //加载高分电影
    $.ajax({
        type:"get",
        url:"/movie/list?"+"key=rating&page="+ratingPage+"&size="+pageSize,
        contentType:"application/json",
        success:function (res) {
            if(res.code == 0){
                var index = 0
                for(var i in res.data){
                    var text = '<div class="col col-3 movie-item" id="rating_list_'+i+
                        '"> <a href="/movie.html?'+res.data[i].movieId+'"><img class="movie-img" src="/movie/img/'+res.data[i].posterImgPath+'"> <div class="movie-info "><p>'
                    +res.data[i].name+'<strong>'+res.data[i].avgRating.toFixed(1)+'</strong></p></div></a></div>'

                    $("#rating_list_div").append(text)
                }
            }
        }
    })

    //加载最新电影
    $.ajax({
        type:"get",
        url:"/movie/list?"+"key=latest&page="+ratingPage+"&size="+pageSize,
        contentType:"application/json",
        success:function (res) {
            if(res.code == 0){
                var index = 0
                for(var i in res.data){
                    var text = '<div class="col col-3 movie-item" id="latest_list_'+i+
                        '"><a href="/movie.html?'+res.data[i].movieId+'"><img class="movie-img" src="/movie/img/'+res.data[i].posterImgPath+'"> <div class="movie-info "><p>'
                        +res.data[i].name+'<strong>'+res.data[i].avgRating.toFixed(1)+'</strong></p></div></a> </div>'

                    $("#latest_list_div").append(text)
                }
            }
        }
    })
    $("#logout").on("click",function () {
        $.ajax({
            type:"post",
            url:"/logout/"+userId,
            contentType: "application/json",

            success: function(res){
                if(res.code == 0){
                    $("#loginTip").show()
                    $("#userNavMenu").hide()
                    removeCookie();
                    //location.href = 'index.html';

                }
                else{
                    alert('fail to logout')
                }
            }
        })
    })
})
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

