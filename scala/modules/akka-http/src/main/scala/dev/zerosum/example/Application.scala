package dev.zerosum.example

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.settings.ServerSettings
import akka.stream.ActorMaterializer
import wvlet.airframe._

import scala.concurrent.Future
import scala.util.{Failure, Success}

object Application extends ApplicationConfig {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("example")

    val design = newDesign
      .bind[ActorSystem].toInstance(system)
      .add(applicationConfigDesign)
      .add(controller.createControllerDesign)

    design.withSession { session =>
      session
        .build[HttpServer]
        .start("localhost", 8080, settings = ServerSettings(system))
    }
  }

}

trait HttpServer {
  implicit val system = bind[ActorSystem]
  implicit val mat = ActorMaterializer()
  implicit val ec = system.dispatcher

  private val route = bind[Routes].routes

  def start(host: String,
            port: Int,
            settings: ServerSettings): Future[ServerBinding] = {
    val bindingFuture = Http().bindAndHandle(route, host, port)

    bindingFuture.onComplete {
      case Success(binding) =>
        system.log.info(s"Server online at http://${binding.localAddress.getHostName}:${binding.localAddress.getPort}/")
      case Failure(ex) =>
        system.log.error(ex, "")
    }

    sys.addShutdownHook {
      bindingFuture
        .flatMap(_.unbind())
        .onComplete { _ =>
          mat.shutdown()
          system.terminate()
        }
    }

    bindingFuture
  }
}
