package beanmaker.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import beanmaker.bean.Bean;
import beanmaker.util.StringHelper;

public class Writer {
	static String path = "D:/general/";

	public static void writePOJO(List<Bean> beanList, String moneyType) throws Exception {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		String tableName = beanList.get(0).getTableName();
		String filePath = path + StringHelper.HumpToUnderlineUpperCase(tableName.toLowerCase()) + "_POJO.java";
		file = new File(filePath);
		OutputStream fos = new FileOutputStream(file);
		fos.write(makePOJOString(beanList, moneyType).getBytes());
		fos.flush();
		fos.close();
	}

	public static void writeVO(List<Bean> beanList, String moneyType) throws Exception {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		String tableName = beanList.get(0).getTableName();
		String filePath = path + StringHelper.getFirstUpper(StringHelper.UnderlineToHump(tableName.toLowerCase())) + "_VO.java";
		file = new File(filePath);
		OutputStream fos = new FileOutputStream(file);
		fos.write(makeVOString(beanList, moneyType).getBytes());
		fos.flush();
		fos.close();
	

	}
	

	private static String makePOJOString(List<Bean> beanList, String moneyType) {
		StringBuffer content = new StringBuffer();
		String tableName = beanList.get(0).getTableName().toLowerCase();
		content.append("public class " + StringHelper.HumpToUnderlineUpperCase(tableName) + " extends POJO {\n");
		if (beanList.size() > 0) {
			for (Bean bean : beanList) {
				content.append("\tprivate ");
				if (bean.getType().contains("TIMESTAMP")) {
					content.append("Timestamp ");
				}
				if (bean.getType().contains("DATE")) {
					content.append("Date ");
				}
				if (bean.getType().contains("VARCHAR")) {
					content.append("String ");
				}
				if (bean.getType().contains("NUMBER")) {
					if ("0".equals(bean.getScales()) || StringHelper.isBlank(bean.getScales())) {
						content.append("Integer ");
					} else {
						content.append(moneyType + " ");
					}
				}
				content.append(StringHelper.HumpToUnderlineUpperCase(bean.getName().toLowerCase()) + "; // " + bean.getComments() + "\n");
			}
			content.append("\n");
			for (Bean bean : beanList) {
				String dataType = "";
				if (bean.getType().contains("TIMESTAMP")) {
					dataType = "Timestamp";
				}
				if (bean.getType().contains("DATE")) {
					dataType = "Date";
				}
				if (bean.getType().contains("VARCHAR")) {
					dataType = "String";
				}
				if (bean.getType().contains("NUMBER")) {
					if ("0".equals(bean.getScales()) || StringHelper.isBlank(bean.getScales())) {
						dataType = "Integer";
					} else {
						dataType = moneyType;
					}
				}
				content.append("\tpublic void set").append(bean.getName()).append("(").append(dataType).append(" "+bean.getName()).append(") {\n");
				content.append("\t\tthis.").append(bean.getName()).append(" = ").append(bean.getName()).append(";\n");
				content.append("\t}\n");
				content.append("\tpublic ").append(dataType).append(" get").append(bean.getName()).append("() {\n");
				content.append("\t\treturn this.").append(bean.getName()).append(";\n");
				content.append("\t}\n");
			}
		}
		content.append("}");
		return content.toString();
	}
	
	private static String makeVOString(List<Bean> beanList, String moneyType) {
		StringBuffer content = new StringBuffer();
		String tableName = beanList.get(0).getTableName();
		content.append("public class " + StringHelper.getFirstUpper(StringHelper.UnderlineToHump(tableName)) + " implements Serializable {\n");
		content.append("\tprivate static final long serialVersionUID = 1L;\n");
		if (beanList.size() > 0) {
			for (Bean bean : beanList) {
				content.append("\tprivate ");
				if (bean.getType().contains("TIMESTAMP")) {
					content.append("Timestamp ");
				}
				if (bean.getType().contains("DATE")) {
					content.append("Date ");
				}
				if (bean.getType().contains("VARCHAR")) {
					content.append("String ");
				}
				if (bean.getType().contains("NUMBER")) {
					if ("0".equals(bean.getScales()) || StringHelper.isBlank(bean.getScales())) {
						content.append("Integer ");
					} else {
						content.append(moneyType + " ");
					}
				}
				content.append(StringHelper.UnderlineToHump(bean.getName()) + "; // " + bean.getComments() + "\n");
			}
			content.append("\n");
			for (Bean bean : beanList) {
				String dataType = "";
				if (bean.getType().contains("TIMESTAMP")) {
					dataType = "Timestamp";
				}
				if (bean.getType().contains("DATE")) {
					dataType = "Date";
				}
				if (bean.getType().contains("VARCHAR")) {
					dataType = "String";
				}
				if (bean.getType().contains("NUMBER")) {
					if ("0".equals(bean.getScales()) || StringHelper.isBlank(bean.getScales())) {
						dataType = "Integer";
					} else {
						dataType = moneyType;
					}
				}
				content.append("\tpublic void set").append(StringHelper.getFirstUpper(StringHelper.UnderlineToHump(bean.getName().toLowerCase()))).append("(").append(dataType).append(" "+StringHelper.UnderlineToHump(bean.getName().toLowerCase())).append(") {\n");
				content.append("\t\tthis.").append(StringHelper.UnderlineToHump(bean.getName().toLowerCase())).append(" = ").append(StringHelper.UnderlineToHump(bean.getName().toLowerCase())).append(";\n");
				content.append("\t}\n");
				content.append("\tpublic ").append(dataType).append(" get").append(StringHelper.getFirstUpper(StringHelper.UnderlineToHump(bean.getName().toLowerCase()))).append("() {\n");
				content.append("\t\treturn this.").append(StringHelper.UnderlineToHump(bean.getName().toLowerCase())).append(";\n");
				content.append("\t}\n");
			}
		}
		content.append("}");
		return content.toString();
	}
	
}
