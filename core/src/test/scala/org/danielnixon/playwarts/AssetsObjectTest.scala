package org.danielnixon.playwarts

import org.scalatest.FunSuite
import org.wartremover.test.WartTestTraverser

class AssetsObjectTest extends FunSuite {
  test("can't use controllers.Assets object") {
    val result = WartTestTraverser(AssetsObject) {
      val foo = controllers.Assets.at("", "")
    }
    assertResult(List("[wartremover:AssetsObject] controllers.Assets is disabled - use dependency injection instead", "[wartremover:AssetsObject] controllers.Assets is disabled - use dependency injection instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can use controllers.Assets class") {
    val result = WartTestTraverser(AssetsObject) {
      val foo = new controllers.Assets(null, null).at("", "")
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("AssetsObject wart obeys SuppressWarnings") {
    val result = WartTestTraverser(AssetsObject) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.AssetsObject"))
      val foo = controllers.Assets.at("", "")
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
