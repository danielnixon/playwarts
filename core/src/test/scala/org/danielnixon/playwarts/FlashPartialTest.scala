package org.danielnixon.playwarts

import org.scalatest.FunSuite
import org.wartremover.test.WartTestTraverser
import play.api.mvc.Flash

class FlashPartialTest extends FunSuite {
  test("can't use Flash#apply") {
    val result = WartTestTraverser(FlashPartial) {
      val foo = Flash()("foo")
    }
    assertResult(List("[wartremover:FlashPartial] Flash#apply is disabled - use Flash#get instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("doesn't detect other `apply` methods") {
    val result = WartTestTraverser(FlashPartial) {
      case class A(apply: Int)
      println(A(1).apply)
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("FlashPartial wart obeys SuppressWarnings") {
    val result = WartTestTraverser(FlashPartial) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.FlashPartial"))
      val foo = Flash()("foo")
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
