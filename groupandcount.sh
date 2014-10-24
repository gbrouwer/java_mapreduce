
hdfs dfs -rm -r relational_output
hadoop jar mapreduce.jar jobs.GroupAndCount relational/table relational_output
hdfs dfs -cat relational_output/p* > groupandcount

