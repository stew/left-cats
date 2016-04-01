name := "left-cats"

organization in Global := "org.typelevel"

scalaVersion in Global := "2.11.7"

lazy val `left-cats` = project.in(file(".")).aggregate(`left-catsJVM`, `left-catsJS`).settings(publish := {})
lazy val `left-catsJVM` = project.aggregate(coreJVM).settings(publish := {})
lazy val `left-catsJS` = project.aggregate(coreJS).settings(publish := {})

lazy val core = crossProject.crossType(CrossType.Pure)

lazy val coreJVM = core.jvm.settings(publishSettings)
lazy val coreJS = core.js.settings(publishSettings)

publishMavenStyle := true

(licenses in Global) += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0.html"))

scmInfo := Some(ScmInfo(url("https://github.com/stew/left-cats"),
  "https://github.com/stew/left-cats.git"))

lazy val tagName = Def.setting {
  "v${if (releaseUseGlobalVersion.value) (version in ThisBuild).value else version.value}"
}

lazy val publishSettings = Seq(
  bintrayRepository := {
    if (isSnapshot.value)
      "snapshots"
    else
      "releases"
  },
  releaseCrossBuild := true,
  releaseTagName := tagName.value,
  publishArtifact in Test := false,
  pomIncludeRepository := Function.const(false)
)
