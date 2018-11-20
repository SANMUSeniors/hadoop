package wordsum;
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordSumMapper extends Mapper<LongWritable, Text, Text, LongWritable>{

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, LongWritable>.Context context)
            throws IOException, InterruptedException {
        //由于数据格式已经分好为一行一个词, 所以每拿到一个词都用total作为key, 并把1作为value.
        context.write(new Text("total"), new LongWritable(1));
    }

}
