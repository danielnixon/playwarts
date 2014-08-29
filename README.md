# PlayWarts

[WartRemover](https://github.com/typelevel/wartremover) warts for [Play Framework](https://www.playframework.com/)

[![Build Status](https://travis-ci.org/danielnixon/play-warts.svg?branch=master)](https://travis-ci.org/danielnixon/play-warts)

## Warts

### FormPartial

`play.api.data.Form` has a `get` method which will throw if the form contains
errors. The program should be refactored to use `play.api.data.Form#fold` to
explicitly handle forms with errors and successful form submissions.

### JsValuePartial

`play.api.libs.json.JsValue` has an `as[T]` method which tries to convert the JSON
value into a T, throwing an exception if it can't. The program should be refactored to use `play.api.libs.json.JsValue#asOpt[T]` which maps any error to `None`.
