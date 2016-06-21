import akka.util.ByteString
import org.brianmckenna.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.WSResponsePartial
import org.scalatest.FunSuite
import play.api.libs.json.JsValue
import play.api.libs.ws.{WSCookie, WSResponse}

import scala.xml.Elem

class WSResponsePartialTest extends FunSuite {
  val response = new WSResponse {
    override def allHeaders: Map[String, Seq[String]] = ???

    override def statusText: String = ???

    override def underlying[T]: T = ???

    override def xml: Elem = ???

    override def body: String = ???

    override def header(key: String): Option[String] = ???

    override def cookie(name: String): Option[WSCookie] = ???

    override def bodyAsBytes: ByteString = ???

    override def cookies: Seq[WSCookie] = ???

    override def status: Int = ???

    override def json: JsValue = ???
  }

  test("can't use WSResponse#json") {
    val result = WartTestTraverser(WSResponsePartial) {
      response.json
    }
    assertResult(List("WSResponse#json is disabled"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use WSResponse#xml") {
    val result = WartTestTraverser(WSResponsePartial) {
      response.xml
    }
    assertResult(List("WSResponse#xml is disabled"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
