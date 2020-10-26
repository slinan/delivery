name := "delivery"

version := "0.1"

scalaVersion := "2.13.3"

unmanagedResourceDirectories in IntegrationTest += baseDirectory.value / "resources"

libraryDependencies ++= Seq(
  "com.osinka.i18n" %% "scala-i18n" % "1.0.3",
  "org.scalatest" %% "scalatest" % "3.0.8" % Test
)


