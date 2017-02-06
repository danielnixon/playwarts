package org.danielnixon.playwarts

import sbt._
import sbt.Keys._
import wartremover.WartRemover
import wartremover.WartRemover.autoImport.wartremoverClasspaths

object PlayWarts extends AutoPlugin {

  private val version = BuildInfo.version
  private val artifactID = BuildInfo.artifactID
  private val organization = BuildInfo.organization

  object autoImport {
    val PlayWart = org.danielnixon.playwarts.PlayWart
  }

  override def trigger: PluginTrigger = allRequirements

  override def requires: Plugins = WartRemover

  override lazy val projectSettings: Seq[Setting[_]] = Seq(
    libraryDependencies += organization %% artifactID % version % Provided,
    wartremoverClasspaths ++= {
      (dependencyClasspath in Compile).value.files
        .find(_.name.contains(artifactID))
        .map(_.toURI.toString)
        .toList
    }
  )
}
