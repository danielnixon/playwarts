import org.wartremover.test.WartTestTraverser
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

  test("can't use TraversableOnce#max") {
    val result = WartTestTraverser(TraversableOnceOps) {
      println(list.max)
    }
    assertResult(List("TraversableOnce#max is disabled - use TraversableOnce#reduceLeftOption instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use TraversableOnce#min") {
    val result = WartTestTraverser(TraversableOnceOps) {
      println(list.min)
    }
    assertResult(List("TraversableOnce#min is disabled - use TraversableOnce#reduceLeftOption instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use TraversableOnce#maxBy") {
    val result = WartTestTraverser(TraversableOnceOps) {
      println(list.maxBy(x => x))
    }
    assertResult(List("TraversableOnce#maxBy is disabled - use TraversableOnce#reduceLeftOption instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use TraversableOnce#minBy") {
    val result = WartTestTraverser(TraversableOnceOps) {
      println(list.minBy(x => x))
    }
    assertResult(List("TraversableOnce#minBy is disabled - use TraversableOnce#reduceLeftOption instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("TraversableOnceOps wart obeys SuppressWarnings") {
    val result = WartTestTraverser(TraversableOnceOps) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.TraversableOnceOps"))
      val foo = {
        println(list.reduceLeft(_ + _))
      }
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

}
