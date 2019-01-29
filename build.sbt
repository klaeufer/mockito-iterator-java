name := "issue-mockito-java"

version := "0.1"

javacOptions ++= Seq.empty

libraryDependencies ++= Seq(
  "com.novocode" % "junit-interface" % "0.11"   % Test,
  "org.mockito"  % "mockito-inline"  % "2.23.4" % Test
)
