package org.hit.data.table;

/**
 * @ClassName TableProperty
 * @Description 表的属性 ,仿照mysql数据库
 * @author kg
 */

public class Field {

	private String name;// 列名
	private String type;// 类型

	private FieldType fieldType;// 类型
	private int length;// 长度
	private int decimalPoint;// 保留小数点
	private boolean isNull;// 是否为空
	private boolean isPrimaryKey;// 是否为主键

	private boolean isForeignKey;// 是否为外键
    
	
	private int minValue;// 针对int，double类型
	private int maxValue;// 针对int，double类型

	private boolean isSource;// 是否有属性值的来源
	private String pathSource;// 路径
	private boolean isRandom;// 是否随机

	// 是为了String类型的
	public Field(String name, String type) {// name="" ,type="String"
		this(name, type, Globals.String_averageLength);
	}

	public Field(String name, String type, int legnth) {// name=""
														// ,type="String"
		this.name = name;
		this.type = type;
		this.fieldType = FieldType.valueOf(type);
		this.length = legnth;
	}
	// 是为了int

	public Field(String name, String type, int minValue, int maxValue, boolean isPrimaryKey,
			Boolean isForeignKey,int length,int decimalPoint) {// name="" ,type="String"
		// this(name,type,Globals.String_averageLength);
	}
	
	

	public Field(String name, String type, int length, int decimalPoint, boolean isPrimaryKey,
			boolean isForeignKey, int minValue, int maxValue) {
		super();
		this.name = name;
		this.type = type;
		this.fieldType = FieldType.valueOf(type);
		this.length = length;
		this.decimalPoint = decimalPoint;
		this.isPrimaryKey = isPrimaryKey;
		this.isForeignKey = isForeignKey;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	public Field(String name, String type, int length, int decimalPoint,
			boolean isNull, boolean isPrimaryKey, boolean isForeignKey, int minValue,
			int maxValue) {
		super();
		this.name = name;
		this.type = type;
		this.fieldType = FieldType.valueOf(type);
		this.length = length;
		this.decimalPoint = decimalPoint;
		this.isNull = isNull;
		this.isPrimaryKey = isPrimaryKey;
		this.isForeignKey = isForeignKey;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	public int getMinValue() {
		return minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
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

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getDecimalPoint() {
		return decimalPoint;
	}

	public void setDecimalPoint(int decimalPoint) {
		this.decimalPoint = decimalPoint;
	}

	public boolean isNull() {
		return isNull;
	}

	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}

	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}

	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	public boolean isForeignKey() {
		return isForeignKey;
	}

	public void setForeignKey(boolean isForeignKey) {
		this.isForeignKey = isForeignKey;
	}

	public FieldType getFieldType() {
		return fieldType;
	}

	public void setFieldType(FieldType fieldType) {
		this.fieldType = fieldType;
	}

	public boolean isSource() {
		return isSource;
	}

	public void setSource(boolean isSource) {
		this.isSource = isSource;
	}

	public String getPathSource() {
		return pathSource;
	}

	public void setPathSource(String pathSource) {
		this.pathSource = pathSource;
	}

	public boolean isRandom() {
		return isRandom;
	}

	public void setRandom(boolean isRandom) {
		this.isRandom = isRandom;
	}

	@Override
	public String toString() {
		return "Field [name=" + name + ", type=" + type + ", fieldType=" + fieldType + ", length="
				+ length + ", decimalPoint=" + decimalPoint + ", isNull=" + isNull
				+ ", isPrimaryKey=" + isPrimaryKey + ", isForeignKey=" + isForeignKey + "]";
	}
}
