name := "sysvp-api"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.12.6"

crossScalaVersions := Seq("2.11.12", "2.12.6")

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

libraryDependencies += guice
libraryDependencies += javaJdbc
libraryDependencies += jdbc
libraryDependencies += "com.h2database" % "h2" % "1.4.196"
libraryDependencies += "org.awaitility" % "awaitility" % "2.0.0" % Test
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % Test
libraryDependencies += "org.mockito" % "mockito-core" % "2.1.0" % Test
libraryDependencies += "io.jsonwebtoken" % "jjwt" % "0.9.1"

testOptions in Test += Tests.Argument(TestFrameworks.JUnit, "-a", "-v")

/*
libraryDependencies += "org.postgresql" % "postgresql" % "9.4-1200-jdbc41"
*/
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.18"

/*libraryDependencies += "com.typesafe.play" %% "play-mailer" % "6.0.1"
libraryDependencies += "com.typesafe.play" %% "play-mailer-guice" % "6.0.1"*/

