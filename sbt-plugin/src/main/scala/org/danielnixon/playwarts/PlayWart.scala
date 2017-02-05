package org.danielnixon.playwarts

import wartremover.Wart

object PlayWart {
  val AssetsObject: Wart = wart("AssetsObject")
  val CookiesPartial: Wart = wart("CookiesPartial")
  val FlashPartial: Wart = wart("FlashPartial")
  val FormPartial: Wart = wart("FormPartial")
  val HeadersPartial: Wart = wart("HeadersPartial")
  val JavaApi: Wart = wart("JavaApi")
  val JsLookupResultPartial: Wart = wart("JsLookupResultPartial")
  val JsReadablePartial: Wart = wart("JsReadablePartial")
  val LangObject: Wart = wart("LangObject")
  val MessagesObject: Wart = wart("MessagesObject")
  val PlayGlobalExecutionContext: Wart = wart("PlayGlobalExecutionContext")
  val SessionPartial: Wart = wart("SessionPartial")
  val WSResponsePartial: Wart = wart("WSResponsePartial")

  private def wart(name: String) = {
    Wart.custom(s"org.danielnixon.playwarts.$name")
  }
}
