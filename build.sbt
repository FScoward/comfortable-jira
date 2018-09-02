name := "comfortable-jira"

version := "0.1"

scalaVersion := "2.12.6"

val circeVersion = "0.9.3"

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.0.5",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "com.typesafe.akka" %% "akka-http" % "10.0.0",
  "com.typesafe" % "config" % "1.3.2",
  "com.softwaremill.sttp" %% "core" % "1.3.1"
)

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

scalafmtOnCompile := true

