package Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import redis.clients.jedis.Jedis;
public class ImportRedis {
    //将数据导入redis
    public static void main(String args []) throws Exception {
        String tempString = null;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("data")));
        Jedis jedis = new Jedis("localhost", 6379);
        while((tempString = bufferedReader.readLine()) != null) {
            tempString = tempString.replaceAll("\t", " ");
            while (tempString.contains("  ")) {
                tempString = tempString.replaceAll("  ", " ");
            }
            String [] lines = tempString.split(" ");

            jedis.set(lines[0], lines[1]+","+Math.log(Double.parseDouble(lines[2]))+","+Math.log(Double.parseDouble(lines[3])));
        }
        jedis.close();
        bufferedReader.close();
    }

}
