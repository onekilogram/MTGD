package org.hit.data.test;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName FloatTest
 * @Description Float测试
 * @author kg
 * @date 2017年12月10日 下午4:24:40
 */
public class FloatTest {

	public static void main(String[] args) {
		//
		System.out.println("0");
		InetSocketAddress favoredNode1 = new InetSocketAddress("strategy_1", 50010);
		InetSocketAddress favoredNode = new InetSocketAddress("localhost", 50010);
		System.out.println("1");

		List<InetSocketAddress> list = new ArrayList<InetSocketAddress>();
		
		list.add(favoredNode1);
		list.add(favoredNode);
		 
		list.toArray(
                new InetSocketAddress[list.size()]);
		
		
		String strategy = favoredNode1.getHostName();

		System.out.println("2");
		String string = "strategy_1";

		System.out.println(string.contains("strategy"));

		String strategy2 = favoredNode1.getHostName();
		
	    System.out.println(strategy2.substring(strategy2.indexOf('_')+1,strategy2.length()));
	    
	    ArrayList<String> list1 = new ArrayList<String>();  
	    
	    list1.add("aaa");  
	      
	    list1.add("bbb");  
	      
	    //转换成数组  
	    String[] strArrayTrue = (String[]) list1.toArray(new String[0]);
	    
	    for(String string2 : strArrayTrue){
	    	System.out.println(string2);
	    }

		int SCALE_BASE = 150_000;
		System.out.println(SCALE_BASE);

		Random random = new Random();

		System.out.println(random.nextDouble());

		// int length =
	}

	public static double getNextDouble(Double min, Double max) {

		Random random = new Random();

		return min + (max - min) * random.nextDouble();
	}
}
