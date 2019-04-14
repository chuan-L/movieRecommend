package mr.mapper;

import mr.entity.Review;
import mr.vo.ReviewForm;
import mr.vo.ReviewVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by LiChuan on 2019/4/10.
 */
@Component
public interface ReviewMapper {

    //根据topicid查找
    @Select("Select * from review where topic_id = #{topicId} ")
    List<Review> findListByTopicIdByPage(Map<String,Object> data);

    //根据userId查找
    @Select("Select * from review where user_id = #{userId} ")
    List<Review> findListByUserIdByPage(Map<String,Object> data);

    //喜欢加一
    @Update("update review set like_number = like_number+1 where review_id = #{review_id} ")
    Integer addLike(Integer reviewId);

    //不喜欢加一
    @Update("update review set dislike_number = dislike_number+1 where review_id = #{review_id} ")
    Integer addDislike(Integer reviewId);

    //留言加一
    @Update("update review set comment_number = comment_number+1 where review_id = #{review_id} ")
    Integer addComment(Integer reviewId);

    //添加
    @Insert("insert into review (user_id,topic_id,content) values(#{userId},#{topicId},#{content}) ")
    Integer addReview(ReviewForm form);
}
