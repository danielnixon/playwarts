package org.danielnixon.playwarts

object FlashPartial extends ClassMethodWart(
  "play.api.mvc.Flash",
  "apply",
  "Flash#apply is disabled - use Flash#get instead"
)
