package org.easyframework.core.util;

public class ArrayUtils {
	/**
	 * @description 将指定的元素追加到指定的数组
	 * @param oldArr
	 * @param element
	 * @return
	 */
	public static String[] addArrayElement(String oldArr[], String element) {
		element = (element == null ? "" : element);

		if ((oldArr == null) || (oldArr.length == 0)) {
			return new String[] { element };
		}

		String newArr[] = new String[oldArr.length + 1];
		for (int i = 0; i < oldArr.length; i++) {
			newArr[i] = oldArr[i];
		}

		newArr[oldArr.length] = element;
		return newArr;
	}

	public static Object[] addArrayElement(Object oldArr[], Object element) {
		element = (element == null ? "" : element);

		if ((oldArr == null) || (oldArr.length == 0)) {
			return new Object[] { element };
		}

		Object newArr[] = new Object[oldArr.length + 1];
		for (int i = 0; i < oldArr.length; i++) {
			newArr[i] = oldArr[i];
		}

		newArr[oldArr.length] = element;

		return newArr;
	}

	/**
	 * @description 用于除去查询字符串左右两边的空格
	 * @param obj
	 *            []
	 * @return
	 */
	public static Object[] trims(Object obj[]) {
		if (obj == null)
			return null;

		for (int i = 0; i < obj.length; i++) {
			obj[i] = (obj[i] == null) ? "" : ("" + obj[i]).trim();
		}

		return obj;
	}

	/**
	 * 把字符串数组转成小写
	 * 
	 * @param strArr
	 * @return
	 */
	public static String[] toLowerCase(String[] strArr) {
		String str = StringUtils.joinString(strArr).toLowerCase();
		return str.split(",");
	}

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
