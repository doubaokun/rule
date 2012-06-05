package net.floaterio.rule.twitter.model

import twitter4j.Status
import net.floaterio.rule.core.RuleConfiguration
import collection.mutable.ListBuffer

/**
 * Created with IntelliJ IDEA.
 * User: Izumi
 * Date: 12/05/30
 * Time: 16:25
 * To change this template use File | Settings | File Templates.
 */

case class StatusContext(val status: Status) {
  var afterAction = ListBuffer[StatusContext => Unit]()
  var updateStatus: String = ""
  def statusId = status.getId
  // TODO Mentionを除く
  def body = status.getText
  def userName = status.getUser.getName
  def screenName = status.getUser.getScreenName
  def userId = status.getUser.getId
}


