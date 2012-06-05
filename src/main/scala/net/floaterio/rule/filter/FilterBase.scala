package net.floaterio.rule.filter

import twitter4j._
import net.floaterio.rule.twitter._
import java.util.concurrent.TimeUnit
import model.{FollowContext, StatusContext}
import net.floaterio.rule.database.dao.UserDao
import net.floaterio.rule.util.{UserSupplier, ReplyCondition}
import java.util.Date
import scala.util.Random
import net.floaterio.rule.database.model.{TweetStatus, User}

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

//  def timeLine(filter: StatusContext => Unit) {
//    onTimeLineListeners += {
//      t => {
//        filter(StatusContext(t.status,Nil,config))
//      }
//    }
//  }

  var timeline = List[StatusContext => Option[_]]()

//  def mention(filter: StatusContext => Unit) {
//    onMentionListeners += {
//      t => {
//        filter(StatusContext(t.status,
//          tweetCache.getConversationList(t.status),config))
//      }
//    }
//  }

  var mention = List[StatusContext => Option[_]]()
  onMentionListeners += { t =>
    val context = StatusContext(t.status, tweetCache.getConversationList(t.status), config)
    mention.find { m =>
      m.apply(context).isDefined
    }
  }

  @deprecated
  def onlyArrowedUser(filter: StatusContext => Unit): (StatusContext => Unit) = {
    context: StatusContext => {
      // 他の人のリプライは拾わない
      if (!context.status.getText.contains("@")) {
        filter.apply(context)
      }
    }
  }

  val permitted : StatusContext => Option[StatusContext] = {
    context => {
      if (!context.status.getText.contains("@")) {
        Some(context)
      } else {
        None
      }
    }
  }

  @deprecated
  def onlyAppOwner(filter: StatusContext => Unit): (StatusContext => Unit) = {
    context : StatusContext => {
      if(context.isOwnerTweet){
        filter.apply(context)
      }
    }
  }

  val owner: StatusContext => Option[StatusContext] = {
    context : StatusContext => {
      // TODO remove config from context
      if(context.isOwnerTweet){
        Some(context)
      } else {
        None
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

  val tweet: StatusContext => TweetStatus = {
    context => {
      tweetQueue.tweet(context.updateStatus)
    }
  }

  val reply: StatusContext => TweetStatus = {
    context => {
      tweetQueue.reply(toReplyStatus(context.updateStatus, context.screenName), context.statusId, context.userId)
    }
  }

//  def tweet(status: String) = {
//    // updateTwitter.updateStatus(status)
//    tweetQueue.tweet(status)
//  }
//
//  def reply(status: String, screenName: String, inReplyToStatusId: Long) {
////    val statusUpdate = new StatusUpdate(toReplyStatus(status, screenName))
////    statusUpdate.setInReplyToStatusId(inReplyToStatusId)
////    updateTwitter.updateStatus(statusUpdate)
//    tweetQueue.reply(toReplyStatus(status, screenName), inReplyToStatusId)
//  }
//
//  def reply(status: String, screenName: String) {
////    updateTwitter.updateStatus(toReplyStatus(status, screenName))
//    tweetQueue.tweet(toReplyStatus(status, screenName))
//  }

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

  implicit def toCombiner[A, B](f1: A => Option[B]): Combiner[A, B] = {
    new Combiner[A, B](f1)
  }

  // TODO move to reply support
  def filter(condition: ReplyCondition):StatusContext => Option[StatusContext] = {
    s => {
      if (condition(s)) {
        Some(s)
      } else {
        None
      }
    }
  }

//  def convert(converter: ReplyConverter): StatusContext => StatusContext = {
//    s => {
//      converter.apply(s)
//    }
//  }

  def complement: StatusContext => StatusContext = {
    c => {
      val sts = c.updateStatus
      c.updateStatus = try {
        val userName = userDao.getUser(c.userId).map(_.nickname).getOrElse(c.userName)
        sts.format(userName)
      } catch {
        case e:Exception => sts
      }
      c
    }
  }

  val userSupplier = new UserSupplier {
    def getUser(userId: Long): Option[User] = userDao.findByPk(userId)
  }

  // 30分間のうちの頻度
  def withFrequency: StatusContext => Option[StatusContext] = {
    c => {
      // TODO TweetStatusを使用する。targetUserで引っ張ってくる
      // TODO Configuration
      val targetTime = 30
      val th1 = 0.95
      val th2 = 0.9
      val now = new Date().getTime
      val count = c.conversationList.filter(tweet => {
        now - tweet.getCreatedAt.getTime < targetTime * 60 * 1000
      }).size
      // Botの状態で変化させる
      val baseTh = if (c.isRuleTrigger) {
        th1
      } else {
        th2
      }

      val th = math.pow(baseTh, count)
      if (th > Random.nextDouble()) {
        Some(c)
      } else {
        None
      }
    }
  }

  def increment(point: Int):StatusContext => Unit = {
    c => {
      userStatusDao.incrementLikability(c.userId, point)
    }
  }

  def rand(functions: FuncWithWeight*): StatusContext => StatusContext = {
    context => {
      functions.maxBy(_.weight * Random.nextDouble()).f(context)
    }
  }

  def fix(function: StatusContext => StatusContext, weight: Double) = FuncWithWeight(function, weight)
  implicit def normal(function: StatusContext => StatusContext) = FuncWithWeight(function, 1.0)
  implicit def strToFunc(text: String): StatusContext => StatusContext = {
    ctx => {
      ctx.updateStatus = text
      ctx
    }
  }

  implicit def strToFuncWithWeight(text: String): FuncWithWeight = normal(strToFunc(text))

  def likability(statusContext: StatusContext) = {
    userStatusDao.findByUserId(statusContext.userId).map(_.likability).getOrElse(0)
  }

  implicit def daoToUserSupplier(dao: UserDao): UserSupplier = {
    new UserSupplier {
      def getUser(userId: Long): Option[User] = dao.findByPk(userId)
    }
  }

}

class Combiner[A, B](f1: A => Option[B]) {

  def >>>[C](f2: B => Option[C]): A => Option[C] = {
    f1 andThen { op =>
      op.map { b =>
        f2.apply(b)
      }.getOrElse(None)
    }
  }

  def >>[C](f2: B => C): A => Option[C] = {
    f1 andThen { op =>
      op.map { b =>
        f2.apply(b)
      }
    }
  }
}

case class FuncWithWeight(f: StatusContext => StatusContext, weight: Double) {

  def withAction(action: StatusContext => Unit): FuncWithWeight = {
    FuncWithWeight( s => {
      s.afterAction += action
      f(s)
    } , weight)
  }
}



