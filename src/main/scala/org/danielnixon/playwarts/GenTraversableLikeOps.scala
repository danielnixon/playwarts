package org.danielnixon.playwarts

object GenTraversableLikeOps extends ClassMultiWart(
  "org.danielnixon.playwarts.GenTraversableLikeOps",
  "scala.collection.GenTraversableLike",
  List(
    "head" -> "GenTraversableLike#head is disabled - use GenTraversableLike#headOption instead",
    "tail" -> "GenTraversableLike#tail is disabled - use GenTraversableLike#drop(1) instead",
    "init" -> "GenTraversableLike#init is disabled - use GenTraversableLike#dropRight(1) instead",
    "last" -> "GenTraversableLike#last is disabled - use GenTraversableLike#lastOption instead"
  )
)
