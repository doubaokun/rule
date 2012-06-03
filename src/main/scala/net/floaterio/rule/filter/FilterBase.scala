package net.floaterio.rule.filter

import twitter4j._
import net.floaterio.rule.twitter._
import java.util.concurrent.TimeUnit
import model.{FollowContext, StatusContext}
import net.floaterio.rule.database.dao.{UserStatusDao, UserDao}
import net.floaterio.rule.core.RuleConfiguration
import net.floaterio.rule.util.schedule.JobScheduler

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/11
 * Time: 15:09
 * To change this template use File | Settings | File Templates.
 */


class FilterBase (dependencies: FilterDependencies) extends Filter {

  def filterName = getClass.getName
  val eventReceiver = new EventReceiver

  import eventReceiver._
  import dependencies._

  def resume = eventReceiver.resume
  def pause = eventReceiver.pause
  def isResume = eventReceiver.status

  def timeLine(filter: StatusContext => Unit) {
    onTimeLineListeners += {
      t => {
        filter(StatusContext(t.status,Nil,config))
      }
    }
  }

  def mention(filter: StatusContext => Unit) {
    onMentionListeners += {
      t => {
        filter(StatusContext(t.status,
          tweetCache.getConversationList(t.status),config))
      }
    }
  }

  def onlyArrowedUser(filter: StatusContext => Unit): (StatusContext => Unit) = {
    context: StatusContext => {
      // 他の人のリプライは拾わない
      if (!context.status.getText.contains("@")) {
        filter.apply(context)
      }
    }
  }

  def onlyAppOwner(filter: StatusContext => Unit): (StatusContext => Unit) = {
    context : StatusContext => {
      if(context.isOwnerTweet){
        filter.apply(context)
      }
    }
  }

  def interval(name: String, span: Int, timeUnit: TimeUnit)(filter: => Unit) {
    scheduleReceiverMap += (name -> (() => {
      filter
    }))
    jobScheduler.addIntervalJob(name, filterName, eventReceiver, span, timeUnit)
  }

  def atJustTime(name: String, cron: String)(filter: => Unit) {
    scheduleReceiverMap += (name -> (() => {
      filter
    }))
    jobScheduler.addCronJob(name, filterName, eventReceiver, cron)
  }

  def tweet(status: String) = {
    updateTwitter.updateStatus(status)
  }

  def reply(status: String, screenName: String, inReplyToStatusId: Long) {
    val statusUpdate = new StatusUpdate(toReplyStatus(status, screenName))
    statusUpdate.setInReplyToStatusId(inReplyToStatusId)
    updateTwitter.updateStatus(statusUpdate)
  }

  def reply(status: String, screenName: String) {
    updateTwitter.updateStatus(toReplyStatus(status, screenName))
  }

  def waitRandomTime(f: => Unit) {
    // TODO delayQueue
  }

  def onFollow(filter: FollowContext => Unit) {
    onFollowListeners += (follow => {
      filter.apply(FollowContext(follow.user, true))
    })
  }

  def onRemove(filter: FollowContext => Unit) {
    onRemoveListeners += (follow => {
      filter.apply(FollowContext(follow.user, false))
    })
  }

  def safeWithTwitter(f: => Unit) = {
    try {
      f
    } catch {
      // TODO logger
      case e:TwitterException => e.printStackTrace()
    }
  }

  def toReplyStatus(status: String, screenName: String) = {
    "@" + screenName + " " + status
  }

}



