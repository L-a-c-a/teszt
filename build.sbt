lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.laca",
      scalaVersion := "2.13.1"
    )),
    name := "tesztfeladat"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test

//ez feltételezi az sbt-assembly plugin meglétét:
assemblyJarName in assembly := "tesztfeladat.jar"
