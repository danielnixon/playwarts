# PlayWarts

[![Build Status](https://travis-ci.org/danielnixon/playwarts.svg?branch=master)](https://travis-ci.org/danielnixon/playwarts)
[![Dependency Status](https://www.versioneye.com/user/projects/5418232b54ffbda60b000061/badge.svg?style=flat)](https://www.versioneye.com/user/projects/5418232b54ffbda60b000061)
[![Codacy Badge](https://api.codacy.com/project/badge/f641de970f1f4a98a1900ee38250bb7d)](https://www.codacy.com/app/danielnixon/playwarts)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.danielnixon/playwarts_2.11/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.danielnixon/playwarts_2.11)

[WartRemover](https://github.com/typelevel/wartremover) warts for [Play Framework](https://www.playframework.com/) and [Slick](http://slick.typesafe.com/) (and some bonus warts).

## Usage

1. Setup [WartRemover](https://github.com/typelevel/wartremover).
2. Add the following to your `build.sbt`:
    ```scala
    val playwartsVersion = "0.7"

    libraryDependencies += "org.danielnixon" %% "playwarts" % playwartsVersion
    
    wartremoverClasspaths += s"file:${Path.userHome.absolutePath}/.ivy2/cache/org.danielnixon/playwarts_2.11/jars/playwarts_2.11-$playwartsVersion.jar"
    
    // Play Framework
    wartremoverWarnings ++= Seq(
      Wart.custom("org.danielnixon.playwarts.AkkaObject"),
      Wart.custom("org.danielnixon.playwarts.CacheObject"),
      Wart.custom("org.danielnixon.playwarts.CookiesPartial"),
      Wart.custom("org.danielnixon.playwarts.CryptoObject"),
      Wart.custom("org.danielnixon.playwarts.DBObject"),
      Wart.custom("org.danielnixon.playwarts.FlashPartial"),
      Wart.custom("org.danielnixon.playwarts.FormPartial"),
      Wart.custom("org.danielnixon.playwarts.GlobalSettings"),
      Wart.custom("org.danielnixon.playwarts.HeadersPartial"),
      Wart.custom("org.danielnixon.playwarts.JsLookupResultPartial"),
      Wart.custom("org.danielnixon.playwarts.JsReadablePartial"),
      Wart.custom("org.danielnixon.playwarts.LangObject"),
      Wart.custom("org.danielnixon.playwarts.MessagesObject"),
      Wart.custom("org.danielnixon.playwarts.PlayObject"),
      Wart.custom("org.danielnixon.playwarts.SessionPartial"),
      Wart.custom("org.danielnixon.playwarts.WSObject"))
    
    // Slick
    wartremoverWarnings ++= Seq(
      Wart.custom("org.danielnixon.playwarts.BasicStreamingActionPartial"))

    // Bonus Warts
    wartremoverWarnings ++= Seq(
      Wart.custom("org.danielnixon.playwarts.GenMapLikePartial"),
      Wart.custom("org.danielnixon.playwarts.GenTraversableLikeOps"),
      Wart.custom("org.danielnixon.playwarts.GenTraversableOnceOps"),
      Wart.custom("org.danielnixon.playwarts.TraversableOnceOps"))
    ```

## Warts

### Play Framework

#### AkkaObject

`play.api.libs.concurrent.Akka` is no longer needed. Declare a dependency on `ActorSystem` instead.
See [Migration24#Dependency-Injected-Components](https://www.playframework.com/documentation/2.4.x/Migration24#Dependency-Injected-Components).

#### CacheObject

`play.api.cache.Cache` relies on global state. Declare a dependency on `play.api.cache.CacheApi` instead.
See [Migration24#Dependency-Injected-Components](https://www.playframework.com/documentation/2.4.x/Migration24#Dependency-Injected-Components).

#### CookiesPartial

`play.api.mvc.Cookies` has an `apply` method that can throw. Use `Cookies#get` instead.

#### CryptoObject

The `play.api.libs.Crypto` object relies on global state. Declare a dependency on the `play.api.libs.Crypto` class instead.
See [Migration24#Dependency-Injected-Components](https://www.playframework.com/documentation/2.4.x/Migration24#Dependency-Injected-Components).

#### DBObject

The `play.api.db.DB` object relies on global state. Declare a dependency on `play.api.db.DBApi` or `play.api.db.Database` instead.
See [Migration24#Dependency-Injected-Components](https://www.playApiframework.com/documentation/2.4.x/Migration24#Dependency-Injected-Components).

#### FlashPartial

`play.api.mvc.Flash` has an `apply` method that can throw. Use `Flash#get` instead.

#### FormPartial

`play.api.data.Form` has a `get` method which will throw if the form contains
errors. The program should be refactored to use `play.api.data.Form#fold` to
explicitly handle forms with errors and successful form submissions.

#### GlobalSettings

`GlobalSettings` relies on global state. See [Migration24#GlobalSettings](https://playframework.com/documentation/2.4.x/Migration24#GlobalSettings).

#### HeadersPartial

`play.api.mvc.Headers` has an `apply` method that can throw. Use `Headers#get` instead.

### JsLookupResultPartial

`play.api.libs.json.JsLookupResult` has a `get` method which can throw. Use `JsLookupResult#getOrElse` instead.

### JsReadablePartial

`play.api.libs.json.JsReadable` has an `as` method which can throw. Use `JsReadable#asOpt` instead.

### LangObject

The `play.api.i18n.Lang` object is disabled. Use `play.api.i18n.Langs` instead.

#### MessagesObject

`play.api.i18n.Messages.Implicits` exists to allow you to continue to use static controller objects in Play 2.4.x. Use controller classes with dependency injection instead. See [Migration24#I18n](https://www.playframework.com/documentation/2.4.x/Migration24#I18n).

#### PlayObject

The following methods on the `play.api.Play` object are disabled:
* `Play#maybeApplication`: Relies on global state.
* `Play#current`: Relies on global state, throws a `RuntimeException` if no current application.
* `Play#unsafeApplication`: Relies on global state, returns `null` if no current application.

In all three cases you should declare a dependency on `play.api.Application` instead.

#### SessionPartial

`play.api.mvc.Session` has an `apply` method that can throw. Use `Session#get` instead.

#### WSObject

`play.api.libs.ws.WS` relies on global state. Declare a dependency on `play.api.libs.ws.WSApi` instead.
See [Migration24#Dependency-Injected-Components](https://www.playframework.com/documentation/2.4.x/Migration24#Dependency-Injected-Components).

### Slick

#### BasicStreamingActionPartial

`slick.profile.BasicStreamingAction` has a `head` method which will fail if the stream is empty (i.e. if the `SELECT` SQL query returned zero rows). Use `headOption` instead.

### Bonus Warts

#### GenMapLikePartial

`scala.collection.GenMapLike` has an `apply` method that can throw ` NoSuchElementException` if there is no mapping for the given key. Use `GenMapLike#get` instead.

#### GenTraversableLikeOps

WartRemover's [ListOps](https://github.com/puffnfresh/wartremover#listops) wart only applies to `scala.collection.immutable.List`. The `GenTraversableLikeOps` wart extends it to everything that implements `scala.collection.GenTraversableLike`.

`scala.collection.GenTraversableLike` has:

* `head`,
* `tail`,
* `init` and
* `last` methods,

all of which will throw if the list is empty. The program should be refactored to use:

* `GenTraversableLike#headOption`,
* `GenTraversableLike#drop(1)`,
* `GenTraversableLike#dropRight(1)` and
* `GenTraversableLike#lastOption` respectively,

to explicitly handle both populated and empty `GenTraversableLike`s.
