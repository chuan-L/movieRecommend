package mr.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by LiChuan on 2019/3/18.
 */
//相似矩阵
public class SimMatrix {
    //用户id到数组下标的对应
    HashMap<Integer,Integer> mapping;
    //根据下标找到id
    ArrayList<Integer> indexMapping;
    int size;
    int usedSize;
    //大小为用户数
    int[][] matrix;
    ArrayList<Integer> elemCount;
    //找出最相似K个,返回K个的id和相似度
    public ArrayList<SimRecord> findK(int id,int k){
        ArrayList<SimRecord> arrayList = new ArrayList<>();
        int rid = mapping.get(id);
        for(int i=0;i<usedSize;i++){
            if(matrix[rid][i] != 0){
                SimRecord record = new SimRecord();
                record.setId(indexMapping.get(i));
                //计算余弦相似度
                record.setSimlarity(matrix[rid][i]/Math.sqrt(elemCount.get(rid)*elemCount.get(i)));

                arrayList.add(record);
            }
        }
        Collections.sort(arrayList, new Comparator<SimRecord>() {
            @Override
            public int compare(SimRecord o1, SimRecord o2) {
                double dif = o1.getSimlarity()-o2.getSimlarity();
                if(dif < 0){
                    return -1;
                }else if(dif > 0 ){
                    return 1;
                }else{
                    return 0;
                }
            }
        });
        return arrayList;
    }

    public void init(HashMap<Integer,ArrayList<Integer>> movie2UserMap){
        mapping = new HashMap<>();
        indexMapping = new ArrayList<>();
        elemCount =new  ArrayList<Integer>();
        //初始时大小为1024，空间不够时，size变为原来2倍
        size = 1024;
        usedSize = 0;
        matrix = new int[size][];
        for(int i = 0;i<size;i++){
            matrix[i] = new int[size];
        }
        //arr是喜欢同一个电影的用户
        for(ArrayList<Integer> arr:movie2UserMap.values()){
            for(Integer id:arr){
                addElem(id);
            }
            for(int i =0;i<arr.size()-1;i++){
                for(int j = i+1;j<arr.size();j++){
                    int ri = mapping.get(arr.get(i));
                    int rj = mapping.get(arr.get(j));
                    //共有多少个有共同兴趣的用户
                    if(matrix[ri][rj] == 0){
                        elemCount.set(rj,elemCount.get(rj)+1);
                        elemCount.set(ri,elemCount.get(ri)+1);
                    }

                    matrix[ri][rj]+=1;//不管评多少分，相似度都加1
                    matrix[rj][ri]+=1;
                }
            }
        }
    }

    //扩展
    public void extend(){
        int[][] tmp;
        tmp = matrix;
        size*=2;
        matrix = new int[size][];
        for(int i = 0;i<size;i++){
            matrix[i] = new int[size];
            //复制原来的数据
            if(i<usedSize){
                for(int j = 0;j<usedSize;j++){
                    matrix[i][j] = tmp[i][j];
                }
            }
        }

    }

    //随时添加新id，如果空间不够，扩大二维数组
    public void addElem(int id){
        if(usedSize<size){
            mapping.put(id,usedSize);
            indexMapping.add(id);
            usedSize++;
        }
        else{
            extend();
            addElem(id);
        }
    }

    //在原有矩阵上添加记录，不改变数组大小
    public void addRecord(int id,ArrayList<Integer> arrayList){
        int index = mapping.get(id);
        for(Integer i:arrayList){
            matrix[index][mapping.get(i)] +=1;
            matrix[mapping.get(i)][index] +=1;
        }
        matrix[index][index] = 0;

    }
}
