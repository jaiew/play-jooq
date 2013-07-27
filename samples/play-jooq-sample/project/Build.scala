import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "play-jooq-sample"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,

    "com.google.inject" % "guice" % "3.0",
    "mysql" % "mysql-connector-java" % "5.1.18",

    "org.jooq" %% "play-jooq" % "1.0-SNAPSHOT",
    "org.jooq" % "jooq" % "3.1.0",
    "org.jooq" % "jooq-meta" % "3.1.0",
    "org.jooq" % "jooq-codegen" % "3.1.0"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
