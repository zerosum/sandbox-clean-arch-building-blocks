ThisBuild / name := "clean-arch-scala"
ThisBuild / scalaVersion := "2.12.8"
ThisBuild / scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlint")

lazy val commonSettings = Seq(
  libraryDependencies ++= {
    val airframeVersion = "19.7.0"

    Seq(
      "org.wvlet.airframe" %% "airframe" % airframeVersion,
      "org.wvlet.airframe" %% "airframe-log" % airframeVersion,

      "org.scalactic" %% "scalactic" % "3.0.8",
      "org.scalatest" %% "scalatest" % "3.0.8" % Test,
      "org.scalamock" %% "scalamock" % "4.3.+" % Test
    )
  }
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .aggregate(`akka-http`)

lazy val `domain` = (project in file("modules/domain"))
  .settings(commonSettings)

lazy val `akka-http` = (project in file("modules/akka-http"))
  .dependsOn(`domain`)
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= {
      val akkaHttpVersion = "10.1.9"
      val akkaStreamVersion = "2.5.23"

      Seq(
        "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
        "com.typesafe.akka" %% "akka-stream" % akkaStreamVersion,

        "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
        "com.typesafe.akka" %% "akka-stream-testkit" % akkaStreamVersion % Test
      )
    }
  )

