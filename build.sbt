name := "rule"

version := "0.0.1:"

scalaVersion := "2.9.1"

resolvers ++= Seq("twitter4j.org Repository" at "http://twitter4j.org/maven2",
                  "twitter-repo" at "http://maven.twttr.com",
                  "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository")

libraryDependencies ++= Seq("commons-logging" % "commons-logging" % "1.1.1",
                            "commons-lang" % "commons-lang" % "2.6",
                           "commons-configuration" % "commons-configuration" % "1.8",
                           "org.slf4j" % "slf4j-api" % "1.6.4",
                           "mysql" % "mysql-connector-java" % "5.1.18",
                           "org.squeryl" % "squeryl_2.9.1" % "0.9.5",
                           "org.quartz-scheduler" % "quartz" % "2.1.2",
                           "org.msgpack" % "msgpack" % "0.6.4",
                           "org.msgpack" % "msgpack-rpc" % "0.7.0-SNAPSHOT",
                           "org.msgpack" % "msgpack-scala_2.9.1" % "0.6.4",
                           "com.twitter" % "util-eval" % "1.12.13" withSources(),
                           "org.twitter4j" % "twitter4j-core" % "2.2.5",
                           "org.twitter4j" % "twitter4j-stream" % "2.2.5",
                           "net.liftweb" % "lift-util_2.9.1" % "2.4")
