package net.floaterio.rule.twitter

import java.util.concurrent._
import java.util.Date
import twitter4j.{StatusUpdate, Twitter}


/**
 * Created with IntelliJ IDEA.
 * User: Izumi
 * Date: 12/06/03
 * Time: 2:16
 * To change this template use File | Settings | File Templates.
 */

trait TweetQueue {

  def tweet(status: String): Unit
  def tweet(status: String, delay: Int): Unit
  def reply(status: String, inReplyToStatusId: Long): Unit
  def reply(status: String, inReplyToStatusId: Long, delay: Int): Unit

}

case class TweetResult(result: Boolean)

sealed trait TweetCommand extends Delayed {
  def status: String
  val delay: Int
  val target = new Date().getTime + delay * 1000
  def getDelay(p1: TimeUnit) = {
    val diff = target - new Date().getTime
    p1.convert(diff, TimeUnit.MILLISECONDS)
  }
  def compareTo(p1: Delayed): Int = {
    p1 match {
      case t:TweetCommand => (target - t.target).toInt
      case _ => 0
    }
  }
}

case class TweetCmd(status: String, delay: Int) extends TweetCommand
case class ReplyCmd(status: String, inReplyToStatusId: Long, delay: Int) extends TweetCommand


class TweetQueueImpl(twitter: Twitter) {

  val queue = new DelayQueue[TweetCommand]()
  val executor = Executors.newSingleThreadExecutor()
  var future: Future[_] = null

  def tweet(status: String):Unit = {
    tweet(status, 0)
  }
  def tweet(status: String, delay: Int):Unit = {
    queue.add(TweetCmd(status, delay))
  }
  def reply(status: String, inReplyToStatusId: Long):Unit = {
    reply(status, inReplyToStatusId, 0)
  }
  def reply(status: String, inReplyToStatusId: Long, delay: Int):Unit = {
    queue.add(ReplyCmd(status, inReplyToStatusId, delay))
  }

  def startConsumer = {
    future = executor.submit(new Runnable {
      def run() {
        while(true) {
          queue.take() match {
            case t:TweetCmd => {
              doIgnoreException {
                twitter.updateStatus(t.status)
              }
            }
            case r:ReplyCmd => {
              doIgnoreException {
                val s = new StatusUpdate(r.status)
                s.setInReplyToStatusId(r.inReplyToStatusId)
                twitter.updateStatus(s)
              }
            }
            case _ => {
              println("not implemented")
            }
          }
        }
      }
    })
  }

  def stopConsumer = {
    if (future != null) {
      future.cancel(false)
    }
  }

  def doIgnoreException(f: => Unit)= {
    try {
      f
      true
    } catch {
      case e: Exception => false
    }
  }

}
