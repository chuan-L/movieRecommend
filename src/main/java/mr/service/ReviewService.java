package mr.service;

import mr.entity.Review;
import mr.mapper.ReviewMapper;
import mr.mapper.UserMapper;
import mr.vo.ReviewForm;
import mr.vo.ReviewVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LiChuan on 2019/4/10.
 */
@Service
public class ReviewService {
    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private UserMapper userMapper;
    /*
    根据topic查列表
     */
    public List<ReviewVo> findListByTopicByPage(int currPage, int pageSize, int topicId) {
        Map<String, Object> data = new HashMap<>(3);
        data.put("currPage", currPage);
        data.put("pageSize", pageSize);
        data.put("topicId", topicId);
        List<ReviewVo> reviewVos = new ArrayList<>();
        List<Review> list = reviewMapper.findListByTopicIdByPage(data);
        for(Review r : list){
            ReviewVo vo = new ReviewVo();
            BeanUtils.copyProperties(r,vo);
            vo.setUserName(userMapper.findNickNameById(r.getUserId()));
            reviewVos.add(vo);
        }
        return reviewVos;
    }

    /*
    点赞加一
     */
    public Integer addLike(int reviewId){
        return reviewMapper.addLike(reviewId);
    }
    /*
    不喜欢加一
     */
    public Integer addDislike(int reviewId){
        return reviewMapper.addDislike(reviewId);
    }

    /*
    留言加一
     */
    public Integer addComment(int reviewId){
        return reviewMapper.addComment(reviewId);
    }
    /*
    添加
     */
    public Integer addReview(ReviewForm reviewForm){
        return reviewMapper.addReview(reviewForm);
    }
}
