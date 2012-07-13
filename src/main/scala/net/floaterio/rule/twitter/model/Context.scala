package net.floaterio.rule.twitter.model

import twitter4j.{StatusUpdate, Status, User}
import collection.mutable.ListBuffer

/**
 * Created with IntelliJ IDEA.
 * User: Izumi
 * Date: 12/06/24
 * Time: 18:32
 * To change this template use File | Settings | File Templates.
 */

trait Context[T <: Context[T]] {
  var afterAction = ListBuffer[T => Unit]()
  var updateStatus: Option[StatusUpdate] = None
  def setStatus(text: String) = {
    updateStatus = Some(new StatusUpdate(text))
  }
}

case class FollowContext(val user: User, val isFollow: Boolean) extends Context[FollowContext]{
}

case class ScheduleContext() extends Context[ScheduleContext]

case class StatusContext(val status: Status) extends Context[StatusContext]{
  def statusId = status.getId
  // TODO Mentionを除く
  def body = status.getText
  def userName = status.getUser.getName
  def screenName = status.getUser.getScreenName
  def userId = status.getUser.getId
}
