import org.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.JsLookupResultPartial
import org.scalatest.FunSuite
import play.api.libs.json.{JsUndefined, JsLookupResult}

class JsLookupResultPartialTest extends FunSuite {

  val jsLookupResult: JsLookupResult = new JsUndefined("")

  test("can't call JsLookupResult#get") {
    val result = WartTestTraverser(JsLookupResultPartial) {
      val foo = jsLookupResult.get
    }
    assertResult(List("JsLookupResult#get is disabled - use JsLookupResult#getOrElse instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("JsLookupResultPartial wart obeys SuppressWarnings") {
    val result = WartTestTraverser(JsLookupResultPartial) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.JsLookupResultPartial"))
      val foo = jsLookupResult.get
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
