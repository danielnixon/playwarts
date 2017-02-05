package org.danielnixon.playwarts

@deprecated("Use sbt-extrawarts instead.", "0.29.0")
object StringOpsPartial extends ClassMultiWart(
  "org.danielnixon.playwarts.StringOpsPartial",
  "scala.collection.immutable.StringOps",
  List(
    "toBoolean" -> "StringOps#toBoolean is disabled",
    "toByte" -> "StringOps#toByte is disabled",
    "toShort" -> "StringOps#toShort is disabled",
    "toInt" -> "StringOps#toInt is disabled",
    "toLong" -> "StringOps#toLong is disabled",
    "toFloat" -> "StringOps#toFloat is disabled",
    "toDouble" -> "StringOps#toDouble is disabled"
  )
)