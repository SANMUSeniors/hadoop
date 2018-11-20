package combine;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Combination {
	//�����4��, ��һ���������ļ���·��, �ڶ���������ļ���·��, ��3����������������, ��4���Ǹ�����������
	public static void main(String args []) throws Exception {
		Configuration configuration = new Configuration();
		configuration.set("positive", args[2]);
		configuration.set("negative", args[3]);
		Job job = Job.getInstance(configuration);
		job.setJarByClass(Combination.class);
		//����mapper�������
		job.setMapperClass(CombinationMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(BayesEntity.class);
		//ͨ����ν����������ļ���·��
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		//����reducer�������
		job.setReducerClass(CombinationReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(BayesTrainResult.class);
		//ͨ����ν���������ļ���·��
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.waitForCompletion(true);
	}
}
