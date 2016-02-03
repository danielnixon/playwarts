import org.brianmckenna.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.ScalaGlobalExecutionContext
import org.scalatest.FunSuite

class ScalaGlobalExecutionContextTest extends FunSuite {
  test("can't use global explicit execution context") {
    val result = WartTestTraverser(ScalaGlobalExecutionContext) {
      val foo = scala.concurrent.ExecutionContext.global
    }
    assertResult(List("ExecutionContext#global is disabled - declare a dependency on an ExecutionContext instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use global implicit execution context") {
    val result = WartTestTraverser(ScalaGlobalExecutionContext) {
      val foo = scala.concurrent.ExecutionContext.Implicits
    }
    assertResult(List("ExecutionContext#Implicits is disabled - declare a dependency on an ExecutionContext instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("ScalaGlobalExecutionContext wart obeys SuppressWarnings") {
    val result = WartTestTraverser(ScalaGlobalExecutionContext) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.ScalaGlobalExecutionContext"))
      val foo = scala.concurrent.ExecutionContext.global
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
