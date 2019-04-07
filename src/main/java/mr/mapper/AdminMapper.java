package mr.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by LiChuan on 2019/1/21.
 */
@Component
public interface AdminMapper {
    @Select("select password from admin where name = #{name} ")
    String findPswdByName(String name);
}
