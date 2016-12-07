package com;

public class ToUperNum {
	final static String[] LEVEL = { "��", "��", "��" };
	final static String[] BYTE = { "ǧ", "��", "ʮ" };
	final static String[] NUMBER = { "��", "Ҽ", "��", "��", "��", "��", "½", "��", "��", "��" };

	public static void main(String[] args) {
		System.out.println(uperNum("310010001010010"));
	}

	public static <E> String uperNum(E number) {
		String result = "";
		String str = String.valueOf(number);
		String last = "";
		if (str.indexOf(".") != -1) { // ��С����
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
			while (result.charAt(result.length()-1) == '��') {
				result = result.substring(0, result.length()-1);
			}
			if (result.lastIndexOf("������") != -1) {
				result = result.replace("������", "��");
			}
			if (result.lastIndexOf("����") != -1) {
				result = result.replace("����", "��");
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
		return "��" + result;
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
		str = str.replaceAll("[��]{2,}", "��");
		if (str.contains("��ǧ")) {
			str = str.replace("��ǧ", "��");
		}
		if (str.contains("���")) {
			str = str.replace("���", "��");
		}
		if (str.contains("��ʮ")) {
			str = str.replace("��ʮ", "��");
		}
		str = str.replaceAll("[��]{2,}", "��");
		if (str.contains("����")) {
			str = str.replace("����", "��");
		}
		if (str.contains("����")) {
			str = str.replace("����", "��");
		}
		if (str.contains("����")) {
			str = str.replace("����", "��");
		}
		return str;
	}
}
