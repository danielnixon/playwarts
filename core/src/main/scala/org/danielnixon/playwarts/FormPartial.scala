package org.danielnixon.playwarts

object FormPartial extends ClassMethodWart(
  "play.api.data.Form",
  "get",
  "Form#get is disabled - use Form#fold instead")
