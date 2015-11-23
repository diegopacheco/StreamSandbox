name := "apache-spark-streaming-scala-kafka"

version := "1.0"

scalaVersion := "2.10.6"

scalaVersion in ThisBuild := "2.10.6"

resolvers += "Akka Repo" at "http://repo.akka.io/releases" 
resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += "Sonatype Repo" at "https://oss.sonatype.org/content/repositories/releases/"
resolvers += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
resolvers += "Twitter Repo" at "http://maven.twttr.com/"
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies += "org.apache.spark" % "spark-core_2.10" % "1.5.2"
libraryDependencies += "org.apache.spark" % "spark-streaming_2.10" % "1.5.2"
libraryDependencies += "org.apache.spark" % "spark-streaming-kafka_2.10" % "1.5.2"

EclipseKeys.withSource := true

lazy val commonSettings = Seq(
  version      := "1.0-SNAPSHOT",
  organization := "com.github.diegopacheco.sandbox.stream.flink",
  scalaVersion := "2.10.6"
)
