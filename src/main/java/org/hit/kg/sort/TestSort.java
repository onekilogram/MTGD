package org.hit.kg.sort;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;


public class TestSort {
    public static void main(String args[]) throws Exception{
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (otherArgs.length != 2 ) {
          System.err.println("Usage: wordcount <in>  <out>");
          System.exit(2);
        }
        Job job = new Job(conf, "Test Sort");
        job.setJarByClass(TestSort.class);
        job.setMapperClass(MyMapper.class);
        job.setPartitionerClass(MyPartitioner.class) ; //partition函数
        job.setReducerClass(MyReducer.class) ;
        job.setNumReduceTasks(3) ;

        job.setMapOutputKeyClass(LongWritable.class) ;
        job.setMapOutputValueClass(NullWritable.class) ;

        job.setOutputKeyClass(LongWritable.class) ;
        job.setOutputValueClass(NullWritable.class) ;

        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}