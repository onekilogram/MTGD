package org.hit.tpch;

import static java.util.Locale.ENGLISH;

public class RandomPhoneNumber extends AbstractRandomInt {
	// limited by country codes in phone numbers
	private static final int NATIONS_MAX = 90;

	public RandomPhoneNumber(long seed) {
		this(seed, 1);
	}

	public RandomPhoneNumber(long seed, int expectedRowCount) {
		super(seed, 3 * expectedRowCount);// 因为每一行有3次的nextInt
	}

	public String nextValue(long nationKey) {
		return String.format(ENGLISH, "%02d-%03d-%03d-%04d", (10 + (nationKey % NATIONS_MAX)),
				nextInt(100, 999), nextInt(100, 999), nextInt(1000, 9999));
	}
}