name := "tfpl-1234567t"
version := "1.2.8"

scalaVersion := "2.12.10"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.1.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.1" % "test"

logBuffered in Test := false

parallelExecution in Test := false

unmanagedResourceDirectories in Compile += baseDirectory.value / "data"
unmanagedResourceDirectories in Compile += baseDirectory.value / "docs"
unmanagedResourceDirectories in Compile += baseDirectory.value / "csp"

testOptions in Test ++= Seq(
  Tests.Argument(TestFrameworks.ScalaTest, "-fWDT", s"docs/test-report_${name.value}.txt","-eNDXEHLO","-C", "lecture.TfplReporter")
)


