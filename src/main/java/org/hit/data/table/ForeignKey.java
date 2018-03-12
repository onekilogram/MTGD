package org.hit.data.table;

/**
 * @ClassName ForeignKey
 * @Description 外键的信息
 * @author kg
 * @date 2017年12月29日 下午5:03:34
 */
public class ForeignKey {

	private String tableName0;// 原表名称
	private String field0;// 原表属性
	private String tableName1;// 当前表名
	private String field1;// 当前属性

	public String getTableName0() {
		return tableName0;
	}

	public void setTableName0(String tableName0) {
		this.tableName0 = tableName0;
	}

	public String getField0() {
		return field0;
	}

	public void setField0(String field0) {
		this.field0 = field0;
	}

	public String getTableName1() {
		return tableName1;
	}

	public void setTableName1(String tableName1) {
		this.tableName1 = tableName1;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

}
