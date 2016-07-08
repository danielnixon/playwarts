package org.danielnixon.playwarts

import wartremover.Wart

object PlayWart {
  val AssetsObject = wart("AssetsObject")
  val CookiesPartial = wart("CookiesPartial")
  val FlashPartial = wart("FlashPartial")
  val FormPartial = wart("FormPartial")
  val HeadersPartial = wart("HeadersPartial")
  val JavaApi = wart("JavaApi")
  val JsLookupResultPartial = wart("JsLookupResultPartial")
  val JsReadablePartial = wart("JsReadablePartial")
  val LangObject = wart("LangObject")
  val MessagesObject = wart("MessagesObject")
  val PlayGlobalExecutionContext = wart("PlayGlobalExecutionContext")
  val SessionPartial = wart("SessionPartial")
  val WSResponsePartial = wart("WSResponsePartial")

  val BasicStreamingActionPartial = wart("BasicStreamingActionPartial")

  val DateFormatPartial = wart("DateFormatPartial")
  val FutureObject = wart("FutureObject")
  val GenMapLikePartial = wart("GenMapLikePartial")
  val GenTraversableLikeOps = wart("GenTraversableLikeOps")
  val GenTraversableOnceOps = wart("GenTraversableOnceOps")
  val LegacyDateTimeCode = wart("LegacyDateTimeCode")
  val OptionPartial = wart("OptionPartial")
  val ScalaGlobalExecutionContext = wart("ScalaGlobalExecutionContext")
  val StringOpsPartial = wart("StringOpsPartial")
  val TraversableOnceOps = wart("TraversableOnceOps")
  val UntypedEquality = wart("UntypedEquality")

  private def wart(name: String) = {
    Wart.custom(s"org.danielnixon.playwarts.$name")
  }
}
