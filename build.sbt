name := "left-cats"

organization in Global := "org.typelevel"
scalaVersion in Global := "2.12.4"

lazy val `left-cats` = project.in(file(".")).aggregate(`left-catsJVM`, `left-catsJS`).settings(publish := {})
lazy val `left-catsJVM` = project.aggregate(coreJVM).settings(publish := {})
lazy val `left-catsJS` = project.aggregate(coreJS).settings(publish := {})

lazy val core = crossProject.crossType(CrossType.Pure)

lazy val coreJVM = core.jvm
lazy val coreJS = core.js

(licenses in Global) += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0.html"))

