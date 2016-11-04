package org.danielnixon.playwarts

import play.sbt.PlayScala
import play.sbt.routes.RoutesKeys.routes
import sbt._
import sbt.Keys._
import wartremover.WartRemover
import wartremover.WartRemover.autoImport.{ wartremoverClasspaths, wartremoverExcluded }

object PlayWarts extends AutoPlugin {

  private val version = buildinfo.BuildInfo.version
  private val artifactID = buildinfo.BuildInfo.artifactID
  private val organization = buildinfo.BuildInfo.organization

  object autoImport {
    val PlayWart = org.danielnixon.playwarts.PlayWart
  }

  override def trigger = allRequirements

  override def requires = WartRemover && PlayScala

  override lazy val projectSettings = Seq(
    libraryDependencies += organization %% artifactID % version % Provided,
    wartremoverClasspaths ++= {
      (dependencyClasspath in Compile).value.files
        .find(_.name.contains(artifactID))
        .map(_.toURI.toString)
        .toList
    },
    wartremoverExcluded ++= routes.in(Compile).value
  )
}
