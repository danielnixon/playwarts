name := "play-warts"

version := "0.1"

scalaVersion := "2.11.2"

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies += "org.brianmckenna" %% "wartremover" % "0.11"

exportJars := true
