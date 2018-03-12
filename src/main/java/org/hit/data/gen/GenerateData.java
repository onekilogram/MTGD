package org.hit.data.gen;

import java.util.Map;

import org.hit.data.table.Globals;
import org.hit.data.table.TableInfo;
import org.hit.data.utils.confUtils;

public class GenerateData {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		// 选择配置文件

		java.util.Scanner scanner = new java.util.Scanner(System.in);

		System.out.println("=====================================");
		System.out.println("=====================================");
		System.out.println("请输入文件路径：（空代表默认）\n>>>");
		String path = scanner.nextLine();
		if (path == null || path.length() == 0) {
			path = "configuration.xml";
		}
		System.out.println(path);
		try {
			Globals.tableInfoMap = confUtils.getConfigByName(path);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("配置文件有问题！");
		}
		// 解析TableInfos

		long startTime = System.currentTimeMillis();// 记录开始时间

		for (Map.Entry<String, TableInfo> entry : Globals.tableInfoMap.entrySet()) {

			Thread thread = new Thread(new ExecutorGen(entry.getValue()));// 多线程实现多表的生成
			thread.start();
			thread.join();// 先让子线程开始
		}

		long endTime = System.currentTimeMillis();// 记录结束时间

		float excTime = (float) (endTime - startTime) / 1000;

		System.out.println("执行时间：" + excTime + "s");

	}

}
