package org.danielnixon.playwarts

object TypedMapPartial extends ClassMethodWart(
  "play.api.libs.typedmap.TypedMap",
  "apply",
  "TypedMap#apply is disabled - use TypedMap#get instead"
)
