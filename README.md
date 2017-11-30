# PlayWarts

[![Build Status](https://travis-ci.org/danielnixon/playwarts.svg?branch=master)](https://travis-ci.org/danielnixon/playwarts)
[![Dependency Status](https://www.versioneye.com/user/projects/5418232b54ffbda60b000061/badge.svg?style=flat)](https://www.versioneye.com/user/projects/5418232b54ffbda60b000061)
[![Codacy Badge](https://api.codacy.com/project/badge/f641de970f1f4a98a1900ee38250bb7d)](https://www.codacy.com/app/danielnixon/playwarts)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.danielnixon/playwarts_2.11/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.danielnixon/playwarts_2.11)

[WartRemover](https://github.com/wartremover/wartremover) warts for [Play Framework](https://www.playframework.com/).

## Versions

| PlayWarts version | WartRemover version | Play version       | Scala version   | sbt version   | Supported |
|-------------------|---------------------|--------------------|-----------------|---------------|-----------|
| 1.2.0             | 2.2.1               | 2.6.7              | 2.11.12, 2.12.4 | 1.0.x, 0.13.x |           |
| 1.0.0             | 2.1.1               | 2.6.0              | 2.11.11, 2.12.2 | 0.13.x        | No        |
| 0.31.0 ([README](https://github.com/danielnixon/playwarts/blob/fda3dc2ebc78bc62c598375c0656ce83f932cf8b/README.md))            | 2.0.1               | 2.5.x             | 2.11.x          | 0.13.x        | No        |
| 0.15 ([README](https://github.com/danielnixon/playwarts/blob/77b01471e016d2d494224dd838715eeff6e19ebf/README.md))     | 0.14                | 2.4.x              | 2.11.x        | 0.13.x        | No        |

## Usage

1. Setup [WartRemover](http://www.wartremover.org/doc/install-setup.html).
2. Add the following to your `plugins.sbt`:

    ```scala
    addSbtPlugin("org.danielnixon" % "sbt-playwarts" % "1.2.0")
    ```

3. Add the following to your `build.sbt`:
    ```scala
    wartremoverWarnings ++= Seq(
      PlayWart.CookiesPartial,
      PlayWart.FlashPartial,
      PlayWart.FormPartial,
      PlayWart.HeadersPartial,
      PlayWart.InjectedController,
      PlayWart.JavaApi,
      PlayWart.JsLookupResultPartial,
      PlayWart.JsReadablePartial,
      PlayWart.LangObject,
      PlayWart.SessionPartial,
      PlayWart.TypedMapPartial,
      PlayWart.WSResponsePartial)
    ```

## Warts

### Play Framework

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

#### InjectedController

Inheriting from `play.api.mvc.InjectedController` is disabled because it uses JSR 330 method injection and therefore _cannot_ work without mutability and magic (and it hinders testing). Inherit your controllers from `AbstractController` instead. See [Migration26#Scala-Controller-changes](https://www.playframework.com/documentation/2.6.x/Migration26#Scala-Controller-changes).

#### JavaApi

The Java API in the `play` package is disabled. Use the Scala API under `play.api` instead.

#### JsLookupResultPartial

`play.api.libs.json.JsLookupResult` has a `get` method which can throw. Use `JsLookupResult#getOrElse` instead.

#### JsReadablePartial

`play.api.libs.json.JsReadable` has an `as` method which can throw. Use `JsReadable#asOpt` instead.

#### LangObject

The `play.api.i18n.Lang` object is disabled. Use `play.api.i18n.Langs` instead.

#### SessionPartial

`play.api.mvc.Session` has an `apply` method that can throw. Use `Session#get` instead.

#### TypedMapPartial

`play.api.libs.typedmap.TypedMap` has an `apply` method that can throw. Use `TypedMap#get` instead.

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

## See also

* [sbt-ignore-play-generated](https://github.com/danielnixon/sbt-ignore-play-generated): Configure linters and coverage tools to ignore Play's generated source files.
* [ExtraWarts](https://github.com/danielnixon/extrawarts): Extra WartRemover warts.
* [SlickWarts](https://github.com/danielnixon/slickwarts): WartRemover warts for [Slick](http://slick.typesafe.com/).
* [Scala.js Warts](https://github.com/danielnixon/scalajswarts):  WartRemover warts for [Scala.js](https://www.scala-js.org/).
