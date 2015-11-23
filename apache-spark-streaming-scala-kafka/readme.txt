$ sbt clean compile package assembly

$ cp /opt/StreamSandbox/apache-spark-streaming-scala-kafka/target/scala-2.10/apache-spark-streaming-scala-kafka-assembly-1.0.jar /opt/spark/examples/target/scala-2.10/

$ ./run-spark com.github.diegopacheco.sandbox.stream.spark.streaming.kafka.fun.KafkaSparkWordCount apache-spark-streaming-scala-kafka-assembly-1.0.jar