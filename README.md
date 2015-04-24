# PlayWarts [![Build Status](https://travis-ci.org/danielnixon/playwarts.svg?branch=master)](https://travis-ci.org/danielnixon/playwarts)

[WartRemover](https://github.com/typelevel/wartremover) warts for [Play Framework](https://www.playframework.com/).

## Usage

1. Setup [WartRemover](https://github.com/typelevel/wartremover).
2. Add the following to your `build.sbt`:
    ```scala
    resolvers += Resolver.sonatypeRepo("snapshots")
    
    libraryDependencies += "org.danielnixon" %% "playwarts" % "0.2"
    
    wartremoverClasspaths += "file:" +
      Path.userHome.absolutePath +
      "/.ivy2/cache/org.danielnixon/playwarts_2.11/jars/playwarts_2.11-0.2.jar"
      
    wartremoverWarnings ++= Seq(
      Wart.custom("org.danielnixon.playwarts.FormPartial"),
      Wart.custom("org.danielnixon.playwarts.JsValuePartial"))
    ```

## Warts

### FormPartial

`play.api.data.Form` has a `get` method which will throw if the form contains
errors. The program should be refactored to use `play.api.data.Form#fold` to
explicitly handle forms with errors and successful form submissions.

### JsValuePartial

`play.api.libs.json.JsValue` has an `as[T]` method which tries to convert the JSON
value into a T, throwing an exception if it can't. The program should be refactored to use `play.api.libs.json.JsValue#asOpt[T]` which maps any error to `None`.
