package org.hit.data.random;

/**
 * @ClassName RandomInt
 * @Description 动态的调整数据的范围
 * @author kg
 */

public class RandomIntKey extends AbstractRandomInt<Integer> {
	
	public int startIndex;
	
	public RandomIntKey(int startIndex) {
		super(System.currentTimeMillis(), 1);
		this.startIndex=startIndex;
	}
	
	public RandomIntKey(long seed, int expectedUsagePerRow) {
		super(seed, expectedUsagePerRow);
	}
	

	public int nextValue(int lowValue, int highValue) {
		return super.nextInt(lowValue, highValue);
	}

	public Integer nextValue() {
		return startIndex++;
	}
	
	//
	public static void main(String[] args) {
		RandomIntKey randomInt = new RandomIntKey(System.currentTimeMillis(), 3);
		
		for(int i= 1;i<9;i++){
			int count = randomInt.nextInt(1, 1*RandomDecimal.num[i]);
			randomInt.rowFinished();
			System.out.println(count);
		}
	}

}