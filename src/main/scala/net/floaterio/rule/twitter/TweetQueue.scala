package net.floaterio.rule.twitter

import java.util.concurrent._
import java.util.Date
import twitter4j.{Status, TwitterException, StatusUpdate}
import net.floaterio.rule.database.model._
import net.floaterio.rule.core.DependencyFactory


/**
 * Created with IntelliJ IDEA.
 * User: Izumi
 * Date: 12/06/03
 * Time: 2:16
 * To change this template use File | Settings | File Templates.
 */

trait TweetQueue {

  def tweet(status: String): TweetStatus
  def tweet(status: String, delay: Int): TweetStatus
  def reply(status: String, inReplyToStatusId: Long, inReplyToUserId: Long): TweetStatus
  def reply(status: String, inReplyToStatusId: Long, inReplyToUserId: Long, delay: Int): TweetStatus

}

case class TweetResult(result: Boolean)

case class TweetCommand(tweetStatus: TweetStatus) extends Delayed {
  val target = new Date().getTime + tweetStatus.delay * 1000
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

class TweetQueueImpl extends TweetQueue {

  val twitter = DependencyFactory.twitter.vend
  val tweetStatusDao = DependencyFactory.tweetStatusDao.vend

  val queue = new DelayQueue[TweetCommand]()
  val executor = Executors.newSingleThreadExecutor()
  var future: Future[_] = null

  def tweet(status: String):TweetStatus = {
    tweet(status, 0)
  }

  def tweet(status: String, delay: Int):TweetStatus = {
    val ts = TweetStatus.createTweet(status, delay)
    val saved = tweetStatusDao.insert(ts)
    queue.add(TweetCommand(saved))
    saved
  }

  def reply(status: String, inReplyToStatusId: Long, inReplyToUserId: Long):TweetStatus = {
    reply(status, inReplyToStatusId, inReplyToUserId, 0)
  }

  def reply(status: String, inReplyToStatusId: Long, inReplyToUserId: Long, delay: Int):TweetStatus = {
    val ts = TweetStatus.createReply(status, inReplyToStatusId, inReplyToUserId, delay)
    val saved = tweetStatusDao.insert(ts)
    queue.add(TweetCommand(saved))
    saved
  }

  def startConsumer = {
    future = executor.submit(new Runnable {
      def run() {
        while(true) {
          val t = queue.take().tweetStatus
          doInTwitter(t) {
            TweetType(t.tweetType) match {
              case TweetType.TWEET => {
                twitter.updateStatus(t.status)
              }
              case TweetType.REPLY  => {
                val s = new StatusUpdate(t.status)
                s.setInReplyToStatusId(t.targetStatusId)
                twitter.updateStatus(s)
              }
//              case _ => {
//                println("not implemented")
//              }
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

  def doInTwitter(tweetStatus: TweetStatus)(f: => Status) = {
    try {
      val result = f
      tweetStatus.createdInTwitter = result.getCreatedAt
      tweetStatus.idInTwitter = result.getId
      tweetStatus.statusType = StatusType.UPDATED.id
    } catch {
      case e: TwitterException => {
        tweetStatus.statusType = StatusType.ERROR.id
        tweetStatus.errorReason = e.getExceptionCode()
        false
      }
    }
  }

}
