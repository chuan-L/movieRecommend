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
    var latestPageCurr =1;
    var pageSize = 8;
    var ratingPageCurr = 1;
    //加载高分电影
    loadMovieList("rating",1,8)
    // $.ajax({
    //     type:"get",
    //     url:"/movie/list?"+"key=rating&page="+ratingPage+"&size="+pageSize,
    //     contentType:"application/json",
    //     success:function (res) {
    //         if(res.code == 0){
    //             var index = 0
    //             for(var i in res.data){
    //                 var text = '<div class="col col-3 movie-item" id="rating_list_'+i+
    //                     '"> <a href="/movie.html?'+res.data[i].movieId+'"><img class="movie-img" src="/movie/img/'+res.data[i].posterImgPath+'"> <div class="movie-info "><p>'
    //                 +res.data[i].name+'<strong>'+res.data[i].avgRating.toFixed(1)+'</strong></p></div></a></div>'
    //
    //                 $("#rating_list_div").append(text)
    //             }
    //         }
    //     }
    // })

    //加载最新电影
    loadMovieList("latest",1,8)
    //电影分页
    $(".page-link").on("click",function(){
        var id = $(this).attr("id")
        switch (id){
            case "rating_pre":

                ratingPageCurr --;
                loadMovieList("rating",ratingPageCurr,pageSize)

                break;
            case "rating_next":

                ratingPageCurr++;
                loadMovieList("rating",ratingPageCurr,pageSize)
                break;
            case "latest_pre":

                latestPageCurr--;
                loadMovieList("latest",latestPageCurr,pageSize)
                break;
            case "latest_next":

                latestPageCurr ++;
                loadMovieList("latest",latestPageCurr,pageSize)
                break;
            default:
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

function loadMovieList(key,page,size) {


    $.ajax({
        type:"get",
        url:"/movie/list?"+"key="+key+"&page="+page+"&size="+size,
        contentType:"application/json",
        success:function (res) {
            if(res.code == 0){
                $("#"+key+"_list_div").text('')
                var index = 0
                for(var i in res.data){
                    var text = '<div class="col col-3 movie-item" id="'+key+'_list_'+i+
                        '"> <a href="/movie.html?'+res.data[i].movieId+'"><img class="movie-img" src="/movie/img/'+res.data[i].posterImgPath+'"> <div class="movie-info "><p>'
                        +res.data[i].name+'<strong>'+res.data[i].avgRating.toFixed(1)+'</strong></p></div></a></div>'

                    $("#"+key+"_list_div").append(text)

                }
                if(page == 1){
                    $("#"+key+"_pre").parent().attr("class","page-item disable")
                }else{
                    $("#"+key+"_pre").parent().attr("class","page-item")
                }
                if(res.data.length < size){
                    $("#"+key+"_next").parent().attr("class","page-item disable")
                }else{
                    $("#"+key+"_next").parent().attr("class","page-item")
                }
            }
            else if(res.code == 1){
                $("#"+key+"_next").parent().attr("class","page-item disable")
            }
        }
    })
}



