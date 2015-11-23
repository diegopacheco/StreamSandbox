//
// SBT Assembly: One Big FAT Jat for Spark
//

import AssemblyKeys._

assemblySettings

//
// NORMAL SBT
//

name := "apache-spark-streaming-scala-kafka"

version := "1.0"

organization := "com.github.diegopacheco"

publishMavenStyle := true

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

//
// SBT Assembly: One Big FAT Jat for Spark
//

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
    case PathList("org", "apache", xs @ _*) => MergeStrategy.last
    case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
    case PathList("com", "google", xs @ _*) => MergeStrategy.last
    case PathList("org.apache.spark", "spark-network-common_2.10", xs @ _*) => MergeStrategy.last
    case PathList(ps @ _*) if ps.last endsWith "pom.properties" => MergeStrategy.first 
    case "about.html" => MergeStrategy.rename
    case x => old(x)
  }
}

// put all libs in the lib_managed directory, that way we can distribute eclipse project files
retrieveManaged := true
    
EclipseKeys.relativizeLibs := true
    
// Avoid generating eclipse source entries for the java directories
(unmanagedSourceDirectories in Compile) <<= (scalaSource in Compile)(Seq(_))
(unmanagedSourceDirectories in Test) <<= (scalaSource in Test)(Seq(_))
