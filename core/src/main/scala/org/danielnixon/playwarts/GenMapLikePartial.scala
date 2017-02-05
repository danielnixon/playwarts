package org.danielnixon.playwarts

@deprecated("Use sbt-extrawarts instead.", "0.29.0")
object GenMapLikePartial extends ClassMethodWart(
  "scala.collection.GenMapLike",
  "apply",
  "GenMapLike#apply is disabled - use GenMapLike#get instead"
)
