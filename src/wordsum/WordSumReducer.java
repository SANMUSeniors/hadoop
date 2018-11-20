package wordsum;
import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
public class WordSumReducer extends Reducer<Text,LongWritable,Text,LongWritable>{
    @Override
    protected void reduce(Text key,Iterable<LongWritable> values,Reducer<Text,LongWritable,
    Text,LongWritable>.Context context)throws IOException, InterruptedException {
        //reduce阶段拿到的是key = total, values是一堆1, 把values求和即可统计单词总数
        long sum = 0;
        for(LongWritable value:values)
        {
            sum +=value.get();
        context.write(key,new LongWritable(sum));
        }
    }
}