package org.danielnixon.playwarts

object SessionPartial extends ClassWart(
  "play.api.mvc.Session",
  "apply",
  "Session#apply is disabled - use Session#get instead")
