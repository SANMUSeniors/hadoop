package com.lin.train.prior;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PriorReducer extends Reducer<Text,Text,Text,LongWritable>{
    @Override
    protected void reduce(Text key,Iterable<Text> values,Reducer<Text,Text,Text,
    LongWritable>.Context context)throws IOException,InterruptedException{
        ArrayList<String> strArray = new ArrayList<String>();
        for(Text text: values)
        {
            String value = text.toString();
            if(!strArray.contains(value))
            {
                strArray.add(value);
            }
        }
        long nums = strArray.size();
        context.write(key,new LongWritable(nums));
    }
}