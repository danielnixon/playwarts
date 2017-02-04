package org.danielnixon.playwarts

import org.scalatest.FunSuite
import org.wartremover.test.WartTestTraverser
import play.api.mvc.Session

class SessionPartialTest extends FunSuite {
  test("can't use Session#apply") {
    val result = WartTestTraverser(SessionPartial) {
      val foo = Session()("foo")
    }
    assertResult(List("Session#apply is disabled - use Session#get instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("doesn't detect other `apply` methods") {
    val result = WartTestTraverser(SessionPartial) {
      case class A(apply: Int)
      println(A(1).apply)
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("SessionPartial wart obeys SuppressWarnings") {
    val result = WartTestTraverser(SessionPartial) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.SessionPartial"))
      val foo = Session()("foo")
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
