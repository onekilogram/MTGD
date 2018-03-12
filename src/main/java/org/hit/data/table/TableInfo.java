package org.hit.data.table;

/**
 * @ClassName TableInfo
 * @Description 对table的详细介绍
 * @author kg
 */
public class TableInfo {

	// Name of table.
	public String tableName;

	// Total number of tuples in dataset.
	public double numTuples;

	// TPC-H generated files use '|'. CSV uses ','.
	public char delimiter;

	// Schema of data set.
	public Table table;

	public TableInfo(String tableName) {
		this(tableName, 100, '|', null);
	}

	public TableInfo(String tableName, double numTuples, Table table) {
		this(tableName, numTuples, '|', table);
	}

	public TableInfo(String tableName, double numTuples, char delimiter, Table table) {
		this.tableName = tableName;
		this.numTuples = numTuples;
		this.delimiter = delimiter;
		this.table = table;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int count = 9;
		if (count % 2 != 0) {
			System.out.println("====");
		}
		if ((count & 1) != 0) {
			System.out.println("====");
		}

	}

}
