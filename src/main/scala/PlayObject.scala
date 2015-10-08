package org.danielnixon.playwarts

object PlayObject extends ObjectMultiWart(
  "org.danielnixon.playwarts.PlayObject",
  "play.api.Play",
  List(
      "current" -> "Play#current is disabled - declare a dependency on Application instead",
      "maybeApplication" -> "Play#maybeApplication is disabled - declare a dependency on Application instead",
      "unsafeApplication" -> "Play#unsafeApplication is disabled - declare a dependency on Application instead"
    )
)
