val ZioVersion = "1.0.0-RC13"
val ZioKafkaVersion = "0.2.0"
val Specs2Version = "4.7.0"
val PureScriptVersion = "0.12.1"

resolvers += Resolver.sonatypeRepo("releases")
resolvers += Resolver.sonatypeRepo("snapshots")

lazy val root = (project in file("."))
  .settings(
    organization := "ZIO",
    name := "zio-awesome-project",
    version := "0.0.1",
    scalaVersion := "2.12.9",
    maxErrors := 3,
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % ZioVersion,
      "dev.zio" %% "zio-streams" % ZioVersion,
      "dev.zio" %% "zio-kafka" % ZioKafkaVersion,
      "com.github.pureconfig" %% "pureconfig" % PureScriptVersion,
      "org.specs2" %% "specs2-core" % Specs2Version % "test"
    )
  )

// Refine scalac params from tpolecat
scalacOptions --= Seq(
  "-Xfatal-warnings"
)

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)

addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias("chk", "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck")
