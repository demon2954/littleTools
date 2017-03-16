package beanmaker.util;

import java.util.UUID;

public class StringHelper {

	/**
	 * 首字母小写
	 * 
	 * @param name
	 * @return
	 */
	public static String getFirstLow(String name) {
		String tem = name.substring(1);
		String first = name.substring(0, 1);
		return first.toLowerCase() + tem;
	}

	/**
	 * 首字母大写
	 * 
	 * @param name
	 * @return
	 */
	public static String getFirstUpper(String name) {
		String tem = name.substring(1);
		String first = name.substring(0, 1);
		return first.toUpperCase() + tem;
	}

	/**
	 * 将驼峰式转化为带下划线的大写模式
	 * 
	 * @param name
	 * @return
	 */
	public static String HumpToUnderlineUpperCase(String name) {
		name = getFirstLow(name);
		StringBuffer sb = new StringBuffer();
		char[] chars = name.toCharArray();
		for (char c : chars) {
			if (Character.isUpperCase(c)) {
				sb.append("_");
			}
			sb.append(c);
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 将驼峰式转化为带下划线的小写模式
	 * 
	 * @param name
	 * @return
	 */
	public static String HumpToUnderlineLowerCase(String name) {
		name = getFirstLow(name);
		StringBuffer sb = new StringBuffer();
		char[] chars = name.toCharArray();
		for (char c : chars) {
			if (Character.isUpperCase(c)) {
				sb.append("_");
			}
			sb.append(c);
		}
		return sb.toString().toLowerCase();
	}

	/**
	 * 带下划线的字符串转化为驼峰式
	 * 
	 * @param name
	 * @return
	 */
	public static String UnderlineToHump(String name) {
		name = name.toLowerCase();
		StringBuffer sb = new StringBuffer();
		char[] chars = name.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			char c = ' ';
			if (Character.compare(chars[i], '_') == 0) { // 若是下划线
				c = Character.toUpperCase(chars[i + 1]);
				i++;
			} else {
				c = chars[i];
			}
			sb.append(c);
		}
		return sb.toString();
	}

	public static boolean isEmpty(String value) {
		return null == value || value.length() == 0 || value.equalsIgnoreCase("null");
	}

	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}

	public static boolean isBlank(String value) {
		return null == value || value.trim().length() == 0 || value.equalsIgnoreCase("null");
	}

	public static boolean isNotBlank(String value) {
		return !isBlank(value);
	}
	
	public static String getUUID(){
		 UUID uuid = UUID.randomUUID();
		 return uuid.toString();
	}
	
	public static String UUID(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	/**
	 * 科学计数法换成字符串
	 * @param science
	 * @return
	 */
	public static String scienceToString(String science) {
		if(isBlank(science) || "null".equalsIgnoreCase(science)){
			return "";
		}
		science = science.toUpperCase();
		if (!science.contains("E")) {
			return science;
		}
		String[] parts = science.split("E");
		int numLen = parts[0].substring(parts[0].indexOf(".") + 1).length();
		int dotLen = Integer.valueOf(parts[1]);
		parts[0] = parts[0].replace(".", "");
		if (dotLen >= numLen) {
			for (int i = 0; i < dotLen - numLen; i++) {
				parts[0] += "0";
			}
		} else {
			int dotPlace = numLen - dotLen;
			int dotPlaces = parts[0].length() - dotPlace;
			parts[0] = parts[0].substring(0, dotPlaces) + "." + parts[0].substring(dotPlace + 1);
		}
		return parts[0];
	}
	
	/**
	 * 给苹果转json串
	 * @param src
	 * @return
	 */
	public static String transJsonForIOS(String src) {
		StringBuffer sb = new StringBuffer();
		src = src.replace(" ", "");
		String[] parts = src.split("=");
		int last = parts.length;
		sb.append("{\"").append(parts[0].substring(1)).append("\":");
		for (int i = 1; i < last - 1; i++) {
			int lastComma = parts[i].lastIndexOf(",");// 最后一个逗号的位置
			String value = parts[i].substring(0, lastComma);
			String key = parts[i].substring(lastComma + 1);
			sb.append("\"").append(value).append("\"").append(",").append("\"").append(key).append("\":");
		}
		sb.append("\"").append(parts[last - 1].substring(0, parts[last - 1].length() - 1)).append("\"}");
		return sb.toString();
	}
	/**
	 * 判断是否是数字
	 * @param src
	 * @return
	 */
	public static boolean isNum(String src){
		if(null==src)
			return false;
		try {
			Integer.parseInt(src);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public static void main(String[] args) {
		String str = "{a=sdf,b=sdfs,c=k,d,f,d=sddf}";
		System.out.println(transJsonForIOS(str));
	}
}
