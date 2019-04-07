package mr.mapper;

import mr.entity.Rating;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * Created by LiChuan on 2019/4/4.
 */
@Component
public interface RatingMapper {

    @Insert("insert into rating (user_id,movie_id,rating) " +
            "values (#{userId},#{movieId},#{rating})")
    Integer insert(@Param("userId")Integer userId,@Param("movieId")Integer movieId,@Param("rating") Integer rating);

    @Select("select rating from rating where user_id = #{userId} and movie_id = #{movieId}")
    Integer findByUserAndMovieId(@Param("userId")Integer userId,@Param("movieId") Integer movieId);

    @Update("update rating set rating = #{rating} , create_time = #{createTime} " +
            "where user_id = #{userId} and movie_id = #{movieId} ")
    Integer updateRating(Rating rating);
}
