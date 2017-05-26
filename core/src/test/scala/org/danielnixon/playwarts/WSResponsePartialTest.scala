package org.danielnixon.playwarts

import org.scalatest.FunSuite
import org.wartremover.test.WartTestTraverser
import play.api.libs.ws.WSResponse

class WSResponsePartialTest extends FunSuite {
  def response: WSResponse = ???

  test("can't use WSResponse#json") {
    val result = WartTestTraverser(WSResponsePartial) {
      response.json
    }
    assertResult(List("[wartremover:WSResponsePartial] WSResponse#json is disabled"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use WSResponse#xml") {
    val result = WartTestTraverser(WSResponsePartial) {
      response.xml
    }
    assertResult(List("[wartremover:WSResponsePartial] WSResponse#xml is disabled"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
