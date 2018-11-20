package com.lin.train.prior;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Prior{
    //入参有3个, 第一个是输入文件路径, 第二个是输出文件路径, 第三个参数说明是正例还是负例
     public static void main(String[] args) throws Exception{
         Configuration configuration = new Configuration();
         configuration.set("type",args[2]);
         Job job =Job.getInstance(configuration);
         job.setJarByClass(Prior.class);
    //设置mapper相关属性
        job.setMapperClass(PriorMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
    //通过入参解析出输入文件的路径
         FileInputFormat.setInputPaths(job, new Path(args[0]));
    //设置reducer相关属性
         job.setReducerClass(PriorReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
    //通过入参解析出输出文件的路径
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.waitForCompletion(true);
     }
    }