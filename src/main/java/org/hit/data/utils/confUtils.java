package org.hit.data.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hit.data.table.Field;
import org.hit.data.table.Globals;
import org.hit.data.table.Table;
import org.hit.data.table.TableInfo;

import com.google.common.io.Resources;

public class confUtils {

	public static Map<String, TableInfo> getConfigByName(String fileName) {

		// List<Field> list = new ArrayList<Field>();
		List<TableInfo> l = new ArrayList<TableInfo>();
		Map<String, TableInfo> map = new HashMap<String, TableInfo>();
		SAXReader saxReader = new SAXReader();
		saxReader.setEncoding("UTF-8");
		Document document;
		try {
			// InputStream is =
			// this.getClass().getResourceAsStream("configuration.xml");
			URL resource = Resources.class.getClassLoader().getResource("configuration.xml");
			// URL url = new URL(path);
			URLConnection urlConnection = resource.openConnection();
			InputStream is = urlConnection.getInputStream();
			
			//InputStream is = new FileInputStream("E:\\configuration.xml");
			
			//FileInputStream fis=new FileInputStream("E:\\configuration.xml");
		 

			//InputStream iStream = new BufferedInputStream(in)
			//InputStream is=new BufferedInputStream(fis);
			

			document = saxReader.read(is);
			is.close();
		} catch (DocumentException e) {
			System.err.println("1");
			return null;

		} catch (IOException e) {
			System.err.println("2");
			return null;
		}

		// 开始解析
		Element root = document.getRootElement();

		// 获取文件列表
		List<Element> tableList = root.elements("table");

		for (Element element : tableList) {
			List<Field> list = new ArrayList<Field>();
			List<Element> propertyList = element.elements("property");
			//
			String tableName = ObjectUtils.checkStringIsNull(element.attributeValue("name"),
					"table" + 0);
			String rowCount = ObjectUtils.checkStringIsNull(element.attributeValue("rowCount"),
					"150000");

			
			// System.out.println(tableName);

			for (Element property : propertyList) {

//				Field temp = new Field();
//
//				temp.setName(property.attributeValue("name"));
//				temp.setType(property.attributeValue("type"));
//				// System.out.println(property.attributeValue("type").toUpperCase());
//				temp.setFieldType(FieldType.valueOf(property.attributeValue("type").toUpperCase()));
//				// temp.setLength(Integer.valueOf(property.attributeValue("length")));
				list.add(getFieldByElement(property,tableName));
			}

			Table table = new Table(list);

			TableInfo tableInfo = new TableInfo(tableName, 10, table);
			tableInfo.numTuples = Integer.valueOf(rowCount);
			// l.add(tableInfo);
			map.put(tableName, tableInfo);
		}

		return map;
	}

	public static void main(String[] args) {
		// List<String> wordList = txtTOList();
		// 获得src/main/resource
		URL resource = Resources.class.getClassLoader().getResource("configuration.xml");
		// File file = new File(url.getFile());
		confUtils confUtil = new confUtils();
		Map<String, TableInfo> map = confUtil.getConfigByName(resource.getFile().toString());

		// System.out.println(list.get(0));//

		// Map<Integer, List<String>> map = new HashMap<Integer,
		// List<String>>();

		for (Map.Entry<String, TableInfo> entry : map.entrySet()) {
			List<Field> list2 = entry.getValue().table.getFieldsList();
			System.out.println(entry.getValue().tableName);
			for (Field s : list2) {

				System.out.println(s.getName() + "\t" + s.getType() + "\t" + s.getLength());
			}
		}

	}

	public static Field getFieldByElement(Element element, String tableName) {

		// 分三种 int、string、自带元数据
		// private String name;// 列名
		// private String type;// 类型
		//
		// private FieldType fieldType;// 类型
		// private int length;// 长度
		// private int decimalPoint;// 保留小数点
		// private boolean isNull;// 是否为空
		// private boolean isPrimaryKey;// 是否为主键
		//
		// private boolean isForeignKey;// 是否为外键
		//
		// private int minValue;// 针对int，double类型
		// private int maxValue;// 针对int，double类型
		//
		// private boolean isSource;// 是否有属性值的来源
		// private String pathSource;// 路径
		// private boolean isRandom;// 是否随机
		// String name;// 列名
		// String type;// 类型
		// FieldType fieldType;// 类型
		int length;// 长度
		int decimalPoint;// 保留小数点
		boolean isNull;// 是否为空
		boolean isPrimaryKey;// 是否为主键
		boolean isForeignKey;// 是否为外键
		int minValue;// 针对int，double类型
		int maxValue;// 针对int，double类型
		boolean isSource;// 是否有属性值的来源
		String pathSource;// 路径
		boolean isRandom;// 是否随机

		String name = ObjectUtils.checkStringIsNull(element.attributeValue("name"), tableName);

		String type = ObjectUtils.checkStringIsNull(element.attributeValue("type"), "String");
		type=type.toUpperCase();

		if (type.equals("INTEGER") || type.equals("DOUBLE")) {
			// 是否是主键
			isNull = ObjectUtils.checkStringIsTrue(element.attributeValue("isNUll"), true);
			isPrimaryKey = ObjectUtils.checkStringIsTrue(element.attributeValue("isPrimaryKey"),
					false);
			isForeignKey = ObjectUtils.checkStringIsTrue(element.attributeValue("isForeignKey"),
					false);
			length = ObjectUtils.checkIntIsNUll(element.attributeValue("length"),
					Globals.con_length);
			decimalPoint = ObjectUtils.checkIntIsNUll(element.attributeValue("decimalPoint"), 0);
			minValue = ObjectUtils.checkIntIsNUll(element.attributeValue("minValue"),
					Globals.minValue);
			maxValue = ObjectUtils.checkIntIsNUll(element.attributeValue("maxValue"),
					Globals.maxValue);
			return new Field(name, type, length, decimalPoint, isNull, isPrimaryKey, isForeignKey,
					minValue, maxValue);
		} else if (type.equals("VARCHAR") || type.equals("STRING")) {
			length = ObjectUtils.checkIntIsNUll(element.attributeValue("length"),
					Globals.String_averageLength);
			return new Field(name, type, length);
		}
		return null;
		// return null;
	}

}
