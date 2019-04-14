package mr.controller;

import mr.entity.Review;
import mr.service.ReviewService;
import mr.utils.Result;
import mr.vo.ReviewForm;
import mr.vo.ReviewVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by LiChuan on 2019/4/10.
 */
@RestController
public class ReviewController {


    @Autowired
    private ReviewService reviewService;


    @GetMapping("/review/topic")
    public Result findListByTopicByPage(@RequestParam("page") Integer page,
                                        @RequestParam("size")Integer size,
                                        @RequestParam("topicId") Integer topicId){
        List<ReviewVo> list = reviewService.findListByTopicByPage(page,size,topicId);
        if(list == null){
            return Result.createErrorResult();
        }
        else{

            return Result.createOkResult(list);
        }

    }
    /*
    喜欢加一
     */
    @PostMapping("/review/addLike/{reviewId}")
    public Result addLike(@PathVariable ("reviewId") Integer reviewId){
         reviewService.addLike(reviewId);
        return Result.createOkResult();
    }

    /*
   不喜欢加一
    */
    @PostMapping("/review/addDislike/{reviewId}")
    public Result addDislike(@PathVariable ("reviewId") Integer reviewId){
        reviewService.addDislike(reviewId);
        return Result.createOkResult();
    }
    /*
    添加新评论
     */
    @PostMapping("/review/add")
    public Result addReview(@RequestBody ReviewForm reviewForm){


        reviewService.addReview(reviewForm);
        return Result.createOkResult();
    }

}
