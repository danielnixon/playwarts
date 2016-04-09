lazy val commonSettings = Seq(
  organization := "org.danielnixon",
  licenses := Seq("The Apache Software License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt")),
  version := "0.18",
  publishMavenStyle := true,
  publishArtifact in Test := false,
  publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
  },
  homepage := Some(url("https://github.com/danielnixon/playwarts")),
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
  </developers>),
  coverageMinimum := 90,
  coverageFailOnMinimum := true
)

val playVersion = "2.5.1"
val wartremoverVersion = "0.14"
val scalatestVersion = "2.2.6"

lazy val root = Project(
  id = "root",
  base = file("."),
  aggregate = Seq(core, sbtPlug)
).settings(commonSettings ++ Seq(
  publishArtifact := false,
  scalaVersion := "2.11.8"
): _*)

lazy val core = Project(
  id = "core",
  base = file("core")
).settings(commonSettings ++ Seq(
  name := "playwarts",
  scalaVersion := "2.11.8",
  libraryDependencies ++= Seq(
    "org.brianmckenna" %% "wartremover" % wartremoverVersion,
    "org.scalatest" %% "scalatest" % scalatestVersion % Test,
    "com.typesafe.play" %% "play" % playVersion % Test,
    "com.typesafe.play" %% "play-test" % playVersion % Test,
    "com.typesafe.play" %% "play-slick" % "2.0.0" % Test,
    "com.typesafe.play" %% "play-ws" % playVersion % Test,
    "com.typesafe.play" %% "play-cache" % playVersion % Test),
  dependencyOverrides ++= Set(
    "org.scalatest" %% "scalatest" % scalatestVersion
  ),
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
): _*)

lazy val sbtPlug: Project = Project(
  id = "sbt-plugin",
  base = file("sbt-plugin")
).enablePlugins(
  BuildInfoPlugin
).disablePlugins(
  ScoverageSbtPlugin
).settings(commonSettings ++ Seq(
  buildInfoKeys := Seq[BuildInfoKey](version),
  buildInfoPackage := "buildinfo",
  sbtPlugin := true,
  name := "sbt-playwarts",
  scalaVersion := "2.10.6",
  addSbtPlugin("org.brianmckenna" %% "sbt-wartremover" % wartremoverVersion),
  scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Xfatal-warnings",
    "-Xlint",
    "-Ywarn-dead-code",
    "-Ywarn-inaccessible",
    "-Ywarn-numeric-widen",
    "-Ywarn-nullary-override")
): _*)

addCommandAlias("publishLocalCoverageOff", ";clean;coverageOff;compile;test;publishLocal")
addCommandAlias("publishSignedCoverageOff", ";clean;coverageOff;compile;test;publishSigned")