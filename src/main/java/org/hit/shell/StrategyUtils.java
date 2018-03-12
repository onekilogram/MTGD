package org.hit.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;

public class StrategyUtils {

	public static String getStringByTXT(String txtFilePath) {

		Configuration conf = new Configuration();
		StringBuffer buffer = new StringBuffer();
		FSDataInputStream fsr = null;
		BufferedReader bufferedReader = null;
		String lineTxt = null;
		try {
			FileSystem fs = FileSystem.get(URI.create(txtFilePath), conf);
			fsr = fs.open(new Path(txtFilePath));
			bufferedReader = new BufferedReader(new InputStreamReader(fsr));
			while ((lineTxt = bufferedReader.readLine()) != null) {
				// if(lineTxt.split("\t")[0].trim().equals("00067")){
				// return lineTxt;
				// }
				// System.out.println(lineTxt);
				return lineTxt == null ? "1" : lineTxt;
			}

		} catch (Exception e) {
			// e.printStackTrace();
			return "1";
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return lineTxt;
	}

	/**
	 * 获取 HDFS 集群节点信息
	 * 
	 * @return DatanodeInfo[]
	 */
	public static DatanodeInfo[] getHDFSNodes() {
		// 获取所有节点
		DatanodeInfo[] dataNodeStats = new DatanodeInfo[0];

		try {
			// 返回FileSystem对象
			FileSystem fs = getFileSystem();

			// 获取分布式文件系统
			DistributedFileSystem hdfs = (DistributedFileSystem) fs;

			dataNodeStats = hdfs.getDataNodeStats();
		} catch (IOException e) {
		}
		return dataNodeStats;
	}

	/**
	 * 获取文件系统
	 * 
	 * @return FileSystem
	 */
	public static FileSystem getFileSystem() {
		// 读取配置文件
		Configuration conf = new Configuration();
		// 文件系统
		FileSystem fs = null;

		String hdfsUri = "hdfs://master:9000";
		if (StringUtils.isBlank(hdfsUri)) {
			// 返回默认文件系统 如果在 Hadoop集群下运行，使用此种方法可直接获取默认文件系统
			try {
				fs = FileSystem.get(conf);
			} catch (IOException e) {
				// logger.error("", e);
			}
		} else {
			// 返回指定的文件系统,如果在本地测试，需要使用此种方法获取文件系统
			try {
				URI uri = new URI(hdfsUri.trim());
				fs = FileSystem.get(uri, conf);
			} catch (URISyntaxException | IOException e) {
				// logger.error("", e);
			}
		}

		return fs;
	}

	/**
	 * 查找某个文件在 HDFS集群的位置
	 * 
	 * @param filePath
	 * @return BlockLocation[]
	 */
	public static BlockLocation[] getFileBlockLocations(String filePath) {
		// 文件路径
		String hdfsUri = "hdfs://master:9000/";
		if (StringUtils.isNotBlank(hdfsUri)) {
			filePath = hdfsUri + filePath;
		}
		Path path = new Path(filePath);

		// 文件块位置列表
		BlockLocation[] blkLocations = new BlockLocation[0];
		try {
			// 返回FileSystem对象
			FileSystem fs = getFileSystem();
			// 获取文件目录
			FileStatus filestatus = fs.getFileStatus(path);
			// 获取文件块位置列表
			blkLocations = fs.getFileBlockLocations(filestatus, 0, filestatus.getLen());
		} catch (IOException e) {
			// logger.error("", e);
		}
		return blkLocations;
	}

	@SuppressWarnings("deprecation")
	public static Long getDefaultBlockSize() throws IOException {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);
		return fs.getDefaultBlockSize();

	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Configuration conf = new Configuration();
		String txtFilePath = "hdfs://master:9000/1.txt";
		String mbline = getStringByTXT(txtFilePath);
		System.out.println(mbline);

		DatanodeInfo[] datanodeInfos = getHDFSNodes();

		for (DatanodeInfo datanodeInfo : datanodeInfos) {
			System.out.println(datanodeInfo.getName());
			System.out.println(datanodeInfo.getCapacity());
			System.out.println(datanodeInfo.getBlockPoolUsed());
			System.out.println(datanodeInfo.getRemaining());
			System.out.println(datanodeInfo.getRemainingPercent());
		}
		BlockLocation[] nBlockLocations = getFileBlockLocations("lineitem.tbl");
		for (BlockLocation blockLocation : nBlockLocations) {
			System.out.println(blockLocation.toString());
			/// 134217728,
			/// 123437056,master
			// blockLocation.get
			// nBlockLocations.
		}

		Configuration conf1 = new Configuration();
		StringBuffer buffer = new StringBuffer();
		FSDataInputStream fsr = null;
		BufferedReader bufferedReader = null;
		String lineTxt = null;
		try {
			FileSystem fs = FileSystem.get(URI.create(txtFilePath), conf1);
			fsr = fs.open(new Path(txtFilePath));
			bufferedReader = new BufferedReader(new InputStreamReader(fsr));
			while ((lineTxt = bufferedReader.readLine()) != null) {
				// if(lineTxt.split("\t")[0].trim().equals("00067")){
				// return lineTxt;
				// }
				System.out.println(lineTxt);
				// return lineTxt == null ? "1" : lineTxt;
			}

		} catch (Exception e) {
			// e.printStackTrace();
			// return "1";
		}

	}

}