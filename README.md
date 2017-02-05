# PlayWarts

[![Build Status](https://travis-ci.org/danielnixon/playwarts.svg?branch=master)](https://travis-ci.org/danielnixon/playwarts)
[![Dependency Status](https://www.versioneye.com/user/projects/5418232b54ffbda60b000061/badge.svg?style=flat)](https://www.versioneye.com/user/projects/5418232b54ffbda60b000061)
[![Codacy Badge](https://api.codacy.com/project/badge/f641de970f1f4a98a1900ee38250bb7d)](https://www.codacy.com/app/danielnixon/playwarts)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.danielnixon/playwarts_2.11/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.danielnixon/playwarts_2.11)

[WartRemover](https://github.com/wartremover/wartremover) warts for [Play Framework](https://www.playframework.com/) (and some bonus warts).

## Versions

| PlayWarts version | WartRemover version | Play version       | Scala version |
|-------------------|---------------------|--------------------|---------------|
| 0.29.0            | 1.3.0               | 2.5.12             | 2.11.8        |
| 0.15 ([README](https://github.com/danielnixon/playwarts/blob/77b01471e016d2d494224dd838715eeff6e19ebf/README.md))     | 0.14                | 2.4.x              | 2.11.x        |

## Usage

1. Setup [WartRemover](https://github.com/wartremover/wartremover).
2. Add the following to your `plugins.sbt`:

    ```scala
    addSbtPlugin("org.danielnixon" % "sbt-playwarts" % "0.28")
    ```

3. Add the following to your `build.sbt`:
    ```scala
    wartremoverWarnings ++= Seq(
      PlayWart.AssetsObject,
      PlayWart.CookiesPartial,
      PlayWart.FlashPartial,
      PlayWart.FormPartial,
      PlayWart.HeadersPartial,
      PlayWart.JavaApi,
      PlayWart.JsLookupResultPartial,
      PlayWart.JsReadablePartial,
      PlayWart.LangObject,
      PlayWart.MessagesObject,
      PlayWart.PlayGlobalExecutionContext,
      PlayWart.SessionPartial,
      PlayWart.WSResponsePartial)
    ```

## Warts

### Play Framework

#### AssetsObject

The `controllers.Assets` object depends on global state. Declare a dependency on an instance of the `controllers.Assets` class instead.

#### CookiesPartial

`play.api.mvc.Cookies` has an `apply` method that can throw. Use `Cookies#get` instead.

#### FlashPartial

`play.api.mvc.Flash` has an `apply` method that can throw. Use `Flash#get` instead.

#### FormPartial

`play.api.data.Form` has a `get` method which will throw if the form contains
errors. The program should be refactored to use `play.api.data.Form#fold` to
explicitly handle forms with errors and successful form submissions.

#### HeadersPartial

`play.api.mvc.Headers` has an `apply` method that can throw. Use `Headers#get` instead.

#### JavaApi

The Java API in the `play` package is disabled. Use the Scala API under `play.api` instead.

#### JsLookupResultPartial

`play.api.libs.json.JsLookupResult` has a `get` method which can throw. Use `JsLookupResult#getOrElse` instead.

#### JsReadablePartial

`play.api.libs.json.JsReadable` has an `as` method which can throw. Use `JsReadable#asOpt` instead.

#### LangObject

The `play.api.i18n.Lang` object is disabled. Use `play.api.i18n.Langs` instead.

#### MessagesObject

`play.api.i18n.Messages.Implicits` exists to allow you to continue to use static controller objects in Play 2.4.x. Use controller classes with dependency injection instead. See [Migration24#I18n](https://www.playframework.com/documentation/2.4.x/Migration24#I18n).

#### PlayGlobalExecutionContext

Play's global execution context `play.api.libs.concurrent.Execution#defaultContext` is disabled. Declare a dependency on an `ExecutionContext` instead. See [MUST NOT hardcode the thread-pool / execution context](https://github.com/alexandru/scala-best-practices/blob/master/sections/4-concurrency-parallelism.md#411-must-not-hardcode-the-thread-pool--execution-context).

#### SessionPartial

`play.api.mvc.Session` has an `apply` method that can throw. Use `Session#get` instead.

#### WSResponsePartial

The `play.api.libs.ws.WSResponse` trait defines `json` and `xml` methods that will throw if the response body can't be parsed as JSON or XML respectively (the default `AhcWSResponse` implementation of this trait throws `JsonParseException` and `SAXException`). You can wrap these unsafe methods in an implicit class that might look something like this:

```scala
implicit class WSResponseWrapper(val response: WSResponse) extends AnyVal {
  @SuppressWarnings(Array("org.danielnixon.playwarts.WSResponsePartial"))
  def jsonOpt: Option[JsValue] = catching[JsValue](classOf[JsonParseException]) opt response.json

  @SuppressWarnings(Array("org.danielnixon.playwarts.WSResponsePartial"))
  def xmlOpt: Option[Elem] = catching[Elem](classOf[SAXException]) opt response.xml
}
```

### Slick

#### BasicStreamingActionPartial

Deprecated. Use [SlickWarts](https://github.com/danielnixon/slickwarts) instead.

### Bonus Warts

Deprecated. Use [ExtraWarts](https://github.com/danielnixon/extrawarts) instead.

## See also

* [ExtraWarts](https://github.com/danielnixon/extrawarts): Extra WartRemover warts.
* [SlickWarts](https://github.com/danielnixon/slickwarts): WartRemover warts for [Slick](http://slick.typesafe.com/).
* [Scala.js Warts](https://github.com/danielnixon/scalajswarts):  WartRemover warts for [Scala.js](https://www.scala-js.org/).
