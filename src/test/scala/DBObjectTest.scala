import org.brianmckenna.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.DBObject
import org.scalatest.FunSuite
import play.api.Application

class DBObjectTest extends FunSuite {

  implicit val app = FakeApp.app

  test("can't use play.api.db.DB object") {
    val result = WartTestTraverser(DBObject) {
      play.api.db.DB.getDataSource()
    }
    assertResult(List("play.api.db.DB is disabled - use DBApi or Database instead", "play.api.db.DB is disabled - use DBApi or Database instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("DBObject wart obeys SuppressWarnings") {
    val result = WartTestTraverser(DBObject) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.DBObject"))
      val foo = play.api.db.DB.getDataSource()
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
