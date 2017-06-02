import scalariform.formatter.preferences._

val scala210 = "2.10.6"
val scala211 = "2.11.11"
val scala212 = "2.12.2"

scalaVersion := scala212

lazy val commonSettings = Seq(
  organization := "org.danielnixon",
  licenses := Seq("The Apache Software License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt")),
  version := "0.33.0-SNAPSHOT",
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
  pomExtra := {
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
      </developers>
  },
  coverageMinimum := 94,
  coverageFailOnMinimum := true,
  scalariformPreferences := scalariformPreferences.value
    .setPreference(DoubleIndentClassDeclaration, true)
    .setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, true),
  scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Xfatal-warnings",
    "-Ywarn-dead-code",
    "-Ywarn-inaccessible",
    "-Ywarn-value-discard",
    "-Ywarn-numeric-widen",
    "-Ywarn-nullary-override"),
  // TODO: Remove after Play 2.6 is released.
  resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
)

val coreName = "playwarts"
val playVersion = "2.6.0-RC1"
val wartremoverVersion = "2.1.0"
val scalatestVersion = "3.0.3"

lazy val core = Project(
  id = "core",
  base = file("core")
).settings(commonSettings ++ Seq(
  name := coreName,
  scalaVersion := scala212,
  crossScalaVersions := Seq(scala211, scala212),
  libraryDependencies ++= Seq(
    "org.wartremover" %% "wartremover" % wartremoverVersion,
    "org.scalatest" %% "scalatest" % scalatestVersion % Test,
    "com.typesafe.play" %% "play" % playVersion % Test,
    "com.typesafe.play" %% "play-test" % playVersion % Test,
    "com.typesafe.play" %% "play-slick" % "3.0.0-RC1" % Test,
    "org.joda" % "joda-convert" % "1.8.1" % Test, // TODO: Compilation fails without this...
    "com.typesafe.play" %% "play-ws" % playVersion % Test,
    "com.typesafe.play" %% "play-cache" % playVersion % Test,
    "com.typesafe.play" %% "play-specs2" % playVersion % Test,
    "com.typesafe.play" %% "play-mailer" % "6.0.0-M1" % Test
  ),
  dependencyOverrides ++= Set(
    "org.scalatest" %% "scalatest" % scalatestVersion
  ),
  scalacOptions ++= Seq("-Xlint:_", "-Ywarn-unused", "-Ywarn-unused-import")
): _*)

lazy val sbtPlug: Project = Project(
  id = "sbt-plugin",
  base = file("sbt-plugin")
).enablePlugins(
  BuildInfoPlugin
).disablePlugins(
  ScoverageSbtPlugin
).settings(commonSettings ++ Seq(
  buildInfoKeys := Seq[BuildInfoKey](version, organization, "artifactID" -> coreName),
  buildInfoPackage := s"${organization.value}.$coreName",
  sbtPlugin := true,
  name := s"sbt-$coreName",
  scalaVersion := scala210,
  crossScalaVersions := Seq(scala210),
  addSbtPlugin("org.wartremover" % "sbt-wartremover" % wartremoverVersion),
  scalacOptions += "-Xlint"
): _*)

addCommandAlias("publishLocalCoverageOff", ";clean;coverageOff;compile;test;publishLocal")
addCommandAlias("publishSignedCoverageOff", ";clean;coverageOff;compile;test;publishSigned")