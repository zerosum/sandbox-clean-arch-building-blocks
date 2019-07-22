package dev.zerosum.example.greeting

import java.time.{Clock, ZoneId, ZonedDateTime}

import org.scalatest.FunSpec
import org.scalatest.Matchers._

class GreetingServiceSpec extends FunSpec {

  val zoneId: ZoneId = ZoneId.systemDefault()

  def fixedClock(at: ZonedDateTime): Clock = Clock.fixed(at.toInstant, zoneId)

  describe("GreetingService#greet") {
    it("should be \"Good morning!\" before 12:00:00") {
      val at = ZonedDateTime.of(2019, 1, 1, 11, 59, 59, 999, zoneId)
      val tested = new GreetingService(fixedClock(at))

      tested.greet should be("Good morning!")
    }
  }
}
