package mr.service;

import mr.entity.User;
import mr.exception.NotFoundException;
import mr.mapper.UserMapper;
import mr.vo.LoginForm;
import mr.vo.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.security.SecureRandom;
import java.security.Security;

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
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            String salt = ""+random.nextInt();

            String p = rform.getPassword()+salt;
            String pswd = DigestUtils.md5DigestAsHex(p.getBytes());

            return  userMapper.insertRegisterUser(rform.getTelephone(),rform.getNickName(),pswd,salt);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }


    }
    /*
    登录
     */
    public void login(LoginForm loginForm) throws Exception{
        String salt = userMapper.findSaltByTele(loginForm.getTelephone());

        //不存在
        if(salt == null || salt.isEmpty() ){
            throw new NotFoundException("账号不存在！");
        }
        //存在用户
        else{

            String p = loginForm.getPassword()+salt;
            String pswd = DigestUtils.md5DigestAsHex(p.getBytes());

            if(pswd.equals(userMapper.findPswdByTele(loginForm.getTelephone()))){
                return;
            }else {//密码不正确
                throw new Exception("密码不正确!");
            }
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
