package mr.mapper;

import mr.entity.Rate;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by LiChuan on 2019/2/20.
 */
@Component
public interface RateMapper {

    @Insert("insert into rate(user_id,movie_id,rating) " +
            "values(#{userId},#{movieId},#{rating})")
    Integer insert(Rate rate);


}
