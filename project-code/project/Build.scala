import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "play-jooq"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,

    "org.jooq" % "jooq" % "3.0.1",
    "org.jooq" % "jooq-meta" % "3.0.1",
    "org.jooq" % "jooq-codegen" % "3.0.1"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    organization := "org.jooq",

    publishArtifact in(Compile, packageDoc) := false
  )

}
