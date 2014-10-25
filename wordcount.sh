
#Remove Previous Directory
hdfs dfs -rm -r /user/gijs/wordcount/short_wordcount


#Run MapReduce Job
hadoop jar mapreduce.jar jobs.WordCount wordcount/short wordcount/short_wordcount


#Cat Results
hdfs dfs -cat /user/gijs/wordcount/short_wordcount/p* > wordcount


#Look at Results
#less wordcount