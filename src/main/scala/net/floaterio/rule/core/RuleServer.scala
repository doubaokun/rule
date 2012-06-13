package net.floaterio.rule.core

import org.msgpack.ScalaMessagePack
import org.msgpack.rpc.loop.EventLoop
import org.msgpack.rpc.Server
import org.apache.commons.logging.LogFactory

/**
 * Created with IntelliJ IDEA.
 * User: Izumi
 * Date: 12/05/31
 * Time: 23:59
 * To change this template use File | Settings | File Templates.
 */

trait RuleServer {

  def startCollectTweet

}

class RuleServerImpl extends RuleServer {

  val log = LogFactory.getLog(classOf[RuleServerImpl])
  val tweetCollector = DependencyFactory.tweetCollector.vend

  {
    ScalaMessagePack.init()
    val loop = EventLoop.start(ScalaMessagePack.messagePack)
    val server = new Server(loop)
    server.serve(this)
    server.listen(8100)
  }

  def startCollectTweet = {

    // TODO
//    filterManager.filterMap.foreach {f =>
//      tweetCollector += f._2.eventReceiver
//    }
    tweetCollector.start()
  }


}
