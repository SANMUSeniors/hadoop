package com.lin.train.prior;
import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class PriorMapper extends Mapper<LongWritable,Text,Text,Text>
{
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable,Text,Text,
    Text>.Context context)throws IOException,InterruptedException{
        //解析文件名
        InputSplit inputSplit =context.getInputSplit();
        String path = ((FileSplit)inputSplit).getPath().getName();
        String fileds [] = path.split("/");
        String filename = fileds[fileds.length-1];
        context.write(new Text(context.getConfiguration().get("type")), new Text(filename));
    }
}

