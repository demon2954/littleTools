package beanmaker.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import beanmaker.bean.Bean;

public class DAO {
	static BasicDataSource ds;
	// 初始化
	static {
		ds = new BasicDataSource();
		ds.setDriverClassName("xxxx");
		ds.setUsername("xxxx");
		ds.setPassword("xxxx");
		ds.setUrl("xxxx");
	}

	public static List<Bean> query(String tableName) throws SQLException {
		List<Bean> beanList = null;
		Connection conn = ds.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ").append("A.table_name as table_name, ").append("A .column_name AS NAME, ").append("A .data_type AS TYPE, ").append("A .data_precision AS PECISION, ")
				.append("A .data_scale AS SCALES, ").append("b.comments AS comments ").append("FROM ").append("( ").append("SELECT ").append("table_name, ").append("column_name, ")
				.append("data_type, ").append("data_precision, ").append("data_scale ").append("FROM ").append("all_tab_columns ").append("WHERE ").append("table_name = ? ").append(") A ")
				.append("LEFT JOIN ( ").append("SELECT ").append("column_name, ").append("comments ").append("FROM ").append("user_col_comments ").append("WHERE ").append("table_name = ? ")
				.append(") b ON b.column_name = A .column_name ");
		List<Map<String, Object>> data = new QueryRunner().query(conn, sql.toString(), new MapListHandler(), tableName.toUpperCase(), tableName.toUpperCase());
		if (data != null && data.size() > 0) {
			beanList = new ArrayList<Bean>();
			for (Map<String, Object> one : data) {
				Bean bean = new Bean();
				bean.setTableName(one.get("TABLE_NAME") + "");
				bean.setComments(one.get("COMMENTS") + "");
				bean.setName(one.get("NAME") + "");
				bean.setType(one.get("TYPE") + "");
				bean.setPecision(one.get("PECISION") + "");
				bean.setScales(one.get("SCALES") + "");
				beanList.add(bean);
			}
		}
		return beanList;
	}
	public static void main(String[] args) throws Exception {
		query("CASH_BOX_RECORD");
	}
}
