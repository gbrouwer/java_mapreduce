package jobs;

import java.io.IOException;
import java.util.*;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
 

//----------------------------------------------------------------------------------------
public class Union 
	{
 
	//------------------------------------------------------------------------------------
    public static class Map extends Mapper<LongWritable, Text, Text, Text> 
    	{
        
    	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException 
    		{

    		
    		//New Text for Relation Name
    		String[] stringvalues;
    		Text relation = new Text();  
    		Text tuple = new Text();

    		
    		//Split String
    		String strTuple = null;
    		String line = value.toString();
    		stringvalues = line.split(",");


    		//Set Relation Name
    		relation.set(stringvalues[0]);
    		
    		
    		//Combine values into key and tuple
    		for (int i=1;i<stringvalues.length;i++)
    			{
    			strTuple = strTuple + "," + stringvalues[i];
    			}
    		tuple.set(strTuple);

    		
    		//Emit to Reducer
    		context.write(tuple,tuple);

    		}
    	}

    

	//------------------------------------------------------------------------------------
    public static class Reduce extends Reducer<Text, Text, Text, Text> 
    	{
        
    	public void reduce(Text key, Iterator<Text> values, Context context) throws IOException, InterruptedException 
    		{
            
    		//Loop through values bag
			//int sum = 0;
			//while (values.hasNext()) 
				//{
			//	sum += values.next().get();
			//	}
    		
			//Write out
    		//context.write(key,new IntWritable(sum));

    		}
    	}
 

	//------------------------------------------------------------------------------------
    //Main method
    public static void main(String[] args) throws Exception 
    	{
    	
    	//Read Parameters
        Path inPath = new Path(args[0]);
        Path outPath =  new Path(args[1]);
        
        //Configuration
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(MatrixMultiplication.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class); 
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class); 

        //Set IO
        FileInputFormat.addInputPath(job, inPath);
        FileOutputFormat.setOutputPath(job, outPath);
 
        //Run
        job.waitForCompletion(true);
    	}
	
	}