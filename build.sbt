name := "playwarts"

version := "0.2-SNAPSHOT"

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

scalaVersion := "2.11.6"

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies += "org.brianmckenna" %% "wartremover" % "0.12"

exportJars := true
