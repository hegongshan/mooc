package com.hegongshan.mooc.util;

public class StringUtils {
	public static boolean isEmpty(String str) {
		if (str == null || str.trim().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isNotEmpty(String str) {
		if(!isEmpty(str)) {
			return true;
		} else {
			return false;
		}
	}
	public static Integer[] toIntegerArrayBySplit(String str,String regex) throws NumberFormatException {
		String[] stringArray = str.split(regex);
		Integer[] integerArray = new Integer[stringArray.length];
 		for(int i = 0;i<stringArray.length;i++) {
 			integerArray[i] = Integer.parseInt(stringArray[i]);
 		}
 		return integerArray;
	}
}
