package dev.zerosum.example

import java.time.{Clock, Duration, ZoneId, ZonedDateTime}

import wvlet.airframe._

import scala.util.Try

trait ApplicationConfig {

  lazy val applicationConfigDesign: Design = newDesign
    .bind[Clock].toInstance(ClockFactory.instance)
}

object ClockFactory {

  lazy val instance: Clock = {
    (maybeFixedZonedDateTime, maybeOffset) match {
      case (Some(zdt), _) =>
        Clock.fixed(zdt.toInstant, zoneId)
      case (None, Some(duration)) =>
        Clock.offset(Clock.systemDefaultZone(), duration)
      case _ =>
        Clock.systemDefaultZone()
    }
  }

  private lazy val zoneId: ZoneId = ZoneId.systemDefault()

  private lazy val maybeFixedZonedDateTime: Option[ZonedDateTime] = for {
    prop <- sys.props.get("clock.fixedDateTime")
    zdt <- Try(ZonedDateTime.parse(prop)).toOption
  } yield zdt

  private lazy val maybeOffset: Option[Duration] = for {
    prop <- sys.props.get("clock.offset")
    duration <- Try(Duration.parse(prop)).toOption
  } yield duration
}