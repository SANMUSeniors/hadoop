package Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import redis.clients.jedis.Jedis;

public class Test {
    public static void main(String args []) throws Exception {
        Jedis jedis = new Jedis("localhost", 6379);
        String [] filename = new File("china").list();
        String tempString = null;
        int countspain = 0;
        int counttaiwan = 0;

        for (int i = 0; i < filename.length; i++) {
            double cspain = Math.log((double)204.0/414);
            double ctaiwan = Math.log((double)210.0/414);
            File file = new File("china/"+filename[i]);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while((tempString = bufferedReader.readLine()) != null) {
                tempString = tempString.replaceAll("\t", " ");
                while (tempString.contains("  ")) {
                    tempString = tempString.replaceAll("  ", " ");
                }
                String [] lines = tempString.split(" ");
                String word = lines[0];
                String values = jedis.get(word);
                if(values != null) {
                    cspain = cspain + Double.parseDouble(values.split(",")[1]);
                    ctaiwan = ctaiwan + Double.parseDouble(values.split(",")[2]);
                }
            }
            if (cspain >= ctaiwan) {
                System.out.println("china");
                countspain++;
            }else {
                System.out.println("cana");
                counttaiwan++;
            }
            bufferedReader.close();
        }
        System.out.println((double)(1.0*countspain/filename.length));
        System.out.println("right:"+countspain);
        System.out.println("wrong:"+counttaiwan);
        jedis.close();
    }
}