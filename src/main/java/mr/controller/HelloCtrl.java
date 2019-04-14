package mr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import mr.utils.Result;

import java.util.ArrayList;

/**
 * Created by LiChuan on 2019/1/21.
 */
@Controller
public class HelloCtrl {

    /*
    剩余功能：
    搜索电影
    分类展示
    导航栏
    个人主页-个人信息修改
    个人主页-显示历史行为评分
    管理员页面-修改电影 添加信息

    个性化推荐

     */
    @GetMapping("/hello")
    public Result getHello(){
        ArrayList<Integer> arrayList = new ArrayList<>();

        return Result.createOkResult();
    }
    @GetMapping("/hell")
    public String hello(){
        return "index";
    }
}
