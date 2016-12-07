package com;

public class ToUperNum {
	final static String[] LEVEL = { "万", "亿", "兆" };
	final static String[] BYTE = { "千", "百", "十" };
	final static String[] NUMBER = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "漆", "捌", "玖" };

	public static void main(String[] args) {
		System.out.println(uperNum("310010001010010"));
	}

	public static <E> String uperNum(E number) {
		String result = "";
		String str = String.valueOf(number);
		String last = "";
		if (str.indexOf(".") != -1) { // 含小数点
			last = uperDon(str.substring(str.indexOf(".") + 1));
			str = str.substring(0, str.indexOf("."));
		}
		int length = str.length();
		int lest = length % 4;
		int groupCount = lest == 0 ? length / 4 : length / 4 + 1;
		String[] group = new String[groupCount];
		String[] resultGroup = new String[groupCount];
		for (int i = 0; i < groupCount; i++) {
			String lev = "";
			if (groupCount - i == 4) {
				lev = LEVEL[2];
			}
			if (groupCount - i == 3) {
				lev = LEVEL[1];
			}
			if (groupCount - i == 2) {
				lev = LEVEL[0];
			}
			if (lest == 0) {
				group[i] = str.substring(i * 4, i * 4 + 4);
			} else {
				if (i == 0) {
					group[i] = str.substring(0, lest);
				} else {
					group[i] = str.substring(i * 4 - 4 + lest, i * 4 + lest);
				}
			}
			resultGroup[i] = uper(group[i]) + lev;
			resultGroup[i] = delZero(resultGroup[i]);
			result += resultGroup[i];
			resultGroup[i] = delZero(resultGroup[i]);
			while (result.charAt(result.length()-1) == '零') {
				result = result.substring(0, result.length()-1);
			}
			if (result.lastIndexOf("兆亿万") != -1) {
				result = result.replace("兆亿万", "兆");
			}
			if (result.lastIndexOf("亿万") != -1) {
				result = result.replace("亿万", "亿");
			}
		}

		return result + last;
	}

	public static String uperDon(String last) {
		if (last == null) {
			return "";
		}
		String result = "";
		int length = last.length();
		for (int i = 0; i < length; i++) {
			switch (last.charAt(i)) {
			case ('0'):
				result += NUMBER[0];
				break;
			case ('1'):
				result += NUMBER[1];
				break;
			case ('2'):
				result += NUMBER[2];
				break;
			case ('3'):
				result += NUMBER[3];
				break;
			case ('4'):
				result += NUMBER[4];
				break;
			case ('5'):
				result += NUMBER[5];
				break;
			case ('6'):
				result += NUMBER[6];
				break;
			case ('7'):
				result += NUMBER[7];
				break;
			case ('8'):
				result += NUMBER[8];
				break;
			case ('9'):
				result += NUMBER[9];
				break;
			}
		}
		return "点" + result;
	}

	public static String uper(String str) {
		if (str == null) {
			return "";
		}
		String result = "";
		int length = str.length();
		for (int i = 0; i < length; i++) {
			switch (str.charAt(i)) {
			case ('0'):
				result += NUMBER[0];
				break;
			case ('1'):
				result += NUMBER[1];
				break;
			case ('2'):
				result += NUMBER[2];
				break;
			case ('3'):
				result += NUMBER[3];
				break;
			case ('4'):
				result += NUMBER[4];
				break;
			case ('5'):
				result += NUMBER[5];
				break;
			case ('6'):
				result += NUMBER[6];
				break;
			case ('7'):
				result += NUMBER[7];
				break;
			case ('8'):
				result += NUMBER[8];
				break;
			case ('9'):
				result += NUMBER[9];
				break;
			}
			if (length == 4 && i != 3) {
				result += BYTE[i];
			} else if (length != 4) {
				String bytee = "";
				switch (length) {
				case 1:
					break;
				case 2:
					if (i + 2 <= 2) {
						bytee = BYTE[i + 2];
					}
					result += bytee;
					break;
				case 3:
					if (i + 1 <= 2) {
						bytee = BYTE[i + 1];
					}
					result += bytee;
					break;
				}
			}
		}
		return result;
	}
	
	public static String delZero(String str) {
		str = str.replaceAll("[零]{2,}", "零");
		if (str.contains("零千")) {
			str = str.replace("零千", "零");
		}
		if (str.contains("零百")) {
			str = str.replace("零百", "零");
		}
		if (str.contains("零十")) {
			str = str.replace("零十", "零");
		}
		str = str.replaceAll("[零]{2,}", "零");
		if (str.contains("零兆")) {
			str = str.replace("零兆", "兆");
		}
		if (str.contains("零亿")) {
			str = str.replace("零亿", "亿");
		}
		if (str.contains("零万")) {
			str = str.replace("零万", "万");
		}
		return str;
	}
}
