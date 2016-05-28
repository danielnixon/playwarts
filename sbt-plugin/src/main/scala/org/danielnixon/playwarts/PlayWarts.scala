package org.danielnixon.playwarts

import sbt._
import sbt.Keys._
import wartremover.WartRemover
import wartremover.WartRemover.autoImport.wartremoverClasspaths

object PlayWarts extends AutoPlugin {

  private val home = Path.userHome.getAbsolutePath
  private val version = buildinfo.BuildInfo.version

  // http://stackoverflow.com/a/21516954/2476884
  private val compileOnly = "compileonly"

  object autoImport {
    val PlayWart = org.danielnixon.playwarts.PlayWart
  }

  override def trigger = allRequirements

  override def requires = WartRemover

  override lazy val projectSettings = Seq(
    ivyConfigurations += config(compileOnly).hide,
    libraryDependencies += "org.danielnixon" % "playwarts_2.11" % version % compileOnly,
    unmanagedClasspath in Compile ++= update.value.select(configurationFilter(compileOnly)),
    wartremoverClasspaths ++= Seq(
      s"file:$home/.ivy2/local/org.danielnixon/playwarts_2.11/$version/jars/playwarts_2.11.jar",
      s"file:$home/.ivy2/cache/org.danielnixon/playwarts_2.11/jars/playwarts_2.11-$version.jar"
    )
  )
}
