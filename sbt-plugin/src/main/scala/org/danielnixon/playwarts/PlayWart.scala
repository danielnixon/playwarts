package org.danielnixon.playwarts

import wartremover.Wart

object PlayWart {
  val CookiesPartial: Wart = wart("CookiesPartial")
  val FlashPartial: Wart = wart("FlashPartial")
  val FormPartial: Wart = wart("FormPartial")
  val HeadersPartial: Wart = wart("HeadersPartial")
  val InjectedController: Wart = wart("InjectedController")
  val JavaApi: Wart = wart("JavaApi")
  val JsLookupResultPartial: Wart = wart("JsLookupResultPartial")
  val JsReadablePartial: Wart = wart("JsReadablePartial")
  val LangObject: Wart = wart("LangObject")
  val SessionPartial: Wart = wart("SessionPartial")
  val TypedMapPartial: Wart = wart("TypedMapPartial")
  val WSResponsePartial: Wart = wart("WSResponsePartial")

  private def wart(name: String) = {
    Wart.custom(s"org.danielnixon.playwarts.$name")
  }
}
