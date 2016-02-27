import org.brianmckenna.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.FlashPartial
import org.scalatest.FunSuite
import play.api.mvc.Flash

class FlashPartialTest extends FunSuite {
  test("can't use Flash#get") {
    val result = WartTestTraverser(FlashPartial) {
      val foo = Flash()("foo")
    }
    assertResult(List("Flash#apply is disabled - use Flash#get instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("doesn't detect other `apply` methods") {
    val result = WartTestTraverser(FlashPartial) {
      case class A(apply: Int)
      println(A(1).apply)
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("FlashPartial wart obeys SuppressWarnings") {
    val result = WartTestTraverser(FlashPartial) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.FlashPartial"))
      val foo = Flash()("foo")
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
