import org.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.{BasicStreamingActionPartial => BasicStreamingActionWart}
import org.scalatest.FunSuite
import slick.dbio.Effect.All
import slick.dbio.NoStream
import slick.profile.BasicStreamingAction

class BasicStreamingActionPartialTest extends FunSuite {

  val basicStreamingAction = new BasicStreamingAction[Nothing, Nothing, All] {
    override def head: ResultAction[Nothing, NoStream, All] = ???

    override def headOption = ???

    override def getDumpInfo = ???
  }

  test("can't call BasicStreamingAction#head") {
    val result = WartTestTraverser(BasicStreamingActionWart) {
      basicStreamingAction.head
    }
    assertResult(List("BasicStreamingAction#head is disabled - use BasicStreamingAction#headOption instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("BasicStreamingActionPartial wart obeys SuppressWarnings") {
    val result = WartTestTraverser(BasicStreamingActionWart) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.BasicStreamingActionPartial"))
      val foo = basicStreamingAction.head
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
