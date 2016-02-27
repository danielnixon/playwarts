import org.brianmckenna.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.CacheObject
import org.scalatest.FunSuite

class CacheObjectTest extends FunSuite {

  implicit val app = FakeApp.app

  test("can't use play.api.cache.Cache object") {
    val result = WartTestTraverser(CacheObject) {
      play.api.cache.Cache.get("foo")
    }
    assertResult(List("play.api.cache.Cache is disabled - use play.api.cache.CacheApi instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("CacheObject wart obeys SuppressWarnings") {
    val result = WartTestTraverser(CacheObject) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.CacheObject"))
      val foo = play.api.cache.Cache.get("foo")
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
