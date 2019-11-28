lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.laca",
      scalaVersion := "2.13.1"
    )),
    name := "tesztfeladat"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test
