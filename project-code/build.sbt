name := """play-jooq"""

version := "1.0-SNAPSHOT"

organization := "org.jooq"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
  javaJdbc,
  "org.jooq" % "jooq" % "3.4.2",
  "org.jooq" % "jooq-meta" % "3.4.2",
  "org.jooq" % "jooq-codegen" % "3.4.2"
)
