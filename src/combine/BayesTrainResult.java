package combine;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class BayesTrainResult implements Writable{
	//���ʵĴ�Ƶ
	private long times;
	//���ʵ���������
	private double positiveChance;
	//���ʵĸ�������
	private double negativeChance;
	
	
	public BayesTrainResult() {
		super();
	}

	public BayesTrainResult(long times, double positiveChance, double negativeChance) {
		super();
		this.times = times;
		this.positiveChance = positiveChance;
		this.negativeChance = negativeChance;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.times = in.readLong();
		this.positiveChance = in.readDouble();
		this.negativeChance = in.readDouble();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeLong(times);
		out.writeDouble(positiveChance);
		out.writeDouble(negativeChance);
	}

	public long getTimes() {
		return times;
	}

	public void setTimes(long times) {
		this.times = times;
	}

	public double getPositiveChance() {
		return positiveChance;
	}

	public void setPositiveChance(double positiveChance) {
		this.positiveChance = positiveChance;
	}

	public double getNegativeChance() {
		return negativeChance;
	}

	public void setNegativeChance(double negativeChance) {
		this.negativeChance = negativeChance;
	}

	@Override
	public String toString() {
		return times + "\t" + positiveChance + "\t" + negativeChance;
	}
	
}
