name := "kstreams-stateless"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "org.apache.kafka" % "kafka-clients" % "0.10.2.0",
  "org.apache.kafka" % "kafka-streams" % "0.10.2.0",
  "org.twitter4j" % "twitter4j-stream" % "4.0.6",
  "com.typesafe" % "config" % "1.3.1"
)