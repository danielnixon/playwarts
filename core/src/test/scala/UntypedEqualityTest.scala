import org.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.UntypedEquality
import org.scalatest.FunSuite

class UntypedEqualityTest extends FunSuite {
  test("can't use eq operator") {
    val result = WartTestTraverser(UntypedEquality) {
      val foo = "" eq ""
    }
    assertResult(List("Untyped equality is disabled - use a typesafe alternative"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use ne operator") {
    val result = WartTestTraverser(UntypedEquality) {
      val foo = "" ne ""
    }
    assertResult(List("Untyped equality is disabled - use a typesafe alternative"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use equals operator") {
    val result = WartTestTraverser(UntypedEquality) {
      val foo = "" equals ""
    }
    assertResult(List("Untyped equality is disabled - use a typesafe alternative"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
