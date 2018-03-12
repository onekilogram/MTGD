package org.hit.tpch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

public class TimeTest {

	public static void main(String[] args) throws IOException {
		
		
		long startTime1 = System.currentTimeMillis();    //获取开始时间


		long endTime1 = System.currentTimeMillis();    //获取结束时间

		System.out.println("程序运行时间：" + (endTime1 - startTime1) + "ms");    //输出程序运行时间
		
		
		long startTime=System.nanoTime();   //获取开始时间  

		
		System.out.println(System.currentTimeMillis());
		System.out.println(System.nanoTime());
		
		RandomInt randomInt = new RandomInt(System.nanoTime(), 4);
		
		File f=new File("out2.txt");  
        f.createNewFile();  
        FileOutputStream fileOutputStream = new FileOutputStream(f);  
        PrintStream printStream = new PrintStream(fileOutputStream);  
        System.setOut(printStream);  
        
		
		int []count = new int[10];
		
		for(int i=0;i<100;i++){
			int c = randomInt.nextInt(0,9);
			System.out.println(c);
			count[c]++;
			randomInt.rowFinished();
		}
		for(int i =0;i<10;i++){
			System.out.println("count["+i+"] = "+count[i]);
		}
		
		
//		for(int i=0;i<100000000;i++){
//			//System.out.println(randomInt.nextInt(2,100));
//			randomInt.nextInt(2,100);
//			randomInt.rowFinished();
//			j++;
//		}
		

		long endTime=System.nanoTime(); //获取结束时间  

		System.out.println("程序运行时间： "+(endTime-startTime)+"ns"); 
		
		startTime=System.nanoTime();   //获取开始时间  
		
		Random random = new Random();
		
//		for(int i=0;i<100000000;i++){
//			//System.out.println(randomInt.nextInt(2,100));
//			random.nextInt(10000);
//			//randomInt.rowFinished();
//		}
		
		
		
		
		endTime=System.nanoTime(); //获取结束时间  

		System.out.println("程序运行时间： "+(endTime-startTime)+"ns"); 
		
		
		
		
	}
}
