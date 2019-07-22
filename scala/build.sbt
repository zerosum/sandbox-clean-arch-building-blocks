ThisBuild / name := "clean-arch-scala"
ThisBuild / scalaVersion := "2.12.8"

lazy val `domain` = (project in file("modules/domain"))
  .settings(
    libraryDependencies ++= {
      val airframeVersion = "19.7.0"
      Seq(
        "org.wvlet.airframe" %% "airframe"
      ).map(_ % airframeVersion) ++ Seq(
        "org.scalactic" %% "scalactic" % "3.0.8",
        "org.scalatest" %% "scalatest" % "3.0.8" % "test"
      )
    }
  )

lazy val `akka-http` = (project in file("modules/akka-http"))
  .dependsOn(`domain`)
  .settings(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http" % "10.1.9",
      "com.typesafe.akka" %% "akka-stream" % "2.5.23"
    )
  )

