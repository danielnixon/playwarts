import org.brianmckenna.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.WSObject
import org.scalatest.FunSuite

class WSObjectTest extends FunSuite {

  implicit val app = FakeApp.app

  test("can't use play.api.libs.ws.WS object") {
    val result = WartTestTraverser(WSObject) {
      play.api.libs.ws.WS.client
    }
    assertResult(List("play.api.libs.ws.WS is disabled - use play.api.libs.ws.WSClient instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("WSObject wart obeys SuppressWarnings") {
    val result = WartTestTraverser(WSObject) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.WSObject"))
      val foo = play.api.libs.ws.WS.client
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
