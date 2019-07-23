package dev.zerosum.example.greeting

import java.time.{Clock, ZoneId, ZonedDateTime}

import org.scalatest._
import wvlet.airframe._

class GreetingServiceSpec extends FunSpec with Matchers {

  val zoneId: ZoneId = ZoneId.systemDefault()

  def fixedClockDesign(at: ZonedDateTime): Design = {
    newDesign.bind[Clock].toInstance(Clock.fixed(at.toInstant, zoneId))
  }

  describe("GreetingService#greet") {

    it("""should be "Good morning!" before 12:00:00""") {
      // given
      val at = ZonedDateTime.of(2019, 1, 1, 11, 59, 59, 999, zoneId)

      // when
      fixedClockDesign(at).build[GreetingService] { tested =>

        // then
        tested.greet should be("Good morning!")
      }
    }

    it("""should be "Good afternoon!" at 16:00:00""") {
      // given
      val at = ZonedDateTime.of(2019, 1, 1, 16, 0, 0, 0, zoneId)

      // when
      fixedClockDesign(at).build[GreetingService] { tested =>

        // then
        tested.greet should be("Good afternoon!")
      }
    }

    it("""should be "Good evening!" before 21:00:00""") {
      // given
      val at = ZonedDateTime.of(2019, 1, 1, 21, 0, 0, 0, zoneId)

      // when
      fixedClockDesign(at).build[GreetingService] { tested =>

        // then
        tested.greet should be("Good evening!")
      }
    }

    it("""should be "Zzz..." at midnight""") {
      // given
      val at = ZonedDateTime.of(2019, 2, 1, 1, 0, 0, 0, zoneId)

      // when
      fixedClockDesign(at).build[GreetingService] { tested =>

        // then
        tested.greet should be("Zzz...")
      }
    }

  }
}
