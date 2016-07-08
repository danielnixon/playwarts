package org.danielnixon.playwarts

object JsReadablePartial extends ClassMethodWart(
  "play.api.libs.json.JsReadable",
  "as",
  "JsReadable#as is disabled - use JsReadable#asOpt instead")