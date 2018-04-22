package org.easyframework.core.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作类
 * 
 */
public class StringUtils {

	public static final int HIGHEST_SPECIAL = '>';

	/**
	 * 特殊字符集
	 */
	public static char[][] specialChars = new char[HIGHEST_SPECIAL + 1][];
	static {
		specialChars['&'] = "&amp;".toCharArray();
		specialChars['<'] = "&lt;".toCharArray();
		specialChars['>'] = "&gt;".toCharArray();
		specialChars['"'] = "&#034;".toCharArray();
		specialChars['\''] = "&#039;".toCharArray();
	}

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
	 * 全角空格为12288，半角空格为32 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
	 * 
	 * 将字符串中的全角字符转为半角
	 * 
	 * @author wangjc
	 * @date 2008-8-20 下午03:20:18
	 * @param str
	 *            要转换的包含全角的任意字符串
	 * @return 转换之后的字符串
	 */
	public static String toSemiangle(String str) {
		if (isEmpty(str))
			return null;
		char[] c = str.trim().toCharArray();
		for (int index = 0; index < c.length; index++) {
			if (c[index] == 12288) {// 全角空格
				c[index] = (char) 32;
			} else if (c[index] > 65280 && c[index] < 65375) {// 其他全角字符
				c[index] = (char) (c[index] - 65248);
			}
		}
		return String.valueOf(c);
	}

	/**
	 * 半角转全角
	 * 
	 * @Methods Name half2Fullchange
	 * @Create In 2012-8-24 By v-jiangwei
	 * @param QJstr
	 * @return String
	 */
	public static final String half2Fullchange(String QJstr) {
		StringBuffer outStrBuf = new StringBuffer("");
		String Tstr = "";
		byte[] b = null;
		for (int i = 0; i < QJstr.length(); i++) {
			Tstr = QJstr.substring(i, i + 1);
			if (Tstr.equals(" ")) {
				// 半角空格
				outStrBuf.append(Tstr);
				continue;
			}
			try {
				b = Tstr.getBytes("unicode");
				if (b[2] == 0) {
					// 半角
					b[3] = (byte) (b[3] - 32);
					b[2] = -1;
					outStrBuf.append(new String(b, "unicode"));
				} else {
					outStrBuf.append(Tstr);
				}
				return outStrBuf.toString();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return outStrBuf.toString();
	}

	// /**
	// *
	// * <description>获得去除特殊字符和标点符号后的字符串</description>
	// *
	// * @author wangjc
	// * @date 2008-8-20 下午03:20:18
	// * @param str
	// * @return
	// */
	// public static String getRegexString(String str){
	// if(isNull(str))
	// return null;
	// str = getSemiangle(str.trim());
	// Pattern pattern =
	// Pattern.compile("[\\.\\,\\|\\\"\\?\\!:'\\-_\\@\\#\\$\\%\\^\\&\\*\\(\\)\\{\\}\\[\\]]");//增加对应的标点
	// Matcher matcher = pattern.matcher(str);
	// String first = matcher.replaceAll("");
	// pattern = Pattern.compile(" {2,}");//去除多余空格
	// matcher = pattern.matcher(first);
	// String second = matcher.replaceAll(" ");
	// return second;
	// }

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
		// for(String s : strArr) {
		// list.add(s);
		// }

		// return list;
	}

	/**
	 * 将数组args合并成 '','',''的形式(一般为sql语句所用)
	 * 
	 * @param args
	 * @return
	 */
	public static String joinString(String[] args) {
		// return org.apache.commons.lang.StringUtils.join(args, ",");
		return null;
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
	 * 小写字母转换成大写字母。
	 * 
	 * @param 小写字母
	 * @return 大写字母
	 */
	public static char toUpperCase(char ch) {
		if (ch >= 'a' && ch <= 'z') {
			ch -= 32;
		}
		return ch;
	}

	/**
	 * 将给定字符串指定位置上的字母大写
	 * 
	 * @param source
	 *            String
	 * @param pos
	 *            int
	 * @return String
	 */
	public static String upperCharAt(final String source, int pos) {

		if (source == null) {
			return null;
		}
		int len = source.length();
		if (pos < 0 || pos >= len) {
			return "";
		}
		char[] chars = source.toCharArray();
		chars[pos] = toUpperCase(chars[pos]);
		return new String(chars);
	}

	/**
	 * 判断一个字符串中是否有汉字 判断依据： 如果字符串pValue中存在值大于256的字符就认为有汉字，否
	 * 
	 * 则就没有（非汉字数据的整数值不可能大于256的，因为他只有8位）
	 * 
	 * @return boolean
	 * @pre pValue != null
	 */
	public static boolean hasChinese(String pValue) {
		for (int i = 0; i < pValue.length(); i++) {
			if ((int) pValue.charAt(i) > 256)
				return true;
		}
		return false;
	}

	/**
	 * 判断一个字符串中是否有汉字 判断依据： 如果字符串pValue中存在值大于256的字符就认为有汉字，否
	 * 
	 * 则就没有（非汉字数据的整数值不可能大于256的，因为他只有8位）
	 * 
	 * @return boolean
	 * @pre pValue != null
	 */
	private static boolean isChinese(String pValue) {
		for (int i = 0; i < pValue.length(); i++) {
			if ((int) pValue.charAt(i) > 256)
				return true;
		}
		return false;
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
	 * 将字符串集合里的每一个字符串用连接符串起来。返回一个长串
	 * 
	 * 
	 * @param strs
	 *            字符串集合
	 * 
	 * @param split
	 *            连接符
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String join(List<String> strs, String split) {
		if (CollectionUtils.isEmpty(strs) || isEmpty(split)) {
			return "";
		}

		StringBuilder rs = new StringBuilder(strs.size() * 60);
		for (Iterator<String> itr = strs.iterator(); itr.hasNext();) {
			String str = itr.next();
			if (!isEmpty(str)) {
				if (itr.hasNext()) {
					rs.append(str).append(split);
				} else {
					rs.append(str);
				}
			}
		}

		return rs.toString();
	}

	/**
	 * 判断两个String集合是否拥有同样的元素
	 * 
	 * @param c1
	 * @param c2
	 * @return
	 */
	public static boolean hasSameElement(Collection<String> c1, Collection<String> c2) {

		if (CollectionUtils.isEmpty(c1) || CollectionUtils.isEmpty(c2)) {
			return false;
		}

		for (String s : c1) {
			if (c2.contains(s)) {
				return true;
			}
		}

		return false;
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
	 * 数组后面添加一个String
	 * <p>
	 * 在数组的后面添加一个元素
	 * </p>
	 * 
	 * @param <T>
	 * 
	 * @param srcarray
	 *            目标数组
	 * @param descStr
	 *            需要添加的字符串
	 * @return
	 */
	public static <T> T[] addStringToArray(T[] srcarray, T descStr) {
		T[] newArr = (T[]) new Object[srcarray.length + 1];
		System.arraycopy(srcarray, 0, newArr, 0, srcarray.length);
		newArr[srcarray.length] = descStr;
		return newArr;
	}

	/**
	 * iso 转为utf-8
	 * 
	 * @param name
	 * @return
	 */
	public static String iso2utf(String name) {
		try {
			return new String(name.getBytes(CharsetEncoding.ISO_8859_1), CharsetEncoding.UTF_8);
		} catch (Exception e) {
			return name;
		}
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
	 * 替换自定义的html
	 * 
	 * @param html
	 * @return
	 */
	public static String replaceMyHtml(String html) {
		if (StringUtils.isBlank(html)) {
			return "";
		}
		html = StringUtils.replaceHtml(html).replaceAll("&nbsp;", "");
		return html.replaceAll(";", "；");
	}

	/**
	 * 判断字符串是否是数字
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}
	
	public static void main(String[] args) {
		//System.out.println(isNumeric("123"));
	}

}
