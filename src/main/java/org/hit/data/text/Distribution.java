package org.hit.data.text;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hit.data.random.RandomInt;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

public class Distribution {
	private final String name;
	private final List<String> values;
	private final int[] weights;
	private final String[] distribution;
	private final int maxWeight;

	public Distribution(String name, Map<String, Integer> distribution) {
		this.name = checkNotNull(name, "name is null");
		checkNotNull(distribution, "distribution is null");

		ImmutableList.Builder<String> values = ImmutableList.builder();
		this.weights = new int[distribution.size()];

		int runningWeight = 0;
		
		int index = 0;
		boolean isValidDistribution = true;
		for (Entry<String, Integer> entry : distribution.entrySet()) {
			values.add(entry.getKey());

			runningWeight += entry.getValue();
			weights[index] = runningWeight;

			isValidDistribution = isValidDistribution && entry.getValue() > 0;

			index++;
		}
		this.values = values.build();

		// "nations" is hack and not a valid distribution so we need to skip it
		if (isValidDistribution) {
			this.maxWeight = weights[weights.length - 1];
			this.distribution = new String[maxWeight];

			index = 0;
			for (String value : this.values) {
				int count = distribution.get(value);
				for (int i = 0; i < count; i++) {
					this.distribution[index++] = value;
				}
			}
		} else {
			this.maxWeight = -1;
			this.distribution = null;
		}
	}

	public String getValue(int index) {
		return values.get(index);
	}

	public List<String> getValues() {
		return values;
	}

	public int getWeight(int index) {
		return weights[index];
	}

	public int size() {
		return values.size();
	}

	public String randomValue(RandomInt randomInt) {
		checkState(distribution != null, "%s does not have a distribution", name);

		int randomValue = randomInt.nextInt(0, maxWeight - 1);
		return distribution[randomValue];
	}

	@Override
	public String toString() {
		//System.out.println(Objects.toStringHelper(this).add("x", 1).toString());
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("Distribution[name="+name+",values=");
		
		for(String string :values){
			stringBuilder.append(string+",");
		}
		return  new String("Distribution[name="+name+"]");
		//return  Objects.toStringHelper(this).add("name", name).toString();
		
	}
}
