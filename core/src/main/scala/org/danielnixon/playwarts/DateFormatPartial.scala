package org.danielnixon.playwarts

object DateFormatPartial extends ClassMethodWart(
   "java.text.DateFormat",
   "parse",
   "DateFormat#parse is disabled")
