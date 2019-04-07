package mr.controller;

import mr.entity.User;
import mr.exception.NotFoundException;
import mr.service.UserService;
import mr.utils.Result;
import mr.vo.LoginForm;
import mr.vo.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by LiChuan on 2019/3/21.
 */
@RestController
public class UserCtrl {

    @Autowired
    private UserService userService;
    @PostMapping("/isLogin")
    public Result isLogin(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies.length > 0){
            if(cookies[0].getMaxAge()   > 0){

                return Result.createOkResult();
            }
            else{
                return Result.createErrorResult();
            }
        }
        return Result.createErrorResult();
    }
    /*
    用户id登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginForm form, HttpServletRequest request){
        Result r = new Result();
        System.out.println(form);
        try{
            userService.login(form);
            r.setCode(0);

            //cookie

            HttpSession session = request.getSession(true);
            int userid = userService.findIdByTele(form.getTelephone());
            session.setAttribute("userId",userid);
            r.setData(userid);
        }catch (NotFoundException ne){
            r.setMessage("账号不存在！");
            r.setCode(-1);
        }catch (Exception e){
            r.setMessage("密码不正确！");
            r.setCode(-2);
        }

        return r;
    }

    /*
    注册
     */
    @PostMapping("/register")
    public Result register(@RequestBody RegisterForm rform,HttpServletRequest request){
        System.out.println(rform.toString());
        //检查格式
        //是否已经存在
        Integer userId = userService.findIdByTele(rform.getTelephone());
        if(userId != null){
            return Result.createErrorResult("账号已经注册",-1);
        }
        //注册
        userService.register(rform);
        //查询id
        userId = userService.findIdByTele(rform.getTelephone());
        HttpSession session = request.getSession(true);//true，若不存在，则新建一个session
        session.setAttribute("userId",userId);
        System.out.println("id:"+userId);
        return Result.createOkResult();
    }

    @PostMapping("/logout/{userId}")
    public Result logout(@PathVariable("userId")int userId,
                         HttpServletRequest request){

        request.getSession().removeAttribute("userId");
        return Result.createOkResult();
    }

    @GetMapping("/user/nickName/{userId}")
    public Result getNickName(@PathVariable("userId") int userId,HttpServletRequest request){
        Result r = Result.createOkResult();
        r.setData(userService.findNickNameById(userId));
        return r;
    }
    /*
        获取用户信息
     */
    @GetMapping("/user/info/{userId}")
    public Result userInfo(@PathVariable("userId") int userId){
        Result r = Result.createOkResult();
        r.setData(userService.findById(userId));
        return r;
    }
    /*
    更新用户信息
     */
    @PostMapping("/user/update")
    public Result updateUserInfo(@RequestBody User user){

        if( userService.updateUser(user) == 1){
            return Result.createOkResult();
        }else{
            return Result.createErrorResult();
        }

    }



}
