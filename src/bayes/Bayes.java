package bayes;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class Bayes {
	//�����4��, ��һ���������ļ���·��, �ڶ���������ļ���·��, �����������������Ǹ���(type1��������, type0������), ���ĸ���������е�������
	public static void main(String [] args) throws Exception {
		Configuration configuration = new Configuration();
		configuration.set("type", args[2]);
		configuration.set("num", args[3]);
		Job job = Job.getInstance(configuration);
		job.setJarByClass(Bayes.class);
		//����mapper�������
		job.setMapperClass(BayesMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		//ͨ����ν����������ļ���·��
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		//����reducer�������
		job.setReducerClass(BayesReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(BayesEntity.class);
		//ͨ����ν���������ļ���·��
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
			
		job.waitForCompletion(true);
	}
}
