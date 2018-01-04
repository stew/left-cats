name := "left-cats-core"

libraryDependencies ++= Seq(
  "org.typelevel"        %%% "cats-core"  % "1.0.1",
  "org.scalacheck"        %% "scalacheck" % "1.13.+" % "test"
)

scalacOptions := Seq(
  "-feature",
  "-deprecation",
  "-encoding", "utf8",
  "-language:postfixOps",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-unchecked",
  "-Xcheckinit",
  "-Xfuture",
  "-Xlint",
  "-Xfatal-warnings",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-value-discard",
  "-Xfuture")
