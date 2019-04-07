$(document).ready(function () {
    $("#submitBtn").on("click",function () {

      var formData = new FormData();

       var fileName =  $("#file").val();
       var fname = fileName.substring(fileName.lastIndexOf("."))//后缀
        formData.append("fileName",fname)
       formData.append("posterImgName",$("#file").get(0).files[0]);
      $.ajax({
          url:"/admin/movie/add",
          type:"post",
          processData:false,
          contentType:false,
          data:formData,
          success:function (res) {
              console.log(res.code);
          }
      })


    })

})
