package org.danielnixon.playwarts

import wartremover.Wart

object PlayWart {
  val AkkaObject = wart("AkkaObject")
  val CacheObject = wart("CacheObject")
  val CookiesPartial = wart("CookiesPartial")
  val CryptoObject = wart("CryptoObject")
  val DBObject = wart("DBObject")
  val FlashPartial = wart("FlashPartial")
  val FormPartial = wart("FormPartial")
  val GlobalSettings = wart("GlobalSettings")
  val HeadersPartial = wart("HeadersPartial")
  val JsLookupResultPartial = wart("JsLookupResultPartial")
  val JsReadablePartial = wart("JsReadablePartial")
  val LangObject = wart("LangObject")
  val MessagesObject = wart("MessagesObject")
  val PlayGlobalExecutionContext = wart("PlayGlobalExecutionContext")
  val PlayObject = wart("PlayObject")
  val SessionPartial = wart("SessionPartial")
  val WSObject = wart("WSObject")
  val TestHelpersObject = wart("TestHelpersObject")
  val BasicStreamingActionPartial = wart("BasicStreamingActionPartial")
  val DateFormatPartial = wart("DateFormatPartial")
  val FutureObject = wart("FutureObject")
  val GenMapLikePartial = wart("GenMapLikePartial")
  val GenTraversableLikeOps = wart("GenTraversableLikeOps")
  val GenTraversableOnceOps = wart("GenTraversableOnceOps")
  val OptionPartial = wart("OptionPartial")
  val ScalaGlobalExecutionContext = wart("ScalaGlobalExecutionContext")
  val StringOpsPartial = wart("StringOpsPartial")
  val TraversableOnceOps = wart("TraversableOnceOps")

  private def wart(name: String) = {
    Wart.custom(s"org.danielnixon.playwarts.$name")
  }
}
