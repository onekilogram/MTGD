package org.hit.data.random;

/**
 * @ClassName RandomBoundedInt
 * @Description 负责产生一个范围的数,例如 0-100
 * @author kg
 * @date 2017年12月27日 下午4:07:04
 */
public class RandomBoundedInt extends AbstractRandomInt<Integer> {
	private final int lowValue;
	private final int highValue;

	public RandomBoundedInt(long seed, int lowValue, int highValue) {
		this(seed, lowValue, highValue, 1); // expectedRowCount=1
	}

	public RandomBoundedInt(long seed, int lowValue, int highValue, int expectedRowCount) {
		super(seed, expectedRowCount);
		this.lowValue = lowValue;
		this.highValue = highValue;
	}

	public Integer nextValue() {
		return nextInt(lowValue, highValue);
	}

	public static void main(String[] args) {
		//
		int min = 1;
		int max = 100000;

		RandomBoundedInt randomBoundedInt = new RandomBoundedInt(System.currentTimeMillis(), min,
				max);

		for (int i = 0; i < 100; i++) {

			int count = randomBoundedInt.nextValue();
			randomBoundedInt.rowFinished();
			System.out.println(count);
		}

	}
}