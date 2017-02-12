package org.danielnixon.playwarts

import akka.util.ByteString
import org.scalatest.FunSuite
import org.wartremover.test.WartTestTraverser
import play.api.libs.json.JsValue
import play.api.libs.ws.{ WSCookie, WSResponse }

import scala.xml.Elem

class WSResponsePartialTest extends FunSuite {
  val response: WSResponse = new WSResponse {
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
    assertResult(List("[wartremover:WSResponsePartial] WSResponse#json is disabled"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use WSResponse#xml") {
    val result = WartTestTraverser(WSResponsePartial) {
      response.xml
    }
    assertResult(List("[wartremover:WSResponsePartial] WSResponse#xml is disabled"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
