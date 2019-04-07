package mr.service;

import mr.entity.Comment;
import mr.mapper.CommentMapper;
import mr.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LiChuan on 2019/4/5.
 */
@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public List<CommentVo> findLatestByPage(int currPage, int pageSize,int movieId){
        Map<String,Object> data = new HashMap<>(3);
        data.put("currPage",currPage);
        data.put("pageSize",pageSize);
        data.put("movieId",movieId);
        return commentMapper.findLatestByPage(data);
    }
    public List<CommentVo> findHotestByPage(int currPage, int pageSize,int movieId){
        Map<String,Object> data = new HashMap<>(3);
        data.put("currPage",currPage);
        data.put("pageSize",pageSize);
        data.put("movieId",movieId);
        return commentMapper.findHotestByPage(data);
    }

    /*
    赞同加一
     */
    public Integer addCommentLike(Integer movieId,Integer userId){
        return commentMapper.addLike(movieId,userId);
    }
}
