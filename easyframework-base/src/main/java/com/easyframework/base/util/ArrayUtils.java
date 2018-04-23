package com.easyframework.base.util;

public class ArrayUtils {
 

	/**
	 * 判断字符串是否在数组中
	 * 
	 * @param array
	 * @param element
	 * @return
	 */
	public static boolean isInArray(String[] array, String element) {
		for (String s : array) {
			if (s.equals(element)) {
				return true;
			}
		}
		return false;
	}
}
