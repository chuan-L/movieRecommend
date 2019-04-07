package mr.service;

import mr.entity.User;
import mr.exception.NotFoundException;
import mr.mapper.UserMapper;
import mr.vo.LoginForm;
import mr.vo.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LiChuan on 2019/3/21.
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    /*
    注册
     */
    public Integer register(RegisterForm rform){
       return  userMapper.insertRegisterUser(rform.getTelephone(),rform.getNickName(),rform.getPassword());
    }
    /*
    登录
     */
    public boolean login(LoginForm loginForm) throws Exception{
        //存在用户
        String pswd = userMapper.findPswdByTele(loginForm.getTelephone());

        //用户密码正确
        Integer id  = userMapper.findIdByTeleAndPswd(loginForm);
        //不存在
        if(pswd == null || pswd.isEmpty() ){
            throw new NotFoundException("账号不存在！");
        }//密码正确
        else if(pswd.equals(loginForm.getPassword())){
            return true;
        }//密码不正确
        else{
            throw new Exception("密码不正确!");
        }

    }

    /*
    根据telephone查找id
     */
    public Integer findIdByTele(String telephone){
       return  userMapper.findIdByTelephone(telephone);
    }
    /*
    根据id查找
     */
    public User findById(Integer userId){
        return userMapper.findById(userId);
    }
    /*
    修改用户信息
     */
    public Integer updateUser(User user){
        return userMapper.updateUser(user);
    }

    /*
    查找nickname
     */
    public String findNickNameById(Integer userId) {
        return userMapper.findNickNameById(userId);
    }
}
