package org.danielnixon.playwarts

object FutureObject extends ObjectMultiWart(
  "org.danielnixon.playwarts.FutureObject",
  "scala.concurrent.Future",
  List("reduce" -> "Future#reduce is disabled - use Future#fold instead")
)
