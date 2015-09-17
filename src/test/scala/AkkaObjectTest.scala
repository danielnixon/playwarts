import org.brianmckenna.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.AkkaObject
import org.scalatest.FunSuite

class AkkaObjectTest extends FunSuite {

  implicit val app = FakeApp.app

  test("can't play.api.libs.concurrent.Akka object") {
    val result = WartTestTraverser(AkkaObject) {
      play.api.libs.concurrent.Akka.system
    }
    assertResult(List("play.api.libs.concurrent.Akka is disabled - declare a dependency on ActorSystem instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("AkkaObject wart obeys SuppressWarnings") {
    val result = WartTestTraverser(AkkaObject) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.AkkaObject"))
      val foo = play.api.libs.concurrent.Akka.system
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
