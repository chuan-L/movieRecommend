$(document).ready(function () {

    //用户登录
    var islogin = getCookie("telephone");
    checkLoginStatus()
    //获得请求参数
    var s = window.location.search
    var topicId = s.substring(s.lastIndexOf("?")+1,s.length)
    var movieId;
    var userId=-1;
    //加载用户信息
    var islogin = getCookie("telephone");
    if(islogin!==''){
        $("#userNavMenu").show()
        $("#loginTip").hide()
        userId = getCookie("userId")
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
    //加载电影信息

    $.ajax({
        type:'get',
        url:"/movie/topic/"+topicId,
        success:function (res) {
            if(res.code == 0){
                $("#movie_name").text(res.data.name)
                movieId = res.data.movieId;
                $("#poster_img").attr("src","/movie/img/"+res.data.posterImgPath)
                $("#movie_name").attr("href","movie.html?"+movieId)
                $("#avg_star").attr("data-num",parseInt( res.data.avgRating).toFixed(1))
                $("#avg_rating").text(parseInt( res.data.avgRating).toFixed(1))
                var introduce = new Date(res.data.releaseDate).getFullYear()
                introduce += '/'+res.data.nation+"/"+res.data.type+"/"+res.data.director
                $("#brief_introduce").text(introduce)

                //设置评分
                $("#avg_star").raty({
                    score:function () {
                        return $(this).attr("data-num");
                    },
                    starOn:'img/star-on.png',
                    starOff:'img/star-off.png',
                    starHalf:'img/star-half.png',
                    readOnly:true,
                    halfShow:true,
                    space:true,
                    round: { down: .25, full: .6, up: .76 },
                })
            }
        }
    })

    //加载话题信息
    $.ajax({
        type:"get",
        url:"/topic/"+topicId,
        contentType: "application/json",
        success:function (res) {
            if(res.code == 0){
                $("#topic_content").text(res.data.content)
                $("#review_number").text(res.data.reviewNumber)
            }
        }
    })
    var reviewPage = 1;
    var reviewPageSize = 10;
    //加载影评信息
    $.ajax({
        type:"get",
        url:"/review/topic?page="+reviewPage+"&size="+reviewPageSize+"&topicId="+topicId,
        contentType:"application/json",
        success:function(res){
            if(res.code == 0){

                for(var i in res.data){


                    var txt = '<hr>\n' +
                        '                    <!-- header-->\n' +
                        '                    <div class="row"><div class="col">\n' +
                        '                        <span class="nick_text">'+res.data[i].userName+'</span>\n' +
                        '                        <span class="grey_text">'+getTimeDifference(res.data[i].createTime)+'</span>\n' +
                        '                    </div></div>\n' +
                        '                    <!-- body -->\n' +
                        '                    <div class="row">\n' +
                        '                        <div class="col">'+res.data[i].content+'</div>\n' +
                        '                    </div>\n' +
                        '                    <!-- foot -->\n' +
                        '                    <div class="row">\n' +
                        '                        <div class="grey_text col-2 pl-0" id="'+res.data[i].reviewId+'">\n' +
                        '                            <span class="like">'+res.data[i].likeNumber+'</span><a href="javascript:;" onclick="addLike(this)">赞</a>\n' +
                        '                            <span class="dislike">'+res.data[i].dislikeNumber+'</span><a href="javascript:;" onclick="addDislike(this)">踩</a>\n' +
                        '                            '+res.data[i].commentNumber+'回应'  +
                        '                        </div>\n' +
                        '                    </div>'
                    $("#review_list").append(txt)
                }
            }
        }
    })
    //写影评
    $("#write_review_btn").on("click",function () {
        window.location.href ='/editor.html?userId='+userId+'&topicId='+topicId
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
function getTimeDifference( t){
    return "一小时"
}

function addLike(obj) {
    var reviewId = $(obj).parent().attr("id")
    $.ajax({
        type:'post',
        url:"/review/addLike/"+reviewId,
        contentType:"application/json",
        success:function(res){
            $(obj).prev().text(parseInt($(obj).prev().text())+1)
        }
    })

}
function addDislike(obj) {
    var reviewId = $(obj).parent().attr("id")
    $.ajax({
        type:'post',
        url:"/review/addDislike/"+reviewId,
        contentType:"application/json",
        success:function(res){
            $(obj).prev().text(parseInt($(obj).prev().text())+1)
        }
    })

}