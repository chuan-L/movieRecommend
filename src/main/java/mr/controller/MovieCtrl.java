package mr.controller;

import mr.entity.Movie;
import mr.entity.User;
import mr.service.MovieService;
import mr.utils.FileUtiles;
import mr.utils.Result;
import mr.vo.MovieBriefVo;
import mr.vo.MovieVo;
import mr.vo.SearchVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by LiChuan on 2019/3/24.
 */
@RestController
public class MovieCtrl {
    @Autowired
    private MovieService movieService;

    /*
    获取电影列表
     */
    @GetMapping("/movie/list")
    public Result findListByPage(@RequestParam("page") Integer page,
                                        @RequestParam("size")Integer size,
                                        @RequestParam("key") String key){


        Result r = Result.createOkResult();
        List<MovieBriefVo> list = null;
        switch (key) {
            case "latest":
                 list = movieService.findLatestReleaseList(page, size);
                break;
            case "rating":
                list = movieService.findRatingRankingList(page, size);
                break;
            default:
                break;
        }
        if(list == null){
            r.setCode(-1);
        }
        else if(list.size() < size){ //分页时，下一页不能点击
            r.setCode(1);
            r.setData(list);
        }
        else{
            r.setCode(0);
            r.setData(list);
        }

        return r;
    }
    /*
    获取详细信息
     */
    @GetMapping("/movie/{id}")
    public Result findById(@PathVariable("id") Integer id){
        Movie movie = movieService.findById(id);

        if(movie == null){
            return Result.createErrorResult("查无此项",-1);
        }

        MovieVo movieVo = new MovieVo();
        BeanUtils.copyProperties(movie,movieVo);
        //获得类型
        String types = "";
        for(String type:movie.getTypeIds().split("/")){
            if(types.equals("")){

                types= types+ movieService.findTypeNameById(Integer.parseInt(type.trim()));
            }else{

                types=types+"/"+ movieService.findTypeNameById(Integer.parseInt(type.trim()));
            }


        }
        movieVo.setTypeName(types);
        float avg =(float)(movie.getStar1()*2+movie.getStar2()*4+ movie.getStar3()*6
                 + movie.getStar4()*8+movie.getStar5()*10)/movie.getRatingPeople();
        movieVo.setRatingAvg(avg);
        return Result.createOkResult(movieVo);
    }
    /*
    获取图片
     */
    @GetMapping("/movie/img/{path}")
    public void getMovieImg(@PathVariable("path") String path, HttpServletResponse response){
        try{
            //读取
            byte[] data = new byte[1024];

            String imagePath = FileUtiles.getMovieImagePath();

            path = imagePath+path;

            //path="G:\\IDEA_Work\\imgsrc\\movie\\1.png";
            File imgFile = new File(path);

            FileInputStream in = new FileInputStream(imgFile);

            //写入
            response.setContentType("image/webp");
            OutputStream os = response.getOutputStream();

            int n = 0;
            while((n = in.read(data)) != -1){
                os.write(data,0,n);
            }

            os.flush();
            os.close();
            in.close();
            return;
        }catch (FileNotFoundException nfe){
            nfe.printStackTrace();

        }
        catch (IOException ioe){
            ioe.printStackTrace();

        }catch (Exception e){
            e.printStackTrace();

        }

    }

    /*
    做出评分
     */
    @PostMapping("/movie/rating/{userId}/{movieId}")
    public Result rating(@PathVariable("userId") Integer userId,
                         @PathVariable("movieId") Integer movieId,
                         @RequestParam("rating") Integer rating) {

        try {
            movieService.addRating(userId,movieId,rating);
            return Result.createOkResult();
        }catch (Exception e){
            e.printStackTrace();
            return Result.createErrorResult();
        }

    }

    /*
    查找评价
     */
    @GetMapping("/movie/rating/{userId}/{movieId}")
    public Result findRating(@PathVariable("userId") Integer userId,
                             @PathVariable("movieId") Integer movieId){
        Integer rating = movieService.findRating(userId,movieId);
        if(rating == null){
            return Result.createErrorResult();
        }
        else{
            return Result.createOkResult(rating);
        }
    }
     /*
    根据userId 推荐
     */
    @GetMapping("/movie/recommend/usercf/{userId}")
    public Result UserCFRecommend(@RequestParam("page") Integer page,
                                @RequestParam("size")Integer size,
                                @PathVariable("userId") Integer userId){
        //。。。
        List<MovieBriefVo> list =  movieService.findRatingRankingList(1,10);
        return Result.createOkResult(list);
    }
    /*
    根据相似电影推荐
     */
    @GetMapping("/movie/recommend/itemcf/{movieId}")
    public Result ItemCFRecommend(@RequestParam("page") Integer page,
                                  @RequestParam("size")Integer size,
                                  @PathVariable("movieId") Integer movieId){
        //。。。
        List<MovieBriefVo> list =  movieService.findRatingRankingList(1,10);
        return Result.createOkResult(list);
    }
    /*
    根据话题找电影
     */
    @GetMapping("/movie/topic/{topicId}")
    public Result findByTopic(@PathVariable("topicId") Integer topicId){

        return Result.createOkResult(movieService.findByTopicId(topicId));

    }
    /*
    搜索
     */
    @GetMapping("/movie/search")
    public Result search(@RequestParam ("key") String key,
                         @RequestParam("page") Integer page,
                         @RequestParam("size") Integer size){

        //
        List<SearchVo> list = movieService.search(key,page,size);
        return Result.createOkResult(list);
    }

}
