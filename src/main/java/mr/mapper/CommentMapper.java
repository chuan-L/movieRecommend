package mr.mapper;

import mr.vo.CommentVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by LiChuan on 2019/4/5.
 */
@Component
public interface CommentMapper {

    //根据movieid查，最新时间排序
    @Select("select comment.user_id,comment.movie_id,content,comment.create_time,like_number,nick_name,rating " +
            "from comment,user,rating " +
            "where comment.user_id = user.user_id " +
            "and comment.user_id = rating.user_id and comment.movie_id = rating.movie_id " +
            "and comment.movie_id = #{movieId} " +
            "order by comment.create_time desc ")
    List<CommentVo> findLatestByPage(Map<String,Object> data);

    //根据movieid查，赞同数量排序
    @Select("select comment.user_id,comment.movie_id,content,comment.create_time,like_number,nick_name,rating " +
            "from comment,user,rating " +
            "where comment.user_id = user.user_id " +
            "and comment.user_id = rating.user_id and comment.movie_id = rating.movie_id " +
            "and comment.movie_id = #{movieId} " +
            "order by comment.like_number desc ")
    List<CommentVo> findHotestByPage(Map<String,Object> data);

    //赞同加一
    @Update("update comment set like_number = like_number+1 " +
            "where movie_id = #{movieId} and user_id = #{userId} ")
    Integer addLike(@Param("movieId") Integer movieId,@Param("userId") Integer userId);
}
