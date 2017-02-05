package org.danielnixon.playwarts

@deprecated("Use sbt-extrawarts instead.", "0.29.0")
object DateFormatPartial extends ClassMethodWart(
  "java.text.DateFormat",
  "parse",
  "DateFormat#parse is disabled"
)
