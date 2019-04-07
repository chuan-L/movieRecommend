package mr.mapper;

import mr.entity.Movie;
import mr.vo.MovieBriefVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by LiChuan on 2019/3/24.
 */
@Component
public interface MovieMapper {
    //查找
    @Select("select * from movie where movie_id = #{movieId} ")
    Movie findById(Integer movieId);

    //根据电影名查找
    @Select("select * from movie where name like '%#{name}% ")
    List<Movie> findListByName(String name);
    //根据导演名查找
    @Select("select * from movie where director like '%#{director}% ")
    List<Movie> findListByDirector(String director);
    //根据电影名和导演名查id
    @Select("select movie_id from movie where director = #{director} and name = #{name}")
    Integer findIdByNameAndDirector(@Param("name") String movieName,@Param("director") String director);

    //添加 不包括图片地址
    @Insert("insert into movie (name, alias, nation, language, " +
            "length,director, actor, screenwriter, release_date," +
            " introduce, type_ids,IMDb,rating_people," +
            " star1,star2,star3,star4,star5 ) " +
            "values(#{name}, #{alias}, #{nation}, #{language}, " +
            "#{length}, #{director}, #{actor}, #{screenwriter}, #{releaseDate}," +
            " #{introduce}, #{typeIds},#{IMDb},#{ratingPeople}," +
            " #{star1}, #{star2}, #{star3}, #{star4}, #{star5})")
     Integer insert(Movie movie);

    //更新图片地址
    @Update("update movie set poster_img_path = #{posterImgPath} where movie_id=#{movieId}")
    Integer updateImg(Integer movieId,String path);

    //获取评分排名列表
    @Select("select movie_id,name,poster_img_path,avg_rating from avg_rating_view ")
    List<MovieBriefVo> findRatingRankingListByPage(Map<String,Object> data);

    //获取日期排名列表
    @Select("select * from latest_release_view ")
    List<MovieBriefVo> findReleaseDateRankingListByPage(Map<String,Object> data);

    @Select("select name from movie ")
    List<String> findNameByPage(Map<String,Object> data);

    /*
    评分
     */
    @Update("update movie set rating_people = rating_people+1 ,star1 = star1+1 where movie_id = #{movieId}")
    Integer addStar1(Integer movieId);
    @Update("update movie set rating_people = rating_people+1 ,star2 = star2+1 where movie_id = #{movieId}")
    Integer addStar2(Integer movieId);
    @Update("update movie set rating_people = rating_people+1 ,star3 = star3+1 where movie_id = #{movieId}")
    Integer addStar3(Integer movieId);
    @Update("update movie set rating_people = rating_people+1 ,star4 = star4+1 where movie_id = #{movieId}")
    Integer addStar4(Integer movieId);
    @Update("update movie set rating_people = rating_people+1 ,star5 = star5+1 where movie_id = #{movieId}")
    Integer addStar5(Integer movieId);

    @Update("update movie set rating_people = rating_people-1 ,star1 = star1-1 where movie_id = #{movieId}")
    Integer removeStar1(Integer movieId);
    @Update("update movie set rating_people = rating_people-1 ,star2 = star2-1 where movie_id = #{movieId}")
    Integer removeStar2(Integer movieId);
    @Update("update movie set rating_people = rating_people-1 ,star3 = star3-1 where movie_id = #{movieId}")
    Integer removeStar3(Integer movieId);
    @Update("update movie set rating_people = rating_people-1 ,star4 = star4-1 where movie_id = #{movieId}")
    Integer removeStar4(Integer movieId);
    @Update("update movie set rating_people = rating_people-1 ,star5 = star5-1 where movie_id = #{movieId}")
    Integer removeStar5(Integer movieId);


}
