name := "shading-databricks"

version := "1.0"

scalaVersion := "2.10.6"

libraryDependencies ++= Seq(
  "com.amazonaws" % "aws-java-sdk" % "1.9.40" % "provided",
  "com.amazonaws" % "aws-java-sdk-kinesis" % "1.10.59"
)

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)

assemblyMergeStrategy in assembly := {
  case PathList("javax", "servlet", xs@_*) => MergeStrategy.first
  case PathList(ps@_*) if ps.last endsWith ".html" => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith "pom.properties"                 => MergeStrategy.first
  case PathList("META-INF", "DEPENDENCIES")                                   => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".html"                          => MergeStrategy.first
  case x if x.endsWith("pom.properties")                                      => MergeStrategy.last
  case x if x.endsWith("log4j.properties")                                    => MergeStrategy.last
  case "application.conf"                                                     => MergeStrategy.concat
  case "unwanted.txt"                                                         => MergeStrategy.discard
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

assemblyShadeRules in assembly := Seq(
  ShadeRule.rename("com.amazonaws.**" -> "com.amazonawsv10.@1").inLibrary("com.amazonaws" % "aws-java-sdk-kinesis" % "1.10.59").inAll,
  ShadeRule.rename("com.amazonaws.**" -> "com.amazonawsv10.@1").inLibrary("com.amazonaws" % "aws-java-sdk-core" % "1.10.59").inAll
)

//logLevel in assembly := Level.Debug