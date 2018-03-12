package org.hit.kg.sort;
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapper extends Mapper<LongWritable, Text, LongWritable, NullWritable> {

    @Override
    protected void map(LongWritable key, Text value,Context context)
            throws IOException, InterruptedException {
        String[] values = value.toString().split("\\s+") ; //空格切分
        //
        context.write(new LongWritable(Long.valueOf(values[0])), NullWritable.get()) ;
    }

}