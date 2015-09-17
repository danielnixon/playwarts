import org.brianmckenna.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.CryptoObject
import org.scalatest.FunSuite

class CryptoObjectTest extends FunSuite {
  test("can't use play.api.libs.Crypto object") {
    val result = WartTestTraverser(CryptoObject) {
      play.api.libs.Crypto.encryptAES("foo")
    }
    assertResult(List("play.api.libs.Crypto object is disabled - use play.api.libs.Crypto class instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("CryptoObject wart obeys SuppressWarnings") {
    val result = WartTestTraverser(CryptoObject) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.CryptoObject"))
      val foo = play.api.libs.Crypto.encryptAES("foo")
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
