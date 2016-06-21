package org.danielnixon.playwarts

object WSResponsePartial extends ClassMultiWart(
  "org.danielnixon.playwarts.WSResponsePartial",
  "play.api.libs.ws.WSResponse",
  List(
    "json" -> "WSResponse#json is disabled",
    "xml" -> "WSResponse#xml is disabled"
  )
)
