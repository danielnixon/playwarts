import org.brianmckenna.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.TraversableOnceOps
import org.scalatest.FunSuite

import scala.collection.TraversableOnce

class TraversableOnceOpsTest extends FunSuite {

  val list: TraversableOnce[Int] = Nil

  test("can't use TraversableOnce#reduceLeft") {
    val result = WartTestTraverser(TraversableOnceOps) {
      println(list.reduceLeft(_ + _))
    }
    assertResult(List("TraversableOnce#reduceLeft is disabled - use TraversableOnce#reduceLeftOption or TraversableOnce#foldLeft instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("TraversableOnceOps wart obeys SuppressWarnings") {
    val result = WartTestTraverser(TraversableOnceOps) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.TraversableOnceOps"))
      val foo = {
        println(list.reduceLeft(_ + _))
      }
    }
    expectResult(List.empty, "result.errors")(result.errors)
    expectResult(List.empty, "result.warnings")(result.warnings)
  }

}
