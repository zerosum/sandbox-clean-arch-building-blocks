package dev.zerosum.example

import akka.http.scaladsl.server.{Directives, Route}
import dev.zerosum.example.controller.GreetingController
import wvlet.airframe._

trait Routes extends Directives {

  private lazy val greetingController = bind[GreetingController]

  def routes: Route = greetingController.route

}
