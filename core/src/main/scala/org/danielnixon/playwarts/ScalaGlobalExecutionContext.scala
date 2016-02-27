package org.danielnixon.playwarts

object ScalaGlobalExecutionContext extends ObjectMultiWart(
  "org.danielnixon.playwarts.ScalaGlobalExecutionContext",
  "scala.concurrent.ExecutionContext",
  List(
    "global" -> "ExecutionContext#global is disabled - declare a dependency on an ExecutionContext instead",
    "Implicits" -> "ExecutionContext#Implicits is disabled - declare a dependency on an ExecutionContext instead"))


