package org.danielnixon.playwarts

object JavaApi extends PackageWart(
  targetPackage = "play",
  errorMessage = "The Java API is disabled - use the Scala API",
  packages = Set("cache", "db", "http", "i18n", "inject", "libs", "mvc"),
  classes = Set("Application", "ApplicationLoader", "Configuration", "DefaultApplication", "Environment", "Logger"))
