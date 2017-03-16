package beanmaker.bean;

public class Bean {
	private String tableName;
	private String name;
	private String type;
	private String scales;
	private String pecision;
	private String comments;
	
	public String getScales() {
		return scales;
	}
	public void setScales(String scales) {
		this.scales = scales;
	}
	public String getPecision() {
		return pecision;
	}
	public void setPecision(String pecision) {
		this.pecision = pecision;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

}
