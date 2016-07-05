import java.io.File

import akka.actor.ActorSystem
import akka.stream.Materializer
import org.wartremover.test.WartTestTraverser
import org.danielnixon.playwarts.JavaApi
import org.scalatest.FunSuite
import play.api
import play.api.{Application, ApplicationLoader}
import play.api.http.{HttpErrorHandler, HttpRequestHandler}

import scala.concurrent.Future
import scala.concurrent.duration.Duration

class JavaApiTest extends FunSuite {
  test("can't use play.cache package") {
    val result = WartTestTraverser(JavaApi) {
      val foo = new play.cache.NamedCacheImpl("")
    }
    assertResult(List("The Java API is disabled - use the Scala API"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can use play.api.cache package") {
    val result = WartTestTraverser(JavaApi) {
      val foo = new play.api.cache.CacheApi {
        override def set(key: String, value: Any, expiration: Duration): Unit = ???

        override def get[T](key: String)(implicit evidence$2: ClassManifest[T]): Option[T] = ???

        override def getOrElse[A](key: String, expiration: Duration)(orElse: => A)(implicit evidence$1: ClassManifest[A]): A = ???

        override def remove(key: String): Unit = ???
      }
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use play.db package") {
    val result = WartTestTraverser(JavaApi) {
      val foo = new play.db.NamedDatabaseImpl("")
    }
    assertResult(List("The Java API is disabled - use the Scala API"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can use play.api.db package") {
    val result = WartTestTraverser(JavaApi) {
      val foo = new play.api.db.slick.DefaultSlickApi(null, null, null)
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use play.http package") {
    val result = WartTestTraverser(JavaApi) {
      val foo = new play.http.DefaultHttpErrorHandler(null, null, null, null)
    }
    assertResult(List("The Java API is disabled - use the Scala API"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use play.i18n package") {
    val result = WartTestTraverser(JavaApi) {
      val foo = new play.i18n.Messages(null, null)
    }
    assertResult(List("The Java API is disabled - use the Scala API"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use play.inject package") {
    val result = WartTestTraverser(JavaApi) {
      val foo = new play.inject.Bindings
    }
    assertResult(List("The Java API is disabled - use the Scala API"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use play.libs package") {
    val result = WartTestTraverser(JavaApi) {
      val foo = play.libs.Json.newDefaultMapper
    }
    assertResult(List("The Java API is disabled - use the Scala API"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use play.mvc package") {
    val result = WartTestTraverser(JavaApi) {
      val foo = new play.mvc.Call {
        override def fragment(): String = ???

        override def url(): String = ???

        override def method(): String = ???
      }
    }
    assertResult(List("The Java API is disabled - use the Scala API"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use play.libs.mailer package") {
    val result = WartTestTraverser(JavaApi) {
      val foo = new play.libs.mailer.Attachment("", new File(""))
    }
    assertResult(List("The Java API is disabled - use the Scala API", "The Java API is disabled - use the Scala API"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can use play.api.libs package") {
    val result = WartTestTraverser(JavaApi) {
      val foo = new play.api.libs.mailer.AttachmentFile("", new File(""))
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use play.Application class") {
    val result = WartTestTraverser(JavaApi) {
      val foo = new play.Application {
        override def injector(): play.inject.Injector = ???

        override def getWrappedApplication: Application = ???

        override def configuration(): play.Configuration = ???
      }
    }
    assertResult(List("The Java API is disabled - use the Scala API"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can use play.api.Application class") {
    val result = WartTestTraverser(JavaApi) {
      val foo = new play.api.Application {
        override def path: File = ???

        override def actorSystem: ActorSystem = ???

        override def errorHandler: HttpErrorHandler = ???

        override def stop(): Future[_] = ???

        override def requestHandler: HttpRequestHandler = ???

        override def classloader: ClassLoader = ???

        override def mode: play.api.Mode.Mode = ???

        override def configuration: api.Configuration = ???

        override implicit def materializer: Materializer = ???
      }
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use play.ApplicationLoader class") {
    val result = WartTestTraverser(JavaApi) {
      val foo = new play.ApplicationLoader {
        override def load(context: play.ApplicationLoader.Context): play.Application = ???
      }
    }
    assertResult(List("The Java API is disabled - use the Scala API"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can use play.api.ApplicationLoader class") {
    val result = WartTestTraverser(JavaApi) {
      val foo = new play.api.ApplicationLoader {
        override def load(context: ApplicationLoader.Context): Application = ???
      }
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use play.Configuration class") {
    val result = WartTestTraverser(JavaApi) {
      val foo = play.Configuration.empty
    }
    assertResult(List("The Java API is disabled - use the Scala API"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can use play.api.Configuration class") {
    val result = WartTestTraverser(JavaApi) {
      val foo = play.api.Configuration.empty
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use play.DefaultApplication class") {
    val result = WartTestTraverser(JavaApi) {
      val foo = new play.DefaultApplication(null, null).configuration
    }
    assertResult(List("The Java API is disabled - use the Scala API"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can use play.api.DefaultApplication class") {
    val result = WartTestTraverser(JavaApi) {
      val foo = new play.api.DefaultApplication(null, null, null, null, null, null, null, null).configuration
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use play.Environment class") {
    val result = WartTestTraverser(JavaApi) {
      val foo = play.Environment.simple()
    }
    assertResult(List("The Java API is disabled - use the Scala API"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can use play.api.Environment class") {
    val result = WartTestTraverser(JavaApi) {
      val foo = play.api.Environment.simple()
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can't use play.Logger class") {
    val result = WartTestTraverser(JavaApi) {
      val foo = play.Logger.of("")
    }
    assertResult(List("The Java API is disabled - use the Scala API"), "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("can use play.api.Logger class") {
    val result = WartTestTraverser(JavaApi) {
      val foo = play.api.Logger.logger
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }

  test("JavaApi wart obeys SuppressWarnings") {
    val result = WartTestTraverser(JavaApi) {
      @SuppressWarnings(Array("org.danielnixon.playwarts.JavaApi"))
      val foo = new play.cache.NamedCacheImpl("")
    }
    assertResult(List.empty, "result.errors")(result.errors)
    assertResult(List.empty, "result.warnings")(result.warnings)
  }
}
