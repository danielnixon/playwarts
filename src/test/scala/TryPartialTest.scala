import org.brianmckenna.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.TryPartial
import org.scalatest.FunSuite

import scala.util.Try

class TryPartialTest extends FunSuite {

  test("can't use Try#get") {
    val result = WartTestTraverser(TryPartial) {
      val foo = Try {
        1 / 0
      }.get
    }
    assertResult(List("Try#get is disabled - use Try#getOrElse instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("TryPartial wart obeys SuppressWarnings") {
    val result = WartTestTraverser(TryPartial) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.TryPartial"))
      val foo = Try {
        1 / 0
      }.get
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
