$(document).ready(function () {

    var s = window.location.search
    var movieId = s.substring(s.lastIndexOf("?")+1,s.length)

    var userId = 1;
    var imgurl ;

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

    //查询电影信息
    $.ajax({
        type: "get",
        url: "/movie/" + movieId,
        contentType: "application/json",
        cache: false,
        success: function (res) {
            console.log(res.data)
            if (res.code == 0) {
                $("#movieName").text(res.data.name)

                $("#director").text(res.data.director)
                $("#screenwriter").text(res.data.screenwriter)
                $("#actor").text(res.data.actor)
                $("#type").text(res.data.typeName)
                $("#nation").text(res.data.nation)
                $("#language").text(res.data.language)
                $("#releaseDate").text(new Date(res.data.releaseDate).getFullYear())
                $("#length").text(res.data.length)
                $("#alias").text(res.data.alias)

                $("title").text(res.data.name)
                $("#introduce").children("h4").text(res.data.name+"的剧情简介")
                $("#introduce").children("p").text(res.data.introduce)

                $("#avg_rating").text(res.data.ratingAvg.toFixed(1))
                $("#star_avg").attr("data-num",res.data.ratingAvg/2)
                $("#rating_people").text(res.data.ratingPeople)
                //设置评分
                $("#star_avg").raty({
                    score:function () {
                        return $(this).attr("data-num");
                    },
                    starOn:'img/star-on.png',
                    starOff:'img/star-off.png',
                    starHalf:'img/star-half.png',
                    readOnly:true,
                    halfShow:true,
                    space:false,
                    readOnly:true,
                    round: { down: .25, full: .6, up: .76 },
                })

                imgurl = res.data.posterImgPath
                $("#movieImg img").attr("src","/movie/img/"+imgurl)
                //加载海报图片
                // var xhr = new XMLHttpRequest()
                // xhr.open("get","/movie/img/"+imgurl,true)
                // xhr.responseType = "blob"
                // xhr.onload = function () {
                //     if(this.status == 200){
                //
                //         var blob = this.response;
                //         var img = document.createElement("img");
                //         img.onload = function(e) {
                //             window.URL.revokeObjectURL(img.src);
                //         };
                //         img.src = window.URL.createObjectURL(blob);
                //
                //         $("#movieImg").html(img);
                //
                //     }
                // }
                // xhr.send()

                //========================评分百分比
                {
                    var cavcolor = "#ffd596"
                    var percent = (100 * res.data.star5 / res.data.ratingPeople).toFixed(1)
                    var c1 = document.getElementById("canvas5");
                    c1.setAttribute("width", percent)
                    var ctx = c1.getContext("2d");
                    ctx.fillStyle = cavcolor;
                    ctx.fillRect(0, 0, percent, 14);
                    $("#star_percent5").text(percent + "%")

                    percent = (100 * res.data.star4 / res.data.ratingPeople).toFixed(1)
                    c1 = document.getElementById("canvas4");
                    c1.setAttribute("width", percent)
                    ctx = c1.getContext("2d");
                    ctx.fillStyle = cavcolor;
                    ctx.fillRect(0, 0, percent, 14);
                    $("#star_percent4").text(percent + "%")


                    percent = (100 * res.data.star3 / res.data.ratingPeople).toFixed(1)
                    c1 = document.getElementById("canvas3");
                    c1.setAttribute("width", percent)
                    ctx = c1.getContext("2d");
                    ctx.fillStyle = cavcolor;
                    ctx.fillRect(0, 0, percent, 14);
                    $("#star_percent3").text(percent + "%")

                    percent = (100 * res.data.star2 / res.data.ratingPeople).toFixed(1)
                    c1 = document.getElementById("canvas2");
                    c1.setAttribute("width", percent)
                    ctx = c1.getContext("2d");
                    ctx.fillStyle = cavcolor;
                    ctx.fillRect(0, 0, percent, 14);
                    $("#star_percent2").text(percent + "%")

                    percent = (100 * res.data.star1 / res.data.ratingPeople).toFixed(1)
                    c1 = document.getElementById("canvas1");
                    c1.setAttribute("width", percent)
                    ctx = c1.getContext("2d");
                    ctx.fillStyle = cavcolor;
                    ctx.fillRect(0, 0, percent, 14);
                    $("#star_percent1").text(percent + "%")
                }


            }
            else {
                console.log("error")
            }
        }

    })


    if(islogin != ''){
        //若已作出评价，加载评价

        $.ajax({
            type: "get",
            url: "/movie/rating/"+userId+"/" + movieId,
            contentType: "application/json",
            cache: false,
            success: function (res) {
                if(res.code == 0){
                    console.log(res.data/2)
                    $("#myrating").attr("data-num",res.data/2)
                }
                //做出评分
                $("#myrating").raty({
                    score:function(){
                        return $(this).attr("data-num");
                    },
                    starOn:'img/star-on.png',
                    starOff:'img/star-off.png',
                    starHalf:'img/star-half.png',
                    readOnly:false,
                    halfShow:true,
                    space:false,
                    round: { down: .25, full: .6, up: .76 },
                    hints:['很差','较差','还行','推荐','力荐'],
                    click: function(score, evt) {
                        if(islogin == ''){
                            window.location.href = "/login.html"
                        }
                        else{
                            //提交评分
                            $.ajax({
                                type: "post",
                                url: "/movie/rating/" +userId+"/"+ movieId+"?rating="+score*2,
                                contentType: "application/json",
                                cache: false,
                                success: function (res) {

                                }
                            })
                        }

                    }
                })
            }
        })
    }
    //加载评论
    var commentTag = "latest"
    var commentPage = 1;
    var commentPageSize = 5;
    $.ajax({
        type:"get",
        url:"/comment/"+movieId+"?key="+commentTag+"&page="+commentPage+"&size="+commentPageSize,
        contentType: "application/json",

        success: function(res){
            if(res.code == 0){
                for (var index in res.data){
                    var text = '<div class="comment">\n' +
                        '                    <hr>\n' +
                        '                    <div class="row">\n' +
                        '                        <span>'+res.data[index].nickName+'</span>看过\n' +
                        '                        <div id="userid" data-num="0"></div>\n' +
                        '                        <span>'+formatDate(res.data[index].createTime)+'</span>\n' +
                        '                        <span class="likeNumber">'+res.data[index].likeNumber+'</span><a  href="javascript:;" onclick="addCommentLike(this)">' +
                        '赞同<span class="userId" hidden>'+res.data[index].userId+'</span>' +
                        '<span class="movieId" hidden>'+res.data[index].movieId+'</span> </a>\n' +
                        '                    </div>\n' +
                        '                    <p>' +res.data[index].content+' </p>' +
                        '         </div>'
                    $("#commentList").append(text)
                }
            }

        }
    })
})
function addCommentLike(obj) {

    var userId = $(obj).find(".userId").text()
    var movieId = $(obj).find(".movieId").text();
   $.ajax({
       type:"post",
       url:"/comment/likeNumber?movieId="+movieId+"&userId="+userId,
       contentType: "application/json",
       success: function(res){
           if(res.code == 0){
               //likenumber+1
               $(obj).prev().text(parseInt($(obj).prev().text())+1)
           }
       }
   })
}
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

function  formatDate(time) {
    var s = new Date(time).toLocaleString()
    return s.replace("/","-").substring(0,s.length-5)

}

