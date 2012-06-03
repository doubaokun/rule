package net.floaterio.rule.twitter

import org.apache.commons.logging.LogFactory
import twitter4j.TwitterException

/**
 * Created with IntelliJ IDEA.
 * User: Izumi
 * Date: 12/06/01
 * Time: 3:29
 * To change this template use File | Settings | File Templates.
 */

object TwitterUtil {

  val log = LogFactory.getLog(getClass)

  def tryO[T](t: => T): Option[T] = {
    try {
      Some(t)
    } catch {
      case e: TwitterException => log.warn("twitter error" + e)
      None
    }
  }

}
