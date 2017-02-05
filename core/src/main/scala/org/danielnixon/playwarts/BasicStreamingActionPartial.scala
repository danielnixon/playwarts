package org.danielnixon.playwarts

@deprecated("Use sbt-slickwarts instead.", "0.29.0")
object BasicStreamingActionPartial extends ClassMethodWart(
  "slick.profile.BasicStreamingAction",
  "head",
  "BasicStreamingAction#head is disabled - use BasicStreamingAction#headOption instead"
)
