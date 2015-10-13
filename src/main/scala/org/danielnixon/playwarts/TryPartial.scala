package org.danielnixon.playwarts

object TryPartial extends ClassMultiWart(
  "org.danielnixon.playwarts.TryPartial",
  "scala.util.Try",
  List("get" -> "Try#get is disabled - use Try#getOrElse instead")
)
