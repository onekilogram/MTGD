package org.hit.data.poi;

import java.util.HashMap;
import java.util.Map;

public class MySheet {

	private String sheetName;		
	private String tableName;		
	private int sheetIndex;			
	private int skipRow;			
	private int skipCol;			
	private String isUsed;			
	private Map<String,MyCell> cell;
	
	public MySheet(){
		this.sheetIndex = 0;
		this.skipRow = 0;
		this.skipCol = 0;
		this.isUsed = "1";
		this.cell = new HashMap<String,MyCell>();
	}
	
	public MySheet(String sheetName,int sheetIndex,int skipRow){
		this.sheetName = sheetName;
		this.sheetIndex = sheetIndex;
		this.skipRow = skipRow;
		this.skipCol = 0;
		this.isUsed = "1";
		this.cell = new HashMap<String,MyCell>();
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public int getSheetIndex() {
		return sheetIndex;
	}

	public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}

	public int getSkipRow() {
		return skipRow;
	}

	public void setSkipRow(int skipRow) {
		this.skipRow = skipRow;
	}

	public int getSkipCol() {
		return skipCol;
	}

	public void setSkipCol(int skipCol) {
		this.skipCol = skipCol;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	public Map<String, MyCell> getCell() {
		return cell;
	}

	public void setCell(Map<String, MyCell> cell) {
		this.cell = new HashMap<String,MyCell>(cell);
	}
	
}
