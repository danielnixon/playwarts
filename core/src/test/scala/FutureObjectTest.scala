import org.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.FutureObject
import org.scalatest.FunSuite

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class FutureObjectTest extends FunSuite {

  implicit val app = FakeApp.app

  val futs: List[Future[String]] = Nil

  test("can't use scala.concurrent.Future#reduce") {
    val result = WartTestTraverser(FutureObject) {
      val foo = Future.reduce(futs)((r, t) => r)
    }
    assertResult(List("Future#reduce is disabled - use Future#fold instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("FutureObject wart obeys SuppressWarnings") {
    val result = WartTestTraverser(FutureObject) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.FutureObject"))
      val foo = Future.reduce(futs)((r, t) => r)
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
