package dev.zerosum.example.test.helper

import java.time.Clock
import java.time.ZoneId
import java.time.ZonedDateTime

val zoneId: ZoneId = ZoneId.systemDefault()

fun fixedClock(at: ZonedDateTime): Clock {
    return Clock.fixed(at.toInstant(), zoneId)
}
