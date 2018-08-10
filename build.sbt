// All releases including intermediate ones are published here,
// final ones are also published to Maven Central.
resolvers += Resolver.bintrayRepo("hseeberger", "maven")

name := "configtest"

version := "0.1"

scalaVersion := "2.12.6"

fork := true// default is false 

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.14"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.1.3",
  "de.heikoseeberger" %% "akka-http-play-json" % "1.21.0"
)

// https://mvnrepository.com/artifact/com.typesafe.play/play-json
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.9"
