package org.danielnixon.playwarts

object CookiesPartial extends ClassMethodWart(
  "play.api.mvc.Cookies",
  "apply",
  "Cookies#apply is disabled - use Cookies#get instead"
)
