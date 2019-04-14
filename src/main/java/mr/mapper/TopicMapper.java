package mr.mapper;

import mr.entity.Topic;
import mr.vo.TopicForm;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by LiChuan on 2019/4/8.
 */
@Component
public interface TopicMapper {
    //根据movieId 获得所有topic
    @Select("select * from topic where movie_id = #{movieId} order by create_time")
    List<Topic> findAllByMovieId(Integer movieId);

    //根据userId 获得所有topic
    @Select("select * from topic where user_id = #{userId} order by create_time")
    List<Topic> findAllByUserId(Integer userId);

    //根据topic找movieId
    @Select("select movie_id from topic where topic_id = #{topicId} ")
    Integer findMovieIdByTopicId(Integer topicId);

    //根据topicId找topic
    @Select("select * from topic where topic_id = #{topicId} ")
    Topic findByTopicId(Integer topicId);

    //添加
    @Insert("insert into topic (user_id,movie_id,content) values(#{userId},#{movieId},#{content}) ")
    Integer addReview(TopicForm form);
}
