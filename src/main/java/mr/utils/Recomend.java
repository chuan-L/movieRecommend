package mr.utils;

import mr.bo.RatingRecord;
import mr.bo.SimMatrix;
import mr.bo.SimRecord;

import java.util.*;

/**
 * Created by LiChuan on 2019/3/18.
 */
public class Recomend {
    /*分训练集和测试集
* 用户 - 电影
* 倒排表 电影-用户
* 计算用户相似度US 同一电影+1
            * 兴趣相似度：US/根号下用户电影数乘积
* Top N：和A最相似K个用户，计算可能电影N个
*
        * 推荐电影和测试集进行测试*/
    //电影到用户倒排表
    HashMap<Integer,ArrayList<Integer>> movie2UserMap;
    //用户到电影倒排表
    HashMap<Integer,ArrayList<Integer>> user2MovieMap;

    //原始数据
    ArrayList<RatingRecord> rawDataList;
    //用户相似度-新的数据结构
    SimMatrix userSim = new SimMatrix();

    //从文件中读取
    void readData(String fileName){

    }
    void init(){
        //获取地址
        String fileName = "";
        readData(fileName);
    }
    //根据rawDataList初始化电影-用户倒排表
    void initMovie2UserList(){

        movie2UserMap = new HashMap<>();
        for(RatingRecord record:rawDataList){
            if(movie2UserMap.containsKey(record.getMovieId())){
                movie2UserMap.get(record.getMovieId()).add(record.getUserId());
            }
            else{
                ArrayList<Integer> arr = new ArrayList<>();
                arr.add(record.getUserId());
                movie2UserMap.put(record.getMovieId(),arr);
            }
        }
    }


    public  ArrayList<SimRecord> userCF(int userId){
        int k = 16;
        //最相似的电影
        HashMap<Integer,Double> simMovieMap = new HashMap<>();

        //simRecords记录用户和他相似的用户id和以及相似度
        ArrayList<SimRecord> simRecords = userSim.findK(userId,k);

        //将k个用户所喜欢的电影打分，然后排序
        for(int i =0;i<k;i++){
            SimRecord record = simRecords.get(i);
            //用户评分的电影
            for(Integer movieId:user2MovieMap.get(record.getId())) {
                if (simMovieMap.containsKey(movieId)) {
                    double sim = simMovieMap.get(movieId);
                    simMovieMap.put(movieId, sim += record.getSimlarity() * 1);//1 系数，或者改为评分
                } else {
                    double sim = record.getSimlarity() * 1;
                    simMovieMap.put(movieId, sim);
                }
            }
        }
        //排序
        ArrayList<SimRecord> simMovies = new ArrayList<>();

        for(Map.Entry<Integer,Double> entry:simMovieMap.entrySet()){
            simMovies.add(new SimRecord(entry.getKey(),entry.getValue()));
        }
        simMovies.sort(new Comparator<SimRecord>() {
            @Override
            public int compare(SimRecord o1, SimRecord o2) {
                if(o1.getSimlarity() < o2.getSimlarity()){
                    return -1;
                }
                else if(o1.getSimlarity() > o2.getSimlarity()){
                    return 1;
                }
                else{
                    return 0;
                }
            }
        });

        return simMovies;
    }
    public static  int similarity(){

        return 1;
    }
}
