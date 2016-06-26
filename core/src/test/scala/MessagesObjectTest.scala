import org.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.MessagesObject
import org.scalatest.FunSuite

class MessagesObjectTest extends FunSuite {

  implicit val app = FakeApp.app

  test("can't use play.api.i18n.Messages#Implicits") {
    val result = WartTestTraverser(MessagesObject) {
      val foo = play.api.i18n.Messages.Implicits
    }
    assertResult(List("play.api.i18n.Messages.Implicits is disabled - use dependency injection instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("MessagesObject wart obeys SuppressWarnings") {
    val result = WartTestTraverser(MessagesObject) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.MessagesObject"))
      val foo = play.api.i18n.Messages.Implicits
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
