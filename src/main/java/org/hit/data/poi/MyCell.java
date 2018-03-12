package org.hit.data.poi;

import org.apache.poi.ss.usermodel.CellStyle;

public class MyCell {

	private String cellName;		
	private CellStyle cellStyle;	
	private Integer cellType;		
	private Integer cellWidth;		
	private Integer cellHight;		
	private Object cellData;		
	private Integer row;		
	private Integer col;			
	private String headerName;		
	private String columnType;		
	
	public MyCell(){
	}
	
	//加载时需添加的元素
	public MyCell(int col,String headerName,String cellName,String columnType){
		this.col = col;
		this.headerName = headerName;
		this.cellName = cellName;
		this.columnType = columnType;
	}
	
	//写入时 设置单元格所需元素
	public MyCell(String cellName,CellStyle cellStyle,int cellType,int cellWidth,int cellHight){
		this.cellName = cellName;
		this.cellStyle = cellStyle;
		this.cellType = cellType;
		this.cellWidth = cellWidth;
		this.cellHight = cellHight;
	}
			
	public String getCellName() {
		return cellName;
	}
	public void setCellName(String cellName) {
		this.cellName = cellName;
	}
	public CellStyle getCellStyle() {
		return cellStyle;
	}
	public void setCellStyle(CellStyle cellStyle) {
		this.cellStyle = cellStyle;
	}
	public int getCellType() {
		return cellType;
	}
	public void setCellType(Integer cellType) {
		this.cellType = cellType;
	}
	public int getCellWidth() {
		return cellWidth;
	}
	public void setCellWidth(Integer cellWidth) {
		this.cellWidth = cellWidth;
	}
	public int getCellHight() {
		return cellHight;
	}
	public void setCellHight(Integer cellHight) {
		this.cellHight = cellHight;
	}
	public Object getCellData() {
		return cellData;
	}
	public void setCellData(Object cellData) {
		this.cellData = cellData;
	}
	public int getRow() {
		return row;
	}
	public void setRow(Integer row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(Integer col) {
		this.col = col;
	}
	public String getHeaderName() {
		return headerName;
	}
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	
	public void setMyCell(CellStyle cellStyle,int cellType,int cellWidth,int cellHight){
		this.cellStyle = cellStyle;
		this.cellType = cellType;
		this.cellWidth = cellWidth;
		this.cellHight = cellHight;
	}
}
