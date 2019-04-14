package mr.controller;

import mr.entity.Topic;
import mr.service.TopicService;
import mr.utils.Result;
import mr.vo.TopicForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.FaultAction;
import java.util.List;

/**
 * Created by LiChuan on 2019/4/8.
 */
@RestController
public class TopicCtrl {

    @Autowired
    private TopicService topicService;

    @GetMapping("/topic/movie/{movieId}")
    public Result findAllByMovieId(@PathVariable ("movieId") Integer movieId){

        List<Topic> list = topicService.findAllByMovieId(movieId);
        return Result.createOkResult(list);
    }
    @GetMapping("/topic/user/{userId}")
    public Result findAllByUserId(@PathVariable ("userId") Integer userId){

        List<Topic> list = topicService.findAllByUserId(userId);
        return Result.createOkResult(list);
    }
    @GetMapping("/topic/{topicId}")
    public Result findByTopicId(@PathVariable("topicId") Integer topicId){

        return Result.createOkResult(topicService.findByTopicId(topicId));
    }

    /*
    添加新话题
     */
    @PostMapping("/topic/add")
    public Result addReview(@RequestBody TopicForm form){

        topicService.addTopic(form);
        return Result.createOkResult();
    }
}
