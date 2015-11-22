package com.github.diegopacheco.sandbox.stream.flink.fun

import org.apache.flink.api.scala._
import com.github.diegopacheco.sandbox.stream.flink.fun.WordCountData._

object WordCount extends App{
    
    val env = ExecutionEnvironment.getExecutionEnvironment
    
    val text:DataSet[String] = env.fromCollection(WORDS)
    
    val counts = text
      .flatMap { _.toLowerCase.split("\\W+") filter { _.nonEmpty } }
      .map { (_, 1) }
      .groupBy(0)
      .sum(1)
   
    counts.print()
    
}