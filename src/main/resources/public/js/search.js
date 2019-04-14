$(document).ready(function () {
    //用户登录
    var islogin = getCookie("telephone");
    checkLoginStatus()
    //...
    var page = 1;
    var pagesize = 10;
    //是否是之前页面跳转过来
    var key = window.localStorage.getItem("searchKey")
    if(key != null && key != ''){
        search(key,page,pagesize)
        window.localStorage.removeItem("searchKey");
    }
    //搜索
    $("#search_btn").on("click",function () {
        key = $("#search").val();
        if(isRightKey(key)){
            search(key,page,pagesize)
        }

    })

    $("#page_pre").on("click",function () {
        page--
        search(key,page,size)
    })
    $("#page_next").on("click",function () {
        page++
        search(key,page,size)
    })
})
function isRightKey(key) {
    if(key != '')
        return true;
}
function search(key,page,size) {


    $("#search").val(key)
    $("#search_word").text(key)

    $.ajax({
        type:'get',
        url:'/movie/search?key='+key+'&page='+page+'&size='+size,
        contentType:"application/json",
        success:function(res){
            if(res.code == 0){

                for(var i in res.data){
                    var item = res.data[i]
                    var txt = '<div class="row mt-4 mb-4">\n' +
                        '                        <img class="col-2" src="/movie/img/'+item.posterImgPath+'">\n' +
                        '                        <div class="col-10 col">\n' +
                        '                            <span>'+item.name+'('+new Date(item.releaseDate).getFullYear()+')'+'</span>\n' +
                        '                            <div>评分</div>\n' +
                        '                            <span>'+item.nation+'/'+item.director+'导演/ 主演/类型/编剧</span>\n' +
                        '                        </div>\n' +
                        '                    </div>'

                    $("#search_result_list").append(txt)
                }
                if(res.data.length < size ){
                    $("#page_next").attr("class","page-item disabled")
                }
                else{
                    $("#page_next").attr("class","page-item")
                }
                if(page > 1){
                    $("#page_pre").attr("class","page-item")
                }else{
                    $("#page_pre").attr("class","page-item disabled")
                }

            }
        }
    })
}