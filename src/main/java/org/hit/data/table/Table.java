package org.hit.data.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Table
 * @Description 类似于数据库中的表
 * @author kg
 * @date 2017年12月27日 上午8:48:03
 */
public class Table {
	private Field[] fields;
	private List<Field> fieldsList;

	private List<ForeignKey> foreignKeyList = new ArrayList<ForeignKey>();
	private List<PrimaryKey> primaryKeyList = new ArrayList<PrimaryKey>();

	public Table(Field[] fields) {
		this.fields = fields;
		fieldsList = new ArrayList<>();
		this.fieldsList = Arrays.asList(fields);
	}

	public Table(List<Field> fieldsList) {
		this.fieldsList = fieldsList;
		this.fields = new Field[fieldsList.size()];
		fieldsList.toArray(this.fields);
	}

	public Table(Field[] fields, List<Field> fieldsList) {
		this.fields = fields;

		this.fieldsList = fieldsList;
	}

	public Field[] getFields() {
		return fields;
	}

	public void setFields(Field[] fields) {
		this.fields = fields;
	}

	public List<Field> getFieldsList() {
		return fieldsList;
	}

	public void setFieldsList(List<Field> fieldsList) {
		this.fieldsList = fieldsList;
	}

	public List<ForeignKey> getForeignKeyList() {
		return foreignKeyList;
	}

	public void setForeignKeyList(List<ForeignKey> foreignKeyList) {
		this.foreignKeyList = foreignKeyList;
	}

	public List<PrimaryKey> getPrimaryKeyList() {
		return primaryKeyList;
	}

	public void setPrimaryKeyList(List<PrimaryKey> primaryKeyList) {
		this.primaryKeyList = primaryKeyList;
	}

	public void addPrimaryKey(PrimaryKey primaryKey) {
		this.primaryKeyList.add(primaryKey);
	}

	public void addForeignKey(ForeignKey foreignKey) {
		this.foreignKeyList.add(foreignKey);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
