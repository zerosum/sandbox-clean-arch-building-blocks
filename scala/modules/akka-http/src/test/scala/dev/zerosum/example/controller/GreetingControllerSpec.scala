package dev.zerosum.example.controller

import akka.http.scaladsl.testkit.ScalatestRouteTest
import dev.zerosum.example.greeting.GreetingService
import org.scalamock.scalatest.MockFactory
import org.scalatest._
import wvlet.airframe._

class GreetingControllerSpec extends FunSpec with Matchers with MockFactory with ScalatestRouteTest {

  // mock
  private val greetingService = stub[GreetingService]

  val design: Design = newDesign
    .bind[GreetingService].toInstance(greetingService)

  design.build[GreetingController] { tested =>
    describe("GreetingController") {
      it("[GET /] should return some greet") {

        // when
        (greetingService.greet _).when().returns("__greet__")

        // then
        Get("/") ~> tested.route ~> check {
          responseAs[String] shouldEqual "__greet__"
        }
      }
    }
  }
}
