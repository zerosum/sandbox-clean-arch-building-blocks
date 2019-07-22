package dev.zerosum.example.greeting

import java.time.{Clock, ZonedDateTime}

class GreetingService(val clock: Clock) {

  def greet: String = {
    ZonedDateTime.now(clock).getHour match {
      case h if (0 to 6).contains(h) => "Zzz..."
      case h if (7 to 11).contains(h) => "Good morning!"
      case h if (12 to 17).contains(h) => "Good afternoon!"
      case h if (18 to 23).contains(h) => "Good evening!"
      case _ => "Hello, World!!"
    }
  }
}
