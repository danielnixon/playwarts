import org.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.LangObject
import org.scalatest.FunSuite

class LangObjectTest extends FunSuite {
  test("can't use play.api.i18n.Lang object") {
    val result = WartTestTraverser(LangObject) {
      play.api.i18n.Lang.apply("en")
    }
    assertResult(List("play.api.i18n.Lang is disabled - use play.api.i18n.Langs instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("LangObject wart obeys SuppressWarnings") {
    val result = WartTestTraverser(LangObject) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.LangObject"))
      val foo = play.api.i18n.Lang.apply("en")
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
