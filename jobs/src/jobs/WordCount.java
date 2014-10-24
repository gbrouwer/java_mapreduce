package jobs;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Iterator;
 
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;



//-------------------------------------------------------------------------------------
public class WordCount 
	{

	
	//---------------------------------------------------------------------------------
	public static class Map extends Mapper<Object, Text, Text, IntWritable> 
		{

		private Text word = new Text();
		private IntWritable one = new IntWritable(1);
  
		@Override
		public void map(Object key, Text value, Context contex) throws IOException, InterruptedException 
			{
			
			//Remove Special Characters
			String line = value.toString().toLowerCase();
			line = line.replaceAll("[()?:!.,;]+", " ");
			
			//Tokenize
			StringTokenizer wordList = new StringTokenizer(line);
			while (wordList.hasMoreTokens()) 
				{
				word.set(wordList.nextToken());
				contex.write(word, one);
				}
			}
		}


	
	//---------------------------------------------------------------------------------
	public static class Reduce extends Reducer<Text, IntWritable, Text, IntWritable> 
		{
  
		private IntWritable totalWordCount = new IntWritable();
  
		@Override
		public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException 
			{
			int wordCount = 0;
			Iterator<IntWritable> it = values.iterator();
			while (it.hasNext()) 
				{
				wordCount += it.next().get();
				}
			totalWordCount.set(wordCount);
			context.write(key, totalWordCount);
			}
		}


	
	//---------------------------------------------------------------------------------
	public static void main(String[] args) throws Exception 
		{
   
		//Job Configuration
        Job job = Job.getInstance(new Configuration());
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class); 
        job.setMapperClass(Map.class); 
        job.setReducerClass(Reduce.class);  
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        //File IO
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
 
        //Run
        job.setJarByClass(WordCount.class);
        job.waitForCompletion(true);
         
         
		}
	
	}