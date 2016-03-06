import akka.stream.Materializer
import play.api.Application

object FakeApp {
  def app: Application = {
    new Application {
      override def path = ???

      override def actorSystem = ???

      override def errorHandler = ???

      override def stop() = ???

      override def requestHandler = ???

      override def classloader = ???

      override def mode = ???

      override def configuration = ???

      override implicit def materializer: Materializer = ???
    }
  }
}
