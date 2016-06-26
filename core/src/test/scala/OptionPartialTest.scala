import org.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.OptionPartial
import org.scalatest.FunSuite

class OptionPartialTest extends FunSuite {

  test("can't use Option#orNull") {
    val result = WartTestTraverser(OptionPartial) {
      val foo = None.orNull
    }
    assertResult(List("Option#orNull is disabled"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("OptionPartial wart obeys SuppressWarnings") {
    val result = WartTestTraverser(OptionPartial) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.OptionPartial"))
      val foo = None.orNull
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
