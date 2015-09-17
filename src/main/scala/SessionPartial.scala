package org.danielnixon.playwarts

object SessionPartial extends Partial(
  "play.api.mvc.Session",
  "apply",
  "Session#apply is disabled - use Session#get instead")
