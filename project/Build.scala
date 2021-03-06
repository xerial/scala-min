import sbt._
import sbt.Keys._
import xerial.sbt.Pack._

object Build extends sbt.Build {

  lazy val root = Project(
    id = "scala-min",
    base = file("."),
    settings = Defaults.defaultSettings ++ packSettings ++
      Seq(
        scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
        // Mapping from program name -> Main class
        packMain := Map("hello" -> "scalamin.Hello"),
        // custom settings here
        scalaVersion := "2.10.3",
        crossPaths := false,
        libraryDependencies ++= Seq(
          // Add dependent jars here
          "org.xerial" % "xerial-core" % "3.2.2",
          "org.scalatest" % "scalatest_2.10" % "2.0.M5b" % "test"
        )
      )
  )
}
