package bayes;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class BayesReducer extends Reducer<Text, LongWritable, Text, BayesEntity>{
	private BayesEntity bayesEntity = new BayesEntity();
	@Override
	protected void reduce(Text key, Iterable<LongWritable> values,
			Context context) throws IOException, InterruptedException {
		long times = 0;
		for (LongWritable value : values) {
			times += value.get();
		}
		//total��Ӧnegative��������, ͨ��WordSum����
		long total = Long.parseLong(context.getConfiguration().get("num"));
		double chance = (double) times / total;
		String type = context.getConfiguration().get("type");
		bayesEntity.setTimes(times);
		bayesEntity.setChance(chance);
		bayesEntity.setType(type);
		context.write(key, bayesEntity);
	}
}
