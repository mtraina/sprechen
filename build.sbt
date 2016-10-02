name := """sprechen"""

version := "0.3.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  ws,
  "org.mindrot" % "jbcrypt" % "0.3m",
  "org.reactivemongo" %% "play2-reactivemongo" % "0.11.14",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "org.mockito" % "mockito-all" % "1.10.19" % Test,
  specs2 % Test,
  "de.flapdoodle.embed" % "de.flapdoodle.embed.mongo" % "1.50.5"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
javaOptions in Test += "-Dconfig.file=conf/application.test.conf"
