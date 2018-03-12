package org.hit.data.poi;
public interface IRowReader {  
    
	/**
	 * @param sheetIndex
	 * @param sheetname
	 * @param rowIndex
	 * @param row
	 * @param currSheet  
	 * @return
	 */
    public void processLine(int Index, String name, int rowIndex, String row,MySheet currSheet);
    
    public boolean isForcedInterrupt();
}
