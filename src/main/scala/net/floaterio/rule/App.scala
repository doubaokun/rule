package net.floaterio.rule

import core.RuleServerImpl
import org.apache.commons.logging.LogFactory

object App {

  val log = LogFactory.getLog(getClass)

  def main(args: Array[String]) = {

    val server = new RuleServerImpl()
    server.startCollectTweet

  }
}


