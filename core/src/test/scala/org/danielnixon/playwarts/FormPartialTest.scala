package org.danielnixon.playwarts

import org.scalatest.FunSuite
import org.wartremover.test.WartTestTraverser
import play.api.data.Form
import play.api.data.Forms.text

class FormPartialTest extends FunSuite {
  test("can't use Form#get") {
    val result = WartTestTraverser(FormPartial) {
      Form("foo" -> text).get
    }
    assertResult(List("[wartremover:FormPartial] Form#get is disabled - use Form#fold instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("doesn't detect other `get` methods") {
    val result = WartTestTraverser(FormPartial) {
      case class A(get: Int)
      println(A(1).get)
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("FormPartial wart obeys SuppressWarnings") {
    val result = WartTestTraverser(FormPartial) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.FormPartial"))
      val foo = Form("foo" -> text).get
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
