package org.hit.kg.jion;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFlatMapFunction;

import scala.Tuple2;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.spark.SparkConf;

public class WordCount {
	// 测试模式标志，代表着在本地运行
	public static Boolean debug = true;

	public static void main(String[] args) throws IOException {
		// 初始化sparkContext
		String appName = "wordcount";
		//String appMaster = "yarn-client";//

		SparkConf conf = new SparkConf().setAppName(appName);
		//if (debug)
			//conf.setMaster(appMaster);
		JavaSparkContext sc = new JavaSparkContext(conf);

		// 读文件
		// List<Integer> data = Arrays.asList(1, 2, 3, 4, 5,5);
		// JavaRDD<Integer> distData = sc.parallelize(data);
		// default value
		// String
		// inputFilePath1="D:\\CZMworkspace\\AutoTuningProject\\PythonFiles\\abusde.join";
		// String inputFilePath2=
		// "D:\\CZMworkspace\\AutoTuningProject\\PythonFiles\\abusde.join";
		String outputFilePath = "", inputFilePath1 = "", inputFilePath2 = "";
		if (args[0] != null) {
			inputFilePath1 = args[0];
		}
		if (args[1] != null) {
			inputFilePath2 = args[1];
		}
		if (args.length >= 3 && args[2] != null) {
			outputFilePath = args[2];
		}

		JavaRDD<String> sourceRDD1 = sc.textFile(inputFilePath1);
		JavaRDD<String> sourceRDD2 = sc.textFile(inputFilePath2);

		// 执行操作
		@SuppressWarnings("serial")
		JavaPairRDD<String, String> pair1 = sourceRDD1
				.flatMapToPair(new PairFlatMapFunction<String, String, String>() {

					@Override
					public Iterator<Tuple2<String, String>> call(String line) throws Exception {
						// TODO Auto-generated method stub
						String str1 = line.split("\\|")[0];
						//String str2 = line;
						Tuple2<String, String> tmp = new Tuple2<String, String>(str1, line);
						return Arrays.asList(tmp).iterator();
					}

				});
		@SuppressWarnings("serial")
		JavaPairRDD<String, String> pair2 = sourceRDD2
				.flatMapToPair(new PairFlatMapFunction<String, String, String>() {
					// @Override
					public Iterator<Tuple2<String, String>> call(String line) throws Exception {
						// TODO Auto-generated method stub
						String str1 = line.split("\\|")[0];
						//String str2 = line.split("\\|")[1];
						Tuple2<String, String> tmp = new Tuple2<String, String>(str1, line);
						// Arrays.asList(tmp).iterator();
						return Arrays.asList(tmp).iterator();
					}

				});

		// System.out.println(sourceRDD2.count());
		// JavaPairRDD<String, Integer> pair2 = distData.sourceRDD2((Integer s)
		// -> new Tuple2<String, Integer>(String.valueOf(s), 1));
		// pair1.join(pair2);
		JavaPairRDD<String, Tuple2<String, String>> joinResult = pair1.join(pair2);
		JavaRDD<String> strRdd = joinResult.map((Tuple2<String, Tuple2<String, String>> a) -> a._1
				+ "\t" + a._2._1 + "\t" + a._2._2);

		System.out.println(strRdd.count());

		// if (!debug)
		// strRdd.saveAsTextFile(outputFilePath);
		//
		// if (debug) {
		// strRdd.count();
		// }
		// // else {
		// // File file=new File(outputFilePath);
		// // if(!file.exists()) file.createNewFile();
		// // FileOutputStream out=new FileOutputStream(file,false); //直接覆盖之前的文件
		// // for (String tmp:tmplist){
		// // //System.out.println(tmp);
		// // out.write((tmp+"\n").getBytes("utf-8"));
		// // }
		// //
		// // }
		// else {
		//
		// }

		// System.out.println( "Hello World!" );
		sc.close();
	}
}
