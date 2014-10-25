
hdfs dfs -rm -r relational_output
hadoop jar mapreduce.jar jobs.NaturalJoin relational/smalltable relational_output
hdfs dfs -cat relational_output/p* > naturaljoin

