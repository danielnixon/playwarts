package org.danielnixon.playwarts

object GenMapLikePartial extends ClassMethodWart(
  "scala.collection.GenMapLike",
  "apply",
  "GenMapLike#apply is disabled - use GenMapLike#get instead")
