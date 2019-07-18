package dev.zerosum.example.greeting

import java.time.Clock
import java.time.ZonedDateTime
import javax.inject.Named

interface GreetingService {
    fun greet(): String
}

@Named
class GreetingServiceImpl(
        private val clock: Clock
) : GreetingService {

    override fun greet(): String {
        return when (ZonedDateTime.now(clock).hour) {
            in 0..6 -> "Zzz..."
            in 7..11 -> "Good morning!"
            in 12..17 -> "Good afternoon!"
            in 18..23 -> "Good evening!"
            else -> "Hello, World!!"
        }
    }
}