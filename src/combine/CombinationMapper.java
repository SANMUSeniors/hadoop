package combine;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CombinationMapper extends Mapper<LongWritable, Text, Text, BayesEntity>{

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		line = line.replaceAll("\t", " ");
		while (line.contains("  ")) {
			line.replaceAll("  ", " ");
		}
		String [] fields = line.split(" ");
		String word = fields[0];
		long times = Long.parseLong(fields[1]); 
		double chance = Double.parseDouble(fields[2]);
		String type = fields[3];
		BayesEntity bayesEntity = new BayesEntity(times, chance, type);
		context.write(new Text(word), bayesEntity);
	}

	
}
