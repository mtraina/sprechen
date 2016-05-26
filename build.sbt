name := """sprechen"""

version := "0.1.1"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  ws,
  "org.reactivemongo" %% "play2-reactivemongo" % "0.11.11",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "org.mockito" % "mockito-all" % "1.10.19" % Test,
  specs2 % Test,
  "de.flapdoodle.embed" % "de.flapdoodle.embed.mongo" % "1.50.3"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
javaOptions in Test += "-Dconfig.file=conf/application.test.conf"
