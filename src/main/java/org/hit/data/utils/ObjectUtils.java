package org.hit.data.utils;

public class ObjectUtils {

	public static Object checkIsNull(Object object, Object object2) {
		return object != null ? object : object2;
	}

	public static String checkStringIsNull(String object, String object2) {
		return object != null ? object : object2;
	}

	public static boolean checkStringIsTrue(String object, boolean flag) {
		if (object == null) {
			return flag;
		} else {
			if (object.equals("ture")) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public static int checkIntIsNUll(String object, int defaultValue) {
		if (object == null) {
			return defaultValue;
		} else {
			return Integer.valueOf(object);
		}
	}

}
