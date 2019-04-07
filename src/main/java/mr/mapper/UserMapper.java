package mr.mapper;

import mr.entity.User;
import mr.vo.LoginForm;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * Created by LiChuan on 2019/3/21.
 */
@Component
public interface UserMapper {


    @Select("select user_id from user where telephone = #{telephone} and password = #{password} ")
    Integer findIdByTeleAndPswd(LoginForm form);

    @Select("select password from user where telephone = #{telephone} ")
    String findPswdByTele(String telephone);
    @Select("select * from user where user_id = #{userId} ")
    User findById(int userId);

    @Select("select user_id from user where telephone = #{telephone}")
    Integer findIdByTelephone(String telephone);

    //注册 option:返回插入后的主键
    @Options(useGeneratedKeys = true,keyProperty = "userId",keyColumn = "user_id")
    @Insert("insert into user (telephone,nick_name,password) values(#{telephone},#{nickName},#{password})")
    Integer insertRegisterUser(@Param("telephone") String telephone,
                           @Param("nickName") String nickName,
                           @Param("password") String password);
    //修改用户信息
    @Update("update user set nick_name = #{nickName}, password = #{password}," +
            "email = #{email},introduce = #{introduce},profile_img_path = #{profileImgPath} " +
            "where user_id = #{userId}")
    Integer updateUser(User user);

    //查找昵称
    @Select ("select nick_name from user where user_id = #{userId}")
    String findNickNameById(Integer userId);
}
