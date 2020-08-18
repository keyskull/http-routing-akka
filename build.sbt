name := "http-routing-akka"

version := "0.1"

scalaVersion := "2.13.3"

herokuAppName in Compile := "stark-peak-41430"


lazy val akkaVersion = "2.6.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
  "org.scalatest" %% "scalatest" % "3.1.0" % Test
)




herokuProcessTypes in Compile := Map(
  "web" -> "target/universal/stage/bin/my-app -Dhttp.port=$PORT",
  "worker" -> "java -jar target/universal/stage/lib/my-worker.jar"
)

herokuAppName in Compile := Map(
  "testing"    -> "http-routing-testing",
  "staging"    -> "http-routing-staging",
  "production" -> "http-routing"
).getOrElse(git.gitCurrentBranch.value, "http-routing-dev")

showCurrentGitBranch

enablePlugins(JavaAppPackaging)
enablePlugins(GitVersioning)