package service;

import mr.Application;
import mr.entity.Rate;
import mr.mapper.MovieMapper;
import mr.mapper.MovieTypeMapper;
import mr.service.MovieService;
import mr.service.RateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by LiChuan on 2019/3/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ServiceTest {
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieTypeMapper movieTypeMapper;
    @Test
    public void addTest(){

       Integer a = movieService.findIdByNameAndDirector("a","b");
        System.out.println(a);
        Integer b = movieMapper.findIdByNameAndDirector("a","b");
        System.out.println(b);
    }
    @Test
    public void findNameById(){
        String s = movieTypeMapper.findNameById(1);
        System.out.println(s);
        System.out.println(movieTypeMapper.findNameById(2));
    }
}
