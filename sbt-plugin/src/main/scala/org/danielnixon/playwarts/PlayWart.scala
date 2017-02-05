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

  @deprecated("Use sbt-slickwarts instead.", "0.29.0")
  val BasicStreamingActionPartial: Wart = wart("BasicStreamingActionPartial")

  val DateFormatPartial: Wart = wart("DateFormatPartial")
  val EnumerationPartial: Wart = wart("EnumerationPartial")
  val FutureObject: Wart = wart("FutureObject")
  val GenMapLikePartial: Wart = wart("GenMapLikePartial")
  val GenTraversableLikeOps: Wart = wart("GenTraversableLikeOps")
  val GenTraversableOnceOps: Wart = wart("GenTraversableOnceOps")
  val LegacyDateTimeCode: Wart = wart("LegacyDateTimeCode")
  val ScalaGlobalExecutionContext: Wart = wart("ScalaGlobalExecutionContext")
  val StringOpsPartial: Wart = wart("StringOpsPartial")
  val TraversableOnceOps: Wart = wart("TraversableOnceOps")
  val UntypedEquality: Wart = wart("UntypedEquality")

  private def wart(name: String) = {
    Wart.custom(s"org.danielnixon.playwarts.$name")
  }
}
