package org.danielnixon.playwarts

object JsLookupResultPartial extends ClassMethodWart(
  "play.api.libs.json.JsLookupResult",
  "get",
  "JsLookupResult#get is disabled - use JsLookupResult#getOrElse instead"
)
