package com.easyframework.base.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作类
 * 
 */
public class StringUtils {

	/**
	 * 判断一个字符串是否为空，即 为 null 或 "" 或 "NULL" 或 "null"
	 * 
	 * @param string
	 * 
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str.trim()) || "NULL".equalsIgnoreCase(str.trim());
	}

	/**
	 * 在isEmpty方法的基础上,加入了去除空格的判断
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		if (isEmpty(str)) {
			return true;
		}
		if (str.trim().length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 检验字符串是否等于某个不为空的值
	 * 
	 * @param str
	 *            String 源字符
	 * @param compareStr
	 *            String 比较字符
	 */
	public static boolean equals(String str, String compareStr) {

		if (isEmpty(str) || isEmpty(compareStr)) {
			return false;
		}

		return str.equals(compareStr);
	}

	/**
	 * 将字符串反转输出
	 * 
	 * @param str
	 * @return
	 */
	public static String reverse(String str) {
		StringBuilder temp = new StringBuilder(str);
		return temp.reverse().toString();
	}

	/**
	 * 判断字符是否真实数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		try {
			new BigDecimal(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	/**
	 * 检验字符串数组是否为空或长度为零
	 * 
	 * @param strArr
	 * @return
	 */
	public static boolean isEmpty(String[] strArr) {

		return strArr == null || strArr.length == 0;
	}

	/**
	 * 删除一个数组中在另外一个数组存在的字符串
	 * 
	 * @param operateArray
	 *            String[] 要删除元素的数组
	 * @param referArray
	 *            String[] 参照数组
	 * @return String[] 经处理后的数组
	 */
	public static String[] subArrayElement(String[] operateArray, String[] referArray) {

		if (isEmpty(operateArray) || isEmpty(referArray)) {
			return operateArray;
		}

		List<String> operateList = arrayToList(operateArray);
		List<String> referList = arrayToList(referArray);
		operateList.removeAll(referList);

		String[] result = listToArray(operateList);
		return isEmpty(result) ? new String[0] : result;
	}

	/**
	 * 把数组转换为List
	 * 
	 * @param strArr
	 * @return
	 */
	public static List<String> arrayToList(String[] strArr) {
		List<String> list = new ArrayList<String>();
		if (isEmpty(strArr)) {
			return list;
		}
		return Arrays.asList(strArr);
	}


	/**
	 * 将指定字符串列表转换成字符数组.
	 * 
	 * @param strList
	 *            字符串列表.
	 * @return 字符数组.
	 */
	public static String[] listToArray(List<String> strList) {
		if (strList == null || strList.size() == 0)
			return null;
		return strList.toArray(new String[0]);

	}



	/**
	 * 判断字符串是否为英文字母
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEnglish(String str) {
		return str.matches("^[a-zA-Z]*");
	}


	/**
	 * 检验字符串集合是否为空 若集合中某元素为空，同样返回true
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(List<String> str) {
		if (CollectionUtils.isEmpty(str)) {
			return true;
		}
		for (String string : str) {
			if (isEmpty(string)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 数组转换成字符串 中间用分隔符连接
	 * <p>
	 * 比如: arrayToString={"one","two"},delimiters=";"; toString(array) return
	 * "one;two"<br>
	 * 如果参数为空,则返回""
	 * <p>
	 * *
	 * 
	 * @param array
	 *            目标数组
	 * @param delimiters
	 *            分隔符
	 * @return
	 */
	public static String arrayToString(Object[] array, String delimiters) {
		if (CollectionUtils.isEmpty(array)) {
			return "";
		}
		int len = array.length;
		StringBuilder buf = new StringBuilder(len * 12);
		for (int i = 0; i < len - 1; i++) {
			buf.append(array[i]).append(delimiters);
		}
		return buf.append(array[len - 1]).toString();
	}

	/**
	 * 去除html
	 * 
	 * @param html
	 * @return
	 */
	public static String replaceHtml(String html) {
		String regEx = "<.+?>"; // 表示标签
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}


	/**
	 * 判断字符串是否是数字
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	public static void main(String[] args) {
		// System.out.println(isNumeric("123"));
	}

}
