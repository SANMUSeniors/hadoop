package combine;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CombinationReducer extends Reducer<Text, BayesEntity, Text, BayesTrainResult>{

	@Override
	protected void reduce(Text key, Iterable<BayesEntity> values,
			Context context) throws IOException, InterruptedException {
		long times = 0;
		BayesTrainResult bayesTrainResult = new BayesTrainResult(0, 0, 0);
		for(BayesEntity bayesEntity : values) {
			if ("type1".equals(bayesEntity.getType())) {
				bayesTrainResult.setPositiveChance(bayesEntity.getChance());
			}
			if ("type0".equals(bayesEntity.getType())) {
				bayesTrainResult.setNegativeChance(bayesEntity.getChance());
			} 
			times += bayesEntity.getTimes();
		}
		bayesTrainResult.setTimes(times);
		long positive = Long.parseLong(context.getConfiguration().get("positive"));
		long negative = Long.parseLong(context.getConfiguration().get("negative"));
		
		if (bayesTrainResult.getNegativeChance() == 0) {
			bayesTrainResult.setNegativeChance((double)(1.0/negative));
		}
		if (bayesTrainResult.getPositiveChance() == 0) {
			bayesTrainResult.setPositiveChance((double)(1.0/positive));
		}
		context.write(key, bayesTrainResult);
	}
	
}
