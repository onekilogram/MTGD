package org.hit.data.utils;

/**
 * @ClassName WordUtils 
 * @Description 单词工厂 
 * @author kg 
 * @date   2017年12月1日 上午10:26:15
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordUtils {

	List<String> wordList = new ArrayList<String>(5000);

	Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();

	/**
	 * 读取txt文件的内容
	 * 
	 * @param file
	 *            想要读取的文件对象
	 * @return 返回文件内容
	 */
	public static List<String> txtTOList(File file) {
		List<String> wordList = new ArrayList<String>(5000);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				wordList.add(s);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wordList;
	}

	public static void main(String[] args) {
		// List<String> wordList = txtTOList();
		//获得src/main/resource
		URL url = WordUtils.class.getClassLoader().getResource("data/words.txt");
		File file = new File(url.getFile());
		List<String> list = txtTOList(file);
		
		System.out.println(list.get(0));//
		
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		
		for(String s: list){
			int len = s.length();
			if(map.get(len)==null){
				List<String> tmp = new ArrayList<String>();
				tmp.add(s);
				map.put(len, tmp);
			}else{
				map.get(len).add(s);
			}
		}
		for(Integer key :map.keySet()){
			System.out.println("length is "+key+",count is "+map.get(key).size());
		}
	}
}
