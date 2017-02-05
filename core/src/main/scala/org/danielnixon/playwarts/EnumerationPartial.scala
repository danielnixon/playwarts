package org.danielnixon.playwarts

@deprecated("Use sbt-extrawarts instead.", "0.29.0")
object EnumerationPartial extends ClassMethodWart(
  "scala.Enumeration",
  "withName",
  "Enumeration#withName is disabled"
)
