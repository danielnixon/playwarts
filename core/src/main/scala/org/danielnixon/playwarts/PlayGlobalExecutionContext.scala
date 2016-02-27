package org.danielnixon.playwarts

object PlayGlobalExecutionContext extends ObjectWart(
  "play.api.libs.concurrent.Execution",
  "Execution is disabled - declare a dependency on an ExecutionContext instead")
