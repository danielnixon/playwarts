package org.danielnixon.playwarts

import org.danielnixon.playwarts.{ InjectedController => InjectedControllerWart }
import org.scalatest.FunSuite
import org.wartremover.test.WartTestTraverser
import play.api.mvc.{ InjectedController => InjectedControllerTrait }

class InjectedControllerTest extends FunSuite {
  test("can't use InjectedController") {
    val result = WartTestTraverser(InjectedControllerWart) {
      trait Foo extends InjectedControllerTrait
    }
    assertResult(List("[wartremover:InjectedController] InjectedController is disabled - use AbstractController instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("InjectedController wart obeys SuppressWarnings") {
    val result = WartTestTraverser(InjectedControllerWart) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.InjectedController"))
      trait Foo extends InjectedControllerTrait
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
