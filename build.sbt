name := "scala-kafka-consumer-example"

version := "0.1"

scalaVersion := "2.12.3"

libraryDependencies := Seq(
  "org.scala-lang" % "scala-library" % "2.12.3",
//  "org.apache.kafka" % "kafka-clients" % "2.4.1",
  "org.apache.kafka" %% "kafka" % "2.4.1",
  "org.slf4j" % "slf4j-log4j12" % "1.7.25"
)
