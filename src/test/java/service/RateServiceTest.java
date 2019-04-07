package service;

import mr.Application;
import mr.entity.Rate;
import mr.service.RateService;
import mr.utils.FileUtiles;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileReader;
import java.io.Reader;
import java.util.Properties;

/**
 * Created by LiChuan on 2019/2/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RateServiceTest {

    @Autowired
    private RateService rateService;

    @Test
    public void addTest(){
        try {
            Rate r = new Rate();
            r.setUserId(1);
            r.setMovieId(1);
            r.setRating(3);
            rateService.insertRate(r);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void aTest() {
        try{
            Resource resource = new ClassPathResource("application.properties");
            Properties props = PropertiesLoaderUtils.loadProperties(resource);

            System.out.println(props.getProperty("moviedir"));
            System.out.println(props.getProperty("userdir"));
        }catch (Exception e){
            e.printStackTrace();
        }




    }
    @Test
    public void readFileTest(){
        try {
            Reader in = new FileReader("C:\\Users\\LiT\\Desktop\\ml-latest-small\\ratings.csv");
            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
            int i =0;

            for (CSVRecord record : records) {
                String user = record.get(0);
                String movie = record.get(1);
                String score = record.get(2);

                Rate r = new Rate();
                r.setUserId(Integer.parseInt(user));
                r.setMovieId(Integer.parseInt(movie));
                r.setRating((int)(Float.parseFloat(score)*2));
                i++;//计数
                System.out.println(i+":  "+r.getMovieId());
                rateService.insertRate(r);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
/*
* 分训练集和测试集
* 用户 - 电影
* 倒排表 电影-用户
* 计算用户相似度US 同一电影+1
* 兴趣相似度：US/根号下用户电影数乘积
* Top N：和A最相似K个用户，计算可能电影N个
*
* 推荐电影和测试集进行测试
*
*
* 生成 用户-电影表
* 计算矩阵 W电影数量为矩阵 电影+1
* 电影相似度
*
*
* */
