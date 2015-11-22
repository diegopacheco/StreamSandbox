package com.github.diegopacheco.sandbox.stream.flink.stream.fun

import java.util.concurrent.TimeUnit

import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.windowing.delta.DeltaFunction
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.assigners.GlobalWindows
import org.apache.flink.streaming.api.windowing.evictors.TimeEvictor
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.triggers.DeltaTrigger

import scala.Stream._
import scala.math._
import scala.language.postfixOps
import scala.util.Random

object CarStream extends App {
    
   case class CarEvent(carId: Int, speed: Int, distance: Double, time: Long)
   
   def genCarStream(): Stream[CarEvent] = {
      
      def nextSpeed(carEvent:CarEvent):CarEvent = {
        val next =
          if (Random.nextBoolean) min(100, carEvent.speed + 5) else max(0, carEvent.speed - 5)
        CarEvent(carEvent.carId, next, carEvent.distance + next/3.6d,System.currentTimeMillis)
      }
      def carStream(speeds : Stream[CarEvent]):Stream[CarEvent] = {
        Thread.sleep(1000)
        speeds.append(carStream(speeds.map(nextSpeed)))
      }
      
      carStream(range(0, numOfCars).map(CarEvent(_,50,0,System.currentTimeMillis())))
  }
   
   val numOfCars = 2
   val evictionSec = 10
   val triggerMeters = 50d
   
   val env = StreamExecutionEnvironment.getExecutionEnvironment
   env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
   env.setParallelism(1)
    
   val cars = env.fromCollection(genCarStream())
   
   val topSeed = cars
      .assignAscendingTimestamps( _.time )
      .keyBy("carId")
      .window(GlobalWindows.create)
      .evictor(TimeEvictor.of(Time.of(evictionSec * 1000, TimeUnit.MILLISECONDS)))
      .trigger(DeltaTrigger.of(triggerMeters, new DeltaFunction[CarEvent] {
        def getDelta(oldSp: CarEvent, newSp: CarEvent): Double = newSp.distance - oldSp.distance
      }))
      .maxBy("speed")

    topSeed.print
  
}