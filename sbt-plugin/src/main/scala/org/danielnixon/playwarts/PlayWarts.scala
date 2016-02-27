package org.danielnixon.playwarts

import sbt._
import sbt.Keys._
import wartremover.WartRemover
import wartremover.WartRemover.autoImport.wartremoverClasspaths

object PlayWarts extends AutoPlugin {

  private val home = Path.userHome.getAbsolutePath
  private val version = buildinfo.BuildInfo.version

  object autoImport {
    val PlayWart = org.danielnixon.playwarts.PlayWart
  }

  override def trigger = allRequirements

  override def requires = WartRemover

  override lazy val projectSettings = Seq(
    libraryDependencies += "org.danielnixon" % "playwarts_2.11" % version,
    wartremoverClasspaths ++= Seq(
      s"file:$home/.ivy2/local/org.danielnixon/playwarts_2.11/$version/jars/playwarts_2.11.jar",
      s"file:$home/.ivy2/cache/org.danielnixon/playwarts_2.11/jars/playwarts_2.11-$version.jar"
    )
  )
}
