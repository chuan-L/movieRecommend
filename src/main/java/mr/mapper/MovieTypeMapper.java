package mr.mapper;

import mr.entity.MovieType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by LiChuan on 2019/3/24.
 */
@Component
public interface MovieTypeMapper {
    //
    @Select("select type_name from movie_type where type_id = #{id}")
    String findNameById(int id);

    @Select("select type_id from movie_type where type_name = #{name}")
    Integer findIdByName(String name);

    @Select("select * from movie_type ")
    List<MovieType> findListAll();


}
