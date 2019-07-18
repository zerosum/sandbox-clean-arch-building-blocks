package dev.zerosum.example.greeting

import dev.zerosum.example.test.helper.fixedClock
import dev.zerosum.example.test.helper.zoneId
import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.time.ZonedDateTime

object GreetingServiceImplTest: Spek({

    describe("GreetingService#greet()") {

        it("should be \"Good morning!\" before 12:00:00") {
            val at = ZonedDateTime.of(2019, 1, 1, 11, 59, 59, 999, zoneId)
            val tested = GreetingServiceImpl(fixedClock(at))

            assertThat(tested.greet()).isEqualTo("Good morning!")
        }

        it("should be \"Good afternoon!\" at 16:00:00") {
            val at = ZonedDateTime.of(2019, 1, 1, 16, 0, 0, 0, zoneId)
            val tested = GreetingServiceImpl(fixedClock(at))

            assertThat(tested.greet()).isEqualTo("Good afternoon!")
        }

        it("should be \"Good evening!\" at 21:00:00") {
            val at = ZonedDateTime.of(2019, 1, 1, 21, 0, 0, 0, zoneId)
            val tested = GreetingServiceImpl(fixedClock(at))

            assertThat(tested.greet()).isEqualTo("Good evening!")
        }

        it("should sleep at 02:00:00") {
            val at = ZonedDateTime.of(2019, 1, 2, 2, 0, 0, 0, zoneId)
            val tested = GreetingServiceImpl(fixedClock(at))

            assertThat(tested.greet()).isEqualTo("Zzz...")
        }
    }
})