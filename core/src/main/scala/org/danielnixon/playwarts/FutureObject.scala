package org.danielnixon.playwarts

@deprecated("Use sbt-extrawarts instead.", "0.29.0")
object FutureObject extends ObjectMultiWart(
  "org.danielnixon.playwarts.FutureObject",
  "scala.concurrent.Future",
  List("reduce" -> "Future#reduce is disabled - use Future#fold instead")
)
