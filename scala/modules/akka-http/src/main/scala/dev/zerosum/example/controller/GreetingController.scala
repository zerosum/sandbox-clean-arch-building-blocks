package dev.zerosum.example.controller

import dev.zerosum.example.greeting.GreetingService
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

class GreetingController(val greetingService: GreetingService) {

  def route: Route = path("") {
    get {
      complete(
        HttpEntity(
          ContentTypes.`text/plain(UTF-8)`,
          greetingService.greet
        )
      )
    }
  }
}
