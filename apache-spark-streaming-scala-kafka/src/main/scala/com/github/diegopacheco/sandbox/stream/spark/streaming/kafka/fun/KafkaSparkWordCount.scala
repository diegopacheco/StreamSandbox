package com.github.diegopacheco.sandbox.stream.spark.streaming.kafka.fun

import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka._
import org.apache.spark.SparkConf

object KafkaSparkWordCount extends App {
  
    val Array(zkQuorum, group, topics, numThreads) = Array[String]("localhost","my-consumer-group","test","1")
    val sparkConf = new SparkConf().setAppName("KafkaSparkStreamWordCount")
    val ssc = new StreamingContext(sparkConf, Seconds(2))
    ssc.checkpoint("checkpoint")

    val topicMap = topics.split(",").map((_, numThreads.toInt)).toMap
    val lines = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap).map(_._2)
    
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x, 1L))
      .reduceByKeyAndWindow(_ + _, _ - _, Minutes(10), Seconds(2), 2)
    wordCounts.print()

    ssc.start()
    ssc.awaitTermination()
    
}