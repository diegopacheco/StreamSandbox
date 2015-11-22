name := "apache-flink-scala-fun"

version := "1.0"

scalaVersion := "2.10.5"

scalaVersion in ThisBuild := "2.10.5"

resolvers += "Akka Repo" at "http://repo.akka.io/releases" 
resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += "Sonatype Repo" at "https://oss.sonatype.org/content/repositories/releases/"
resolvers += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
resolvers += "Twitter Repo" at "http://maven.twttr.com/"
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies += "org.apache.flink" % "flink-scala" % "0.10.0"

EclipseKeys.withSource := true

lazy val commonSettings = Seq(
  version      := "1.0-SNAPSHOT",
  organization := "com.github.diegopacheco.sandbox.stream.flink",
  scalaVersion := "2.10.6"
)

lazy val app = (project in file("app")).
  settings(commonSettings: _*).
  settings(
    // your settings here
  )