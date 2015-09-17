import org.brianmckenna.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.SessionPartial
import org.scalatest.FunSuite
import play.api.mvc.Session

class SessionPartialTest extends FunSuite {
  test("can't use Session#get") {
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
