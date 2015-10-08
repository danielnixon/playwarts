package org.danielnixon.playwarts

object GenTraversableOnceOps extends ClassMultiWart(
  "org.danielnixon.playwarts.GenTraversableOnceOps",
  "scala.collection.GenTraversableOnce",
  List(
    "reduce" -> "GenTraversableOnce#reduce is disabled - use GenTraversableOnce#reduceOption or GenTraversableOnce#fold instead",
    "reduceRight" -> "GenTraversableOnce#reduceRight is disabled - use GenTraversableOnce#reduceRightOption or GenTraversableOnce#foldRight instead"
  )
)