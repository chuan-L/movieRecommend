package mr.service;

import mr.entity.Topic;
import mr.mapper.TopicMapper;
import mr.vo.TopicForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.util.resources.cldr.pa.TimeZoneNames_pa;

import java.util.List;

/**
 * Created by LiChuan on 2019/4/8.
 */
@Service
public class TopicService {

    @Autowired
    private TopicMapper topicMapper;

    /*
    /根据movieId 获得所有topic
     */
    public List<Topic> findAllByMovieId(Integer movieId){
        return topicMapper.findAllByMovieId(movieId);
    }

    /*
    /根据userId 获得所有topic
     */
    public List<Topic> findAllByUserId(Integer movieId){
        return topicMapper.findAllByUserId(movieId);
    }

    /*
    根据id查找
     */
    public Topic findByTopicId(Integer topicid) {
        return topicMapper.findByTopicId(topicid);
    }

    /*
    添加
     */
    public Integer addTopic(TopicForm topicForm){
        return topicMapper.addReview(topicForm);
    }
}
