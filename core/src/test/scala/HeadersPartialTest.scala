import org.brianmckenna.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.HeadersPartial
import org.scalatest.FunSuite
import play.api.mvc.Headers

class HeadersPartialTest extends FunSuite {
  test("can't use Headers#apply") {
    val result = WartTestTraverser(HeadersPartial) {
      val foo = Headers()("foo")
    }
    assertResult(List("Headers#apply is disabled - use Headers#get instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("doesn't detect other `apply` methods") {
    val result = WartTestTraverser(HeadersPartial) {
      case class A(apply: Int)
      println(A(1).apply)
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("HeadersPartial wart obeys SuppressWarnings") {
    val result = WartTestTraverser(HeadersPartial) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.HeadersPartial"))
      val foo = Headers()("foo")
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
