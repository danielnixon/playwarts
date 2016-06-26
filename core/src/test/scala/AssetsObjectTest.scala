import org.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.AssetsObject
import org.scalatest.FunSuite
import play.api.http.HttpErrorHandler
import play.api.mvc.{RequestHeader, Result}

import scala.concurrent.Future

class AssetsObjectTest extends FunSuite {
  test("can't use controllers.Assets object") {
    val result = WartTestTraverser(AssetsObject) {
      val foo = controllers.Assets.at("", "")
    }
    assertResult(List("controllers.Assets is disabled - use dependency injection instead", "controllers.Assets is disabled - use dependency injection instead"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can use controllers.Assets class") {
    val result = WartTestTraverser(AssetsObject) {
      val errorHandler = new HttpErrorHandler {
        override def onClientError(request: RequestHeader, statusCode: Int, message: String): Future[Result] = ???

        override def onServerError(request: RequestHeader, exception: Throwable): Future[Result] = ???
      }
      val foo = new controllers.Assets(errorHandler).at("", "")
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("AssetsObject wart obeys SuppressWarnings") {
    val result = WartTestTraverser(AssetsObject) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.AssetsObject"))
      val foo = controllers.Assets.at("", "")
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
