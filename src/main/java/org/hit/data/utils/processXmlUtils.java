package org.hit.data.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hit.data.poi.MyCell;
import org.hit.data.poi.MySheet;

/**
 * @ClassName processXmlUtils
 * @Description TODO xml数据转成类信息
 * @author kg
 * @date 2017年12月1日 下午6:53:35
 */
public class processXmlUtils {

	private static Log log = LogFactory.getLog("service.log");
	
	private Document document;
	private String cfgPath;

	public processXmlUtils(){
		this.cfgPath = "/config.xml";
	}

	public processXmlUtils(String cfgPath){
		if(!ComUtil.isNull(cfgPath)){
			this.cfgPath = cfgPath;
		}else{
			this.cfgPath = "/config.xml";
		}
	}

	// 根据文件名获取对应文件中的excel配置解析模版
	// 返回该文件下表的所有信息
	@SuppressWarnings("unchecked")
	public Map<String, MySheet> getConfigByName(String fileName) {
		Map<String, MySheet> sheetMap = new HashMap<String, MySheet>();

		SAXReader saxReader = new SAXReader();
		saxReader.setEncoding("UTF-8");
		try {
			InputStream is = this.getClass().getResourceAsStream(cfgPath);
			document = saxReader.read(is);
			is.close();
		} catch (DocumentException e) {
			log.error("加载xml配置文件" + cfgPath + "失败：", e);
			return null;
		} catch (IOException e) {
			log.error("打开xml配置文件" + cfgPath + "失败：", e);
			return null;
		}

		// 开始解析
		Element root = document.getRootElement();
		// 获取文件列表
		List<Element> fileList = root.elements("file");
		for (Element file : fileList) {
			if (file.attributeValue("name").equals(fileName)) { // 这一个直接过滤很多目录，必须和cfgPath配置文件的name一致
				List<Element> sheetList = file.elements("sheet");
				for (Element sheet : sheetList) {
					MySheet sheetInfo = new MySheet();
					List<Element> cellList = sheet.elements("cell");
					Map<String, MyCell> cellMap = new HashMap<String, MyCell>();
					for (Element cell : cellList) {
						Element column = cell.element("column");
						MyCell cellInfo = new MyCell();
						cellInfo.setHeaderName(cell.attributeValue("name"));
						cellInfo.setCellName(column.attributeValue("name"));
						try {
							cellInfo.setCol(Integer.parseInt(cell.attributeValue("colnum")));
						} catch (Exception e) {
							cellInfo.setCol(-1);
						}
						cellInfo.setColumnType(column.attributeValue("datatype"));

						cellMap.put(cell.attributeValue("colnum"), cellInfo);
					}
					sheetInfo.setSheetName(sheet.attributeValue("name"));
					try {
						sheetInfo.setSheetIndex(Integer.parseInt(sheet.attributeValue("index")));
					} catch (Exception e) {
						sheetInfo.setSheetIndex(0);
					}
					try {
						sheetInfo.setSkipCol(Integer.parseInt(sheet.attributeValue("skipcol")));
					} catch (Exception e) {
						sheetInfo.setSkipCol(0);
					}
					try {
						sheetInfo.setSkipRow(Integer.parseInt(sheet.attributeValue("skiprow")));
					} catch (Exception e) {
						sheetInfo.setSkipRow(1);
					}
					sheetInfo.setIsUsed(sheet.attributeValue("isused"));
					sheetInfo.setTableName(sheet.attributeValue("table"));
					sheetInfo.setCell(new HashMap<String, MyCell>(cellMap));
					sheetMap.put(sheet.attributeValue("index"), sheetInfo);
				}
			}
		}
		return sheetMap;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
