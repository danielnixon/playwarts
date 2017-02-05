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

  @deprecated("Use sbt-extrawarts instead.", "0.29.0")
  val DateFormatPartial: Wart = wart("DateFormatPartial")
  @deprecated("Use sbt-extrawarts instead.", "0.29.0")
  val EnumerationPartial: Wart = wart("EnumerationPartial")
  @deprecated("Use sbt-extrawarts instead.", "0.29.0")
  val FutureObject: Wart = wart("FutureObject")
  @deprecated("Use sbt-extrawarts instead.", "0.29.0")
  val GenMapLikePartial: Wart = wart("GenMapLikePartial")
  @deprecated("Use sbt-extrawarts instead.", "0.29.0")
  val GenTraversableLikeOps: Wart = wart("GenTraversableLikeOps")
  @deprecated("Use sbt-extrawarts instead.", "0.29.0")
  val GenTraversableOnceOps: Wart = wart("GenTraversableOnceOps")
  @deprecated("Use sbt-extrawarts instead.", "0.29.0")
  val LegacyDateTimeCode: Wart = wart("LegacyDateTimeCode")
  @deprecated("Use sbt-extrawarts instead.", "0.29.0")
  val ScalaGlobalExecutionContext: Wart = wart("ScalaGlobalExecutionContext")
  @deprecated("Use sbt-extrawarts instead.", "0.29.0")
  val StringOpsPartial: Wart = wart("StringOpsPartial")
  @deprecated("Use sbt-extrawarts instead.", "0.29.0")
  val TraversableOnceOps: Wart = wart("TraversableOnceOps")
  @deprecated("Use sbt-extrawarts instead.", "0.29.0")
  val UntypedEquality: Wart = wart("UntypedEquality")

  private def wart(name: String) = {
    Wart.custom(s"org.danielnixon.playwarts.$name")
  }
}
