package mr.controller;

import mr.entity.Movie;
import mr.exception.NotFoundException;
import mr.service.AdminService;
import mr.service.MovieService;
import mr.utils.FileUtiles;
import mr.utils.Result;
import mr.vo.AdminForm;
import mr.vo.MovieVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by LiChuan on 2019/3/23.
 */
@RestController
public class AdminCtrl {

    @Autowired
    MovieService movieService;
    @Autowired
    AdminService adminService;
    //管理员登录
    @PostMapping("/admin/login")
    public Result login(@RequestBody AdminForm adminForm){
        try {
            adminService.findPswdByName(adminForm);
        }catch (NotFoundException nfe){
            nfe.printStackTrace();
            return Result.createErrorResult("用户名不存在",-1);
        }catch (Exception e){
            e.printStackTrace();
            return Result.createErrorResult("密码错误",-2);
        }

        return Result.createOkResult();
    }

    @PostMapping("/admin/movie/add")
    public Result add(@RequestParam("file")MultipartFile file,
                      @RequestParam("fileName") String fileName,
                      @RequestBody MovieVo movieVo ,HttpServletRequest request){
        //Integer userId = (Integer)request.getAttribute("userId");
        //查找类型id
        String ids = movieService.findTypeIdsByName(movieVo.getTypeName());
        if(ids == null || ids.isEmpty()){
            return Result.createErrorResult("电影类型未找到",-1);
        }
        //添加
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieVo,movie);
        movie.setTypeIds(ids);

        movieService.add(movie);
        //获取刚才的id
        Integer movieId = movieService.findIdByNameAndDirector(movieVo.getName(),movieVo.getDirector());
        //存储图片
        String path;
        try{
            //将id作为图片名
             path= FileUtiles.storeFile(file,FileUtiles.getMovieImagePath(),movieId.toString()+fileName);
        }catch (Exception e){
            e.printStackTrace();
            return Result.createErrorResult("上传图片失败",-1);
        }
        //更新图片地址
        movieService.updateImgPath(movieId,path);

        return Result.createOkResult();
    }
    //movie修改
    @PostMapping("/admin/movie/update")
    public Result update(){

        return  null;

    }
}
