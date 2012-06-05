package net.floaterio.rule.database.dao

import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Table
import twitter4j.Status
import net.floaterio.rule.database.model.{StatusType, User, TweetStatus}
import java.util.Date


/**
 * Created with IntelliJ IDEA.
 * User: Izumi
 * Date: 12/06/05
 * Time: 18:51
 * To change this template use File | Settings | File Templates.
 */

trait TweetStatusDao {

  def findByPk(id: Long) : Option[TweetStatus]

  def selectByTargetUser(userId: Long, start: Int, count: Int): List[TweetStatus]

  def create(status: TweetStatus): TweetStatus

  def update(status: TweetStatus): Unit

  def updateOnSuccessTweet(status: TweetStatus, origin: Status): Unit

  def updateOnErrorTweet(status: TweetStatus, reason: String): Unit

}

class TweetStatusDaoImpl(table: Table[TweetStatus]) extends TweetStatusDao{

  def findByPk(id: Long): Option[TweetStatus] = {
    from(table)(t =>
      where(t.id === id).select(t)
    ).headOption
  }

  def selectByTargetUser(userId: Long, start: Int, count: Int): List[TweetStatus] = {
    from(table)(t =>
      where(t.targetUserId === userId).select(t)
    ).page(start, count).toList
  }

  def create(status: TweetStatus) = {
    transaction {
      table.insert(status)
    }
  }

  def update(status: TweetStatus) = {
    transaction {
      table.update(status)
    }
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
