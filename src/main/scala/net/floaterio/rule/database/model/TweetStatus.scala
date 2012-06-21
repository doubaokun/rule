package net.floaterio.rule.database.model

import java.util.Date
import net.floaterio.rule.database.base.{DaoBase, BaseEntity}
import org.squeryl.Table
import org.squeryl.PrimitiveTypeMode._
import twitter4j.Status

/**
 * Created with IntelliJ IDEA.
 * User: Izumi
 * Date: 12/06/05
 * Time: 18:39
 * To change this template use File | Settings | File Templates.
 */

class TweetStatus(var id: Long,
                  var status: String,
                  var tweetType: Int,
                  var statusType: Int,
                  var delay: Int,
                  var targetStatusId: Long,
                  var targetUserId: Long,
                  var created: Date,
                  var idInTwitter: Long,
                  var createdInTwitter: Date,
                  var errorReason: String
                  ) extends BaseEntity {

  def this() = this(0, "", TweetType.TWEET.id, StatusType.UPDATING.id,
    0, 0, 0, new Date(), 0, new Date(), "")

}

object TweetStatus {

  def createTweet(status: String, delay: Int) = {
    val s = new TweetStatus()
    s.status = status
    s.delay = delay
    s
  }

  def createReply(status: String, inReplyToStatusId: Long, inReplyToUserId: Long, delay: Int) = {
    val s = new TweetStatus()
    s.status = status
    s.delay = delay
    s.targetStatusId = inReplyToStatusId
    s.targetUserId = inReplyToUserId
    s.tweetType = TweetType.REPLY.id
    s
  }

}

object StatusType extends Enumeration {
  val UPDATING, UPDATED, ERROR = Value
}

object TweetType extends Enumeration {
  val TWEET, REPLY, FOLLOW, UNFOLLOW, BLOCK = Value
}

trait TweetStatusDao extends DaoBase[TweetStatus] {

  def selectByTargetUser(userId: Long, start: Int, count: Int): List[TweetStatus]

  def updateOnSuccessTweet(status: TweetStatus, origin: Status): Unit

  def updateOnErrorTweet(status: TweetStatus, reason: String): Unit

}

class TweetStatusDaoImpl(val table: Table[TweetStatus]) extends TweetStatusDao {

  def selectByTargetUser(userId: Long, start: Int, count: Int): List[TweetStatus] = {
    from(table)(t =>
      where(t.targetUserId === userId).select(t)
    ).page(start, count).toList
  }

  def updateOnSuccessTweet(status: TweetStatus, origin: Status): Unit = {
    status.statusType = StatusType.UPDATED.id
    status.idInTwitter = origin.getId
    status.createdInTwitter = origin.getCreatedAt
    update(status)
  }

  def updateOnErrorTweet(status: TweetStatus, reason: String): Unit = {
    status.statusType = StatusType.ERROR.id
    // TODO
    status.createdInTwitter = new Date()
    update(status)
  }
}
