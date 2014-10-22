MapReduce
=========

Java MapReduce Tools

How to run
hadoop jar mapreduce.jar jobs.PageRank pagerank/inputdata pagerank/outputdata 10 10 10 1 0.85

input parameters
-input data dir or file
-output data dir or file
-number of iterations
-rows in transition matrix
-columns in transition matrix
-columns in vector (1)
-beta correction vector
