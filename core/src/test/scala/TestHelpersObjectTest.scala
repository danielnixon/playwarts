import org.brianmckenna.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.TestHelpersObject
import org.scalatest.FunSuite
import play.api.mvc.RequestHeader

class TestHelpersObjectTest extends FunSuite {

  implicit val app = FakeApp.app

  test("can use play.api.test.Helpers#route with two args") {
    val result = WartTestTraverser(TestHelpersObject) {
      play.api.test.Helpers.route(null: play.api.Application, null)(null)
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can use play.api.test.Helpers#route with three args") {
    val result = WartTestTraverser(TestHelpersObject) {
      play.api.test.Helpers.route(null: play.api.Application, null, null)(null)
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use play.api.test.Helpers#route with one arg") {
    val result = WartTestTraverser(TestHelpersObject) {
      play.api.test.Helpers.route(null)(null)
    }
    assertResult(List("Use a variant of play.api.test.Helpers#route that accepts a play.api.Application parameter."), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use play.api.test.Helpers#route with two args") {
    val result = WartTestTraverser(TestHelpersObject) {
      play.api.test.Helpers.route(null: RequestHeader, null)(null)
    }
    assertResult(List("Use a variant of play.api.test.Helpers#route that accepts a play.api.Application parameter."), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
