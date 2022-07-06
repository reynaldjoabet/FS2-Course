ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val fs2Version="3.2.8"
lazy val  munitVersion="0.7.29"
lazy val root = (project in file("."))
  .settings(
    name := "FS2Course"
  )




libraryDependencies += "co.fs2" %% "fs2-core" % fs2Version
libraryDependencies += "co.fs2" %% "fs2-io" % fs2Version
libraryDependencies += "org.scalameta" %% "munit" % munitVersion % Test
libraryDependencies += "org.scalameta" %% "munit-scalacheck" % munitVersion % Test