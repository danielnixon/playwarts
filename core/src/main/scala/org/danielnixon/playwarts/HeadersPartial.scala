package org.danielnixon.playwarts

object HeadersPartial extends ClassMethodWart(
  "play.api.mvc.Headers",
  "apply",
  "Headers#apply is disabled - use Headers#get instead"
)
