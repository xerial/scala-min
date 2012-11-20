import sbt._
import sbt.Keys._
import xerial.sbt.Pack._

object Build extends sbt.Build {

  lazy val root = Project(
    id = "scala-min",
    base = file("."),
    settings = Defaults.defaultSettings ++ packSettings ++
      Seq(
        // Mapping from program name -> Main class
        packMain := Map("hello" -> "scalamin.Hello"),
        // custom settings here
        crossPaths := false,
        libraryDependencies ++= Seq(
          // Add dependent jars here
          "org.xerial" % "xerial-core" % "3.0",
          "org.scalatest" %% "scalatest" % "1.8" % "test"
        )
      )
  )
}
