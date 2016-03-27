name := "left-cats-core"

libraryDependencies ++= Seq(
  "org.typelevel"        %%% "cats-core"  % "0.4.1",
  "org.scalacheck"        %% "scalacheck" % "1.12.+" % "test",
  "com.github.mpilquist" %%% "simulacrum" % "0.7.0",
  compilerPlugin("org.scalamacros" %% "paradise" % "2.1.0-M5" cross CrossVersion.full)
)

scalacOptions := Seq(
  "-feature",
  "-deprecation",
  "-encoding", "utf8",
  "-language:postfixOps",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-target:jvm-1.7",
  "-unchecked",
  "-Xcheckinit",
  "-Xfuture",
  "-Xlint",
  "-Xfatal-warnings",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-value-discard",
  "-Xfuture")
