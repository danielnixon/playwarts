package org.danielnixon.playwarts

import org.scalatest.FunSuite
import org.wartremover.test.WartTestTraverser
import play.api.libs.typedmap.{ TypedKey, TypedMap }

class TypedMapPartialTest extends FunSuite {

  test("can't use TypedMap#apply") {
    val result = WartTestTraverser(TypedMapPartial) {
      TypedMap.empty(TypedKey[String](""))
    }
    assertResult(List("[wartremover:TypedMapPartial] TypedMap#apply is disabled - use TypedMap#get instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("doesn't detect other `apply` methods") {
    val result = WartTestTraverser(TypedMapPartial) {
      case class A(apply: Int)
      println(A(1).apply)
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("TypedMapPartial wart obeys SuppressWarnings") {
    val result = WartTestTraverser(TypedMapPartial) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.TypedMapPartial"))
      val foo = TypedMap.empty(TypedKey[String](""))
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
