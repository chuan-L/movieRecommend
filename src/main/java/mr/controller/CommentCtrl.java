package mr.controller;

import mr.service.CommentService;
import mr.utils.Result;
import mr.vo.CommentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by LiChuan on 2019/4/5.
 */
@RestController
public class CommentCtrl {

/*
有评论一定有评分
有评分不一定有评论
写评论的时候，可以评分和修改，而且强行要求评分，
 */
    @Autowired
    private CommentService commentService;

    /*
        评论列表
        url:/comment/12?key=latest&page=1&size=5
     */
    @GetMapping("/comment/{movieId}")
    public Result findLatestByPage(@PathVariable("movieId")Integer movieId,
                                   @RequestParam("key") String key,
                                   @RequestParam("page") Integer page,
                                   @RequestParam("size") Integer size){

        switch (key){
            case "latest":
                return Result.createOkResult(commentService.findLatestByPage(page,size,movieId));

            case "hotest":
                return Result.createOkResult(commentService.findHotestByPage(page,size,movieId));

            default:
                return Result.createErrorResult("key不正确",-1);
        }

    }

    /*
       赞同加一
     */
    @PostMapping("/comment/likeNumber")
    public Result findLatestByPage(@RequestParam("movieId")Integer movieId,
                                   @RequestParam("userId")Integer userId){
        return Result.createOkResult(commentService.addCommentLike(movieId,userId));
    }

    /*
    添加评论
     */
    @PostMapping("/comment/add")
    public Result addComment(@RequestBody CommentForm commentForm){

        Integer r =  commentService.addComment(commentForm);
        if(r == 1){
            return Result.createOkResult();
        }else{
            return Result.createErrorResult();
        }

    }

}
