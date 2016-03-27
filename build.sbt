name := "left-cats"

organization in Global := "org.typelevel"

scalaVersion in Global := "2.11.7"

crossScalaVersions in Global := Seq("2.10.6", "2.11.7")

lazy val `left-cats` = project.in(file(".")).aggregate(`left-catsJVM`, `left-catsJS`).settings(publish := {})
lazy val `left-catsJVM` = project.aggregate(coreJVM)
lazy val `left-catsJS` = project.aggregate(coreJS)

lazy val core = crossProject.crossType(CrossType.Pure)

lazy val coreJVM = core.jvm
lazy val coreJS = core.js
