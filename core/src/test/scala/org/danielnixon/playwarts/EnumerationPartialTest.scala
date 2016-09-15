package org.danielnixon.playwarts

import org.scalatest.FunSuite
import org.wartremover.test.WartTestTraverser

class EnumerationPartialTest extends FunSuite {

  test("can't use Enumeration#withName") {
    val result = WartTestTraverser(EnumerationPartial) {
      val foo = play.api.Mode.withName("")
    }
    assertResult(List("Enumeration#withName is disabled"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("doesn't detect other `apply` methods") {
    val result = WartTestTraverser(EnumerationPartial) {
      case class A(apply: Int)
      println(A(1).apply)
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("EnumerationPartial wart obeys SuppressWarnings") {
    val result = WartTestTraverser(EnumerationPartial) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.EnumerationPartial"))
      val foo = play.api.Mode.withName("")
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
