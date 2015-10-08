import org.brianmckenna.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.PlayObject
import org.scalatest.FunSuite

class PlayObjectTest extends FunSuite {

  implicit val app = FakeApp.app

  test("can't use play.api.Play#current") {
    val result = WartTestTraverser(PlayObject) {
      play.api.Play.current
    }
    assertResult(List("Play#current is disabled - declare a dependency on Application instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use play.api.Play#maybeApplication") {
    val result = WartTestTraverser(PlayObject) {
      play.api.Play.maybeApplication
    }
    assertResult(List("Play#maybeApplication is disabled - declare a dependency on Application instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use play.api.Play#unsafeApplication") {
    val result = WartTestTraverser(PlayObject) {
      play.api.Play.unsafeApplication
    }
    assertResult(List("Play#unsafeApplication is disabled - declare a dependency on Application instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("PlayObject wart obeys SuppressWarnings") {
    val result = WartTestTraverser(PlayObject) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.PlayObject"))
      val foo = play.api.Play.current
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
