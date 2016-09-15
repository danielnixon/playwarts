package org.danielnixon.playwarts

object EnumerationPartial extends ClassMethodWart(
  "scala.Enumeration",
  "withName",
  "Enumeration#withName is disabled"
)
