organization := "com.example"

name := "Omise Sample"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.2"

appengineSettings

val unusedWarnings = (
  "-Ywarn-unused" ::
    "-Ywarn-unused-import" ::
    Nil
  )

scalacOptions ++= PartialFunction.condOpt(CrossVersion.partialVersion(scalaVersion.value)) {
  case Some((2, v)) if v >= 11 => unusedWarnings
}.toList.flatten

Seq(Compile, Test).flatMap(c =>
  scalacOptions in(c, console) --= unusedWarnings
)

scalacOptions ++= "-deprecation" :: "unchecked" :: "-feature" :: Nil

libraryDependencies ++= Seq(
  "org.glassfish.jersey.containers" % "jersey-container-servlet" % "2.25.1",
  "co.omise" % "omise-java" % "2.5.4"
) ++ Seq( // local testing
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "com.google.appengine" % "appengine-api-1.0-sdk" % "1.9.54" % "container",
  "org.eclipse.jetty" % "jetty-webapp" % "9.2.21.v20170120" % "container"
)
