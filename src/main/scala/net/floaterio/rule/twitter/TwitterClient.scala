package net.floaterio.rule.twitter

import org.apache.commons.logging.LogFactory
import twitter4j._

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/17
 * Time: 3:27
 * To change this template use File | Settings | File Templates.
 */

class TwitterClient(twitter: Twitter) {

  val log = LogFactory.getLog(getClass)

  def tryO[T](t: T): Option[T] = {
    try {
      Some(t)
    } catch {
      case e: TwitterException => log.warn("twitter error" + e)
      None
    }
  }
}

object TwitterClient {
  implicit def toClient(twitter: Twitter) = new TwitterClient(twitter)
}
