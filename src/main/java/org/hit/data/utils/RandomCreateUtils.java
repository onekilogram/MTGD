package org.hit.data.utils;

import org.hit.data.random.AbstractRandomInt;
import org.hit.data.random.RandomBoundedInt;
import org.hit.data.random.RandomDecimal;
import org.hit.data.random.RandomIntKey;
import org.hit.data.random.RandomText;
import org.hit.data.table.Field;
import org.hit.data.table.FieldType;
import org.hit.data.text.TextPool;

public class RandomCreateUtils {

	public static AbstractRandomInt getRandomByField(Field field) {

		// 先判断是否是主键
		if (field.isPrimaryKey()) {
			return new RandomIntKey(1);
		} else if (field.getFieldType() == FieldType.INT
				|| field.getFieldType() == FieldType.INTEGER) {
			// 考虑位数
			return new RandomIntKey(1);

		} else if (field.getFieldType() == FieldType.DOUBLE) {
			if (field.getDecimalPoint() == 0) {// 整数
				return new RandomBoundedInt(System.nanoTime(),
						field.getMinValue(), field.getMaxValue());
			}

			return new RandomDecimal(System.nanoTime(),
					field.getMinValue(), field.getMaxValue(), field.getDecimalPoint());
		}else {
			return new RandomText(System.nanoTime(), TextPool.getDefaultTestPool());
		}
		//return null;
	}
}
