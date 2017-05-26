package org.danielnixon.playwarts

import org.scalatest.FunSuite
import org.wartremover.test.WartTestTraverser
import play.api.mvc.Cookies

class CookiesPartialTest extends FunSuite {
  def cookies: Cookies = ???

  test("can't use Cookies#apply") {
    val result = WartTestTraverser(CookiesPartial) {
      val foo = cookies("foo")
    }
    assertResult(List("[wartremover:CookiesPartial] Cookies#apply is disabled - use Cookies#get instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("doesn't detect other `apply` methods") {
    val result = WartTestTraverser(CookiesPartial) {
      case class A(apply: Int)
      println(A(1).apply)
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("CookiesPartial wart obeys SuppressWarnings") {
    val result = WartTestTraverser(CookiesPartial) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.CookiesPartial"))
      val foo = cookies("foo")
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
