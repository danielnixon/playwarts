package org.danielnixon.playwarts

object TraversableOnceOps extends ClassMultiWart(
  "org.danielnixon.playwarts.TraversableOnceOps",
  "scala.collection.TraversableOnce",
  List(
    "reduceLeft" -> "TraversableOnce#reduceLeft is disabled - use TraversableOnce#reduceLeftOption or TraversableOnce#foldLeft instead"
  )
)
