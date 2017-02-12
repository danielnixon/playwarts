package org.danielnixon.playwarts

import org.scalatest.FunSuite
import org.wartremover.test.WartTestTraverser
import play.api.libs.json.{ JsLookupResult, JsUndefined }

class JsLookupResultPartialTest extends FunSuite {

  val jsLookupResult: JsLookupResult = new JsUndefined("")

  test("can't call JsLookupResult#get") {
    val result = WartTestTraverser(JsLookupResultPartial) {
      val foo = jsLookupResult.get
    }
    assertResult(List("[wartremover:JsLookupResultPartial] JsLookupResult#get is disabled - use JsLookupResult#getOrElse instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("JsLookupResultPartial wart obeys SuppressWarnings") {
    val result = WartTestTraverser(JsLookupResultPartial) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.JsLookupResultPartial"))
      val foo = jsLookupResult.get
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
