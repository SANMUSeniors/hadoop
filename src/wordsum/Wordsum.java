package wordsum;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
public class Wordsum {
    //入参有2个, 第一个是 输入文件的路径, 第二个是 输出文件的路径
    //功能:统计目录下所有文件中的单词总数
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);
        job.setJarByClass(Wordsum.class);
        //设置mapper相关属性
        job.setMapperClass(WordSumMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);
        //通过入参解析出输入文件的路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        //设置reducer相关属性
        job.setReducerClass(WordSumReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);
        //通过入参解析出输出文件的路径
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.waitForCompletion(true);
    }
}