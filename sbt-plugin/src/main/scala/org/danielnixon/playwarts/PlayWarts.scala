package org.danielnixon.playwarts

import sbt._
import sbt.Keys._
import wartremover.WartRemover
import wartremover.WartRemover.autoImport.{ wartremoverClasspaths, wartremoverExcluded }

object PlayWarts extends AutoPlugin {

  private val home = Path.userHome.getAbsolutePath
  private val version = buildinfo.BuildInfo.version
  private val artifactID = buildinfo.BuildInfo.artifactID
  private val organization = buildinfo.BuildInfo.organization

  object autoImport {
    val PlayWart = org.danielnixon.playwarts.PlayWart
  }

  override def trigger = allRequirements

  override def requires = WartRemover

  override lazy val projectSettings = Seq(
    libraryDependencies += organization % artifactID % version % Provided,
    wartremoverClasspaths ++= Seq(
      s"file:$home/.ivy2/local/$organization/$artifactID/$version/jars/$artifactID.jar",
      s"file:$home/.ivy2/cache/$organization/$artifactID/jars/$artifactID-$version.jar"
    ),
    wartremoverExcluded ++= Seq(
      crossTarget.value / "routes" / "main" / "controllers" / "ReverseRoutes.scala",
      crossTarget.value / "routes" / "main" / "controllers" / "javascript" / "JavaScriptReverseRoutes.scala",
      crossTarget.value / "routes" / "main" / "router" / "Routes.scala",
      crossTarget.value / "routes" / "main" / "router" / "RoutesPrefix.scala"
    )
  )
}
