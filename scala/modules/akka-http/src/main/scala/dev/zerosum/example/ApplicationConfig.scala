package dev.zerosum.example

import java.time.Clock

import wvlet.airframe._

trait ApplicationConfig {

  lazy val applicationConfigDesign: Design = newDesign.bind[Clock].toInstance(Clock.systemDefaultZone())
}
