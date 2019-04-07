package mr.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

/**
 * Created by LiChuan on 2019/2/20.
 */
public  class FileUtiles {

    public static int readCsvFile(){
        try {
            Reader in = new FileReader("C:\\Users\\LiT\\Desktop\\ml-latest-small\\ratings.csv");
            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
            int i =0;
            for (CSVRecord record : records) {
                String user = record.get(0);
                String movie = record.get(1);
                String score = record.get(2);
                System.out.println(user+movie+" "+score);
                i++;
                if(i == 4){
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        return 0;
    }

    public static String  storeFile(MultipartFile file, String filePath, String fileName) throws Exception{
        if (file.isEmpty()) {
            throw new Exception("file is empty");
        }


        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public static String getUserImagePath() throws IOException{
        Resource resource = new ClassPathResource("application.properties");

        Properties props = PropertiesLoaderUtils.loadProperties(resource);
        return props.getProperty("userdir");
    }
    public static String getMovieImagePath() throws IOException{
        Resource resource = new ClassPathResource("application.properties");

        Properties props = PropertiesLoaderUtils.loadProperties(resource);
        return props.getProperty("moviedir");

    }
}
