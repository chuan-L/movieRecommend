package mr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import mr.utils.Result;

import java.util.ArrayList;

/**
 * Created by LiChuan on 2019/1/21.
 */
@RestController
public class HelloCtrl {

    @GetMapping("/hello")
    public Result getHello(){
        ArrayList<Integer> arrayList = new ArrayList<>();

        return Result.createOkResult();
    }
}
