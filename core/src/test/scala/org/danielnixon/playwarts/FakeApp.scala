package org.danielnixon.playwarts

import java.io.File

import akka.actor.ActorSystem
import akka.stream.Materializer
import play.api.http.{ HttpErrorHandler, HttpRequestHandler }
import play.api.mvc.request.RequestFactory
import play.api.{ Application, Configuration, Environment }

import scala.concurrent.Future

object FakeApp {
  def app: Application = {
    new Application {
      override def path: File = ???

      override def classloader: ClassLoader = ???

      override def environment: Environment = ???

      override def configuration: Configuration = ???

      override def actorSystem: ActorSystem = ???

      override implicit def materializer: Materializer = ???

      override def requestFactory: RequestFactory = ???

      override def requestHandler: HttpRequestHandler = ???

      override def errorHandler: HttpErrorHandler = ???

      override def stop(): Future[_] = ???
    }
  }
}
