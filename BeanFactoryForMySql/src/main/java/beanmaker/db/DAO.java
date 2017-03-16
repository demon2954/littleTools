package beanmaker.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import beanmaker.bean.Bean;

public class DAO {
	static BasicDataSource ds;
	// 初始化
	static {
		ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUsername("wheel");
		ds.setPassword("wheel_data");
		ds.setUrl("jdbc:mysql://192.168.5.173:3306/dev_wheel_data?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull");
	}

	public static List<Bean> query(String tableName) throws SQLException {
		List<Bean> beanList = null;
		Connection conn = ds.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("desc ").append(tableName);
		List<Map<String, Object>> data = new QueryRunner().query(conn, sql.toString(), new MapListHandler());
		Map<String, String> typeMap = new HashMap<String, String>();
		Map<String, String> commentMap = new HashMap<String, String>();
		if (data != null && data.size() > 0) {
			for (Map<String, Object> one : data) {
				String field = one.get("Field") + "";
				String type = one.get("Type") + "";
				typeMap.put(field.toUpperCase(), type.toUpperCase());
			}
		}
		sql.setLength(0);
		sql.append("SELECT COLUMN_NAME as name, column_comment as comment FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = '" + tableName + "' AND table_schema = 'dev_wheel_data'");
		data = new QueryRunner().query(conn, sql.toString(), new MapListHandler());
		if (data != null && data.size() > 0) {
			for (Map<String, Object> one : data) {
				String name = one.get("name") + "";
				String comment = one.get("comment") + "";
				commentMap.put(name.toUpperCase(), comment);
			}
		}
		Set<String> keys = typeMap.keySet();
		if (keys != null && keys.size() > 0) {
			beanList = new ArrayList<Bean>();
			for (String key : keys) {
				Bean bean = new Bean();
				bean.setComments(commentMap.get(key));
				bean.setName(key);
				bean.setTableName(tableName.toUpperCase());
				if (!typeMap.get(key).contains(",0")) {
					bean.setScales("3");
				}
				bean.setType(typeMap.get(key));
				beanList.add(bean);
			}
		}
		return beanList;
	}
	public static void main(String[] args) throws Exception {
		query("CASH_BOX_RECORD");
	}
}
