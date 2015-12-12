name := "playwarts"

version := "0.11"

organization := "org.danielnixon"

licenses := Seq("The Apache Software License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

homepage := Some(url("https://github.com/danielnixon/playwarts"))

pomExtra := (
  <scm>
    <url>git@github.com:danielnixon/playwarts.git</url>
    <connection>scm:git:git@github.com:danielnixon/playwarts.git</connection>
  </scm>
  <developers>
    <developer>
      <id>danielnixon</id>
      <name>Daniel Nixon</name>
      <url>https://danielnixon.org/</url>
    </developer>
  </developers>)

publishMavenStyle := true

publishArtifact in Test := false

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

scalaVersion := "2.11.7"

coverageMinimum := 90
coverageFailOnMinimum := true

scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint:_",
  "-Ywarn-dead-code",
  "-Ywarn-inaccessible",
  "-Ywarn-unused",
  "-Ywarn-unused-import",
  "-Ywarn-numeric-widen",
  "-Ywarn-nullary-override")

resolvers += Resolver.sonatypeRepo("releases")

val playVersion = "2.4.4"

libraryDependencies ++= Seq(
  "org.brianmckenna" %% "wartremover" % "0.14",
  "org.scalatest" %% "scalatest" % "2.2.5" % Test,
  "com.typesafe.play" %% "play" % playVersion % Test,
  "com.typesafe.play" %% "play-slick" % "1.1.1" % Test,
  "com.typesafe.play" %% "play-jdbc" % playVersion % Test,
  "com.typesafe.play" %% "play-ws" % playVersion % Test,
  "com.typesafe.play" %% "play-cache" % playVersion % Test)

exportJars := true
