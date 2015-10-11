import org.brianmckenna.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.StringOpsPartial
import org.scalatest.FunSuite

class StringOpsPartialTest extends FunSuite {

  test("can't use StringLike#toBoolean") {
    val result = WartTestTraverser(StringOpsPartial) {
      val foo = "foo".toBoolean
    }
    assertResult(List("StringOps#toBoolean is disabled"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use StringLike#toByte") {
    val result = WartTestTraverser(StringOpsPartial) {
      val foo = "foo".toByte
    }
    assertResult(List("StringOps#toByte is disabled"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use StringLike#toShort") {
    val result = WartTestTraverser(StringOpsPartial) {
      val foo = "foo".toShort
    }
    assertResult(List("StringOps#toShort is disabled"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use StringLike#toInt") {
    val result = WartTestTraverser(StringOpsPartial) {
      val foo = "foo".toInt
    }
    assertResult(List("StringOps#toInt is disabled"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use StringLike#toLong") {
    val result = WartTestTraverser(StringOpsPartial) {
      val foo = "foo".toLong
    }
    assertResult(List("StringOps#toLong is disabled"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use StringLike#toFloat") {
    val result = WartTestTraverser(StringOpsPartial) {
      val foo = "foo".toFloat
    }
    assertResult(List("StringOps#toFloat is disabled"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use StringLike#toDouble") {
    val result = WartTestTraverser(StringOpsPartial) {
      val foo = "foo".toDouble
    }
    assertResult(List("StringOps#toDouble is disabled"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("StringLikeOps wart obeys SuppressWarnings") {
    val result = WartTestTraverser(StringOpsPartial) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.StringLikeOps"))
      val foo = "foo".toInt
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
