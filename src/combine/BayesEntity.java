package combine;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

//reducer��value������, ��ʵ���Բ���ô��, ��Ҫ���볢��һ��
public class BayesEntity implements Writable{

	//���ʵĴ�Ƶ
	private long times;
	//���ʵĸ���
	private double chance;
	//�������ڵ�����(����or����)
	//0�Ǹ���, 1������
	private String type;
	
	public BayesEntity() {
		super();
	}

	public BayesEntity(long times, double chance, String type) {
		this.times = times;
		this.chance = chance;
		this.type = type;
	}

	@Override
	public void readFields(DataInput input) throws IOException {
		this.times = input.readLong();
		this.chance = input.readDouble();
		this.type = input.readUTF();
	}

	@Override
	public void write(DataOutput output) throws IOException {
		output.writeLong(times);
		output.writeDouble(chance);
		output.writeUTF(type);
	}

	public long getTimes() {
		return times;
	}

	public void setTimes(long times) {
		this.times = times;
	}

	public double getChance() {
		return chance;
	}

	public void setChance(double chance) {
		this.chance = chance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return times +  "\t" + chance + "\t" + type;
	}
	
}
