import org.brianmckenna.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.{GlobalSettings => GlobalSettingsWart}
import org.scalatest.FunSuite
import play.api.GlobalSettings

class GlobalSettingsTest extends FunSuite {
  test("can't declare GlobalSettings classes") {
    val result = WartTestTraverser(GlobalSettingsWart) {
      class Settings extends GlobalSettings
    }
    assertResult(List("play.api.GlobalSettings is disabled - use dependency injection instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
  test("can't declare GlobalSettings objects") {
    val result = WartTestTraverser(GlobalSettingsWart) {
      object Settings extends GlobalSettings
    }
    assertResult(List("play.api.GlobalSettings is disabled - use dependency injection instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
  test("can use user-defined GlobalSettings traits") {
    val result = WartTestTraverser(GlobalSettingsWart) {
      trait GlobalSettings
      object Settings extends GlobalSettings
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
  test("GlobalSettings wart obeys SuppressWarnings") {
    val result = WartTestTraverser(GlobalSettingsWart) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.GlobalSettings"))
      object Settings extends GlobalSettings
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
