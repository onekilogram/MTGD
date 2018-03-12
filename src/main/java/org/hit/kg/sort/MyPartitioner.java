package org.hit.kg.sort;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class MyPartitioner extends Partitioner<LongWritable, NullWritable> {

    @Override
    public int getPartition(LongWritable key, NullWritable value, int numPartitions) {
        if(key.get() <= 3300){
            return 0 % numPartitions ;
        }else if(key.get() >3300 && key.get() < 6600){
            return 1 % numPartitions ;
        }else{
            return 2 % numPartitions ;
        }
    }

}