name := """play-scala"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "com.google.inject" % "guice" % "3.0",
  "mysql" % "mysql-connector-java" % "5.1.18",
  "org.jooq" %% "play-jooq" % "1.0-SNAPSHOT",
  "org.jooq" % "jooq" % "3.4.2",
  "org.jooq" % "jooq-meta" % "3.4.2",
  "org.jooq" % "jooq-codegen" % "3.4.2"
)
