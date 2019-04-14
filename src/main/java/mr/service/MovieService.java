package mr.service;

import mr.entity.Movie;
import mr.entity.Rating;
import mr.exception.ParamErrException;
import mr.mapper.MovieMapper;
import mr.mapper.MovieTypeMapper;
import mr.mapper.RatingMapper;
import mr.mapper.TopicMapper;
import mr.vo.MovieBriefVo;
import mr.vo.MovieTopicVo;
import mr.vo.MovieVo;
import mr.vo.SearchVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LiChuan on 2019/3/24.
 */
@Service
public class MovieService {
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieTypeMapper movieTypeMapper;
    @Autowired
    private RatingMapper ratingMapper;

    @Autowired
    private TopicMapper topicMapper;



    /*
    搜索
     */
    public List<SearchVo> search(String key,int page,int size){
        Map<String,Object> data = new HashMap<>(3);
        data.put("currPage",page);
        data.put("pageSize",size);
        data.put("name",key);
        List<SearchVo> list =  movieMapper.searchByNameByPage(data);
        for(SearchVo item : list){
            String ts = "";
            for(String s:item.getTypeIds().split("/")){
                 ts =ts+ "/"+ movieTypeMapper.findNameById(Integer.parseInt(s.trim()));
            }
            item.setTypeName(ts.substring(1));
        }
        return list;
    }
    /*
    查找
     */
    public Movie findById(Integer id){
        return movieMapper.findById(id);
    }
    /*
    根据电影名和导演名查id
     */
    public Integer findIdByNameAndDirector(String movieName,String director){
        return movieMapper.findIdByNameAndDirector(movieName,director);
    }

    /*
    添加
     */
    public Integer add(Movie movie){
        return movieMapper.insert(movie);
    }
    /*
    更新图片
     */
    public Integer updateImgPath(Integer movieId,String path){
        return movieMapper.updateImg(movieId,path);
    }
    /*
    根据类型名查id
     */
    public String findTypeIdsByName(String name){
        String ids = "";
        for(String s:name.split("/")){
            if(ids.equals("")){
                ids+=movieTypeMapper.findIdByName(name);
            }
            else{
                ids+="/"+movieTypeMapper.findIdByName(name);
            }
        }
        return ids;

    }
    /*
    根据id查类型名
     */
    public String findTypeNameById(Integer id){

        return movieTypeMapper.findNameById(id);

    }
    /*
    最新列表
     */
    public List<MovieBriefVo> findLatestReleaseList(int currPage, int pageSize){
        Map<String,Object> data = new HashMap<>(2);
        data.put("currPage",currPage);
        data.put("pageSize",pageSize);
        return movieMapper.findReleaseDateRankingListByPage(data);
    }

    /*
    评分最高列表
     */
    public List<MovieBriefVo> findRatingRankingList(int currPage, int pageSize){
        Map<String,Object> data = new HashMap<>(2);
        data.put("currPage",currPage);
        data.put("pageSize",pageSize);
        return movieMapper.findRatingRankingListByPage(data);
    }
    public List<String> findNameByPage(int currPage, int pageSize) {
        Map<String, Object> data = new HashMap<>(2);
        data.put("currPage", currPage);
        data.put("pageSize", pageSize);
        return movieMapper.findNameByPage(data);
    }

    /*
    做出评分
     */
    public void addRating(Integer userId,Integer movieId,Integer rating) throws Exception{
        Integer before = ratingMapper.findByUserAndMovieId(userId,movieId);
        //没有评过分
        if( before== null){
            ratingMapper.insert(userId,movieId,rating);

        }//评过分
        else{

            switch (before){
                case 2:
                    movieMapper.removeStar1(movieId);
                    break;
                case 4:
                    movieMapper.removeStar2(movieId);
                    break;
                case 6:
                    movieMapper.removeStar3(movieId);
                    break;
                case 8:
                    movieMapper.removeStar4(movieId);
                    break;
                case 10:

                    movieMapper.removeStar5(movieId);
                    break;
                default:
                    throw new ParamErrException("参数不正确，应该为2,4,6,8,10,不是"+rating);
            }

            Rating rating1 = new Rating();
            rating1.setCreateTime(new Date());
            rating1.setMovieId(movieId);
            rating1.setRating(rating);
            rating1.setUserId(userId);

            ratingMapper.updateRating(rating1);
        }
        switch (rating){
            case 2:
                movieMapper.addStar1(movieId);
                break;
            case 4:
                movieMapper.addStar2(movieId);
                break;
            case 6:
                movieMapper.addStar3(movieId);
                break;
            case 8:
                movieMapper.addStar4(movieId);
                break;
            case 10:
                movieMapper.addStar5(movieId);
                break;
            default:
                throw new ParamErrException("参数不正确，应该为2,4,6,8,10,不是"+rating);
        }

        return;
    }

    /*
    查找评价
     */
    public Integer findRating(Integer userId,Integer movieId){
        return ratingMapper.findByUserAndMovieId(userId,movieId);
    }

    /*
    根据topic找电影
     */
    public MovieTopicVo findByTopicId(Integer topicId){

        Integer movieId = topicMapper.findMovieIdByTopicId(topicId);
        Movie movie = movieMapper.findById(movieId);
        MovieTopicVo vo = new MovieTopicVo();
        BeanUtils.copyProperties(movie,vo);
        //平均分
        float rating = (movie.getStar1()*2+movie.getStar2()*4+movie.getStar3()*6+movie.getStar4()*8+movie.getStar5()*10)/movie.getRatingPeople();
        vo.setAvgRating(rating);
        //电影类型
        String types ="";
        for(String id: movie.getTypeIds().split("/")){
            types+= "/"+movieTypeMapper.findNameById(Integer.parseInt(id.trim()));
        }
        types = types.substring(1);
        vo.setType(types);

        return vo;
    }
}
