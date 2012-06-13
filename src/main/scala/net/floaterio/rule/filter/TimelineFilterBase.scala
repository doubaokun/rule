package net.floaterio.rule.filter

import twitter4j._
import net.floaterio.rule.twitter._
import java.util.concurrent.TimeUnit
import model.{FollowContext, StatusContext}
import net.floaterio.rule.util.{UserSupplier, ReplyCondition}
import java.util.Date
import scala.util.Random
import net.floaterio.rule.database.model.{TweetStatus, User}
import org.apache.commons.lang.StringUtils
import javax.management.remote.rmi._RMIConnectionImpl_Tie
import org.apache.commons.logging.LogFactory
import net.floaterio.rule.database.dao.{TweetStatusDao, UserStatusDao, UserDao}
import net.floaterio.rule.core.{DependencyFactory, RuleConfiguration}

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/11
 * Time: 15:09
 * To change this template use File | Settings | File Templates.
 */


class TimelineFilterBase extends TimelineFilter {

  val log = LogFactory.getLog(getClass)

  val twitter = DependencyFactory.twitter.vend
  val tweetQueue = DependencyFactory.tweetQueue.vend
  val tweetCache = DependencyFactory.tweetCache.vend
  val userDao = DependencyFactory.userDao.vend
  val userStatusDao = DependencyFactory.userStatusDao.vend
  val tweetStatusDao = DependencyFactory.tweetStatusDao.vend
  val config = DependencyFactory.ruleConfig.vend

  def filterName = getClass.getName
  def mention: List[(StatusContext) => Option[_]] = Nil
  def timeline: List[(StatusContext) => Option[_]] = Nil
  def followState: List[(FollowContext) => Option[_]] = Nil
  def schedule: List[(JobSchedule, (StatusContext) => Option[_])] = Nil

  val tweet: StatusContext => Unit = {
    context => {
      tweetQueue.tweet(context.updateStatus)
    }
  }

  val reply: StatusContext => Unit = {
    context => {
      tweetQueue.reply(toReplyStatus(context.updateStatus, context.screenName), context.statusId, context.userId)
    }
  }

//  def onFollow(filter: FollowContext => Unit) {
//    onFollowListeners += (
//      follow => {
//        filter.apply(FollowContext(follow.user, true))
//      }
//    )
//  }
//
//  def onRemove(filter: FollowContext => Unit) {
//    onRemoveListeners += (
//      follow => {
//        filter.apply(FollowContext(follow.user, false))
//      }
//    )
//  }

  // Helper Method

  val permitted : StatusContext => Option[StatusContext] = {
    context => {
      if (!context.status.getText.contains("@")) {
        Some(context)
      } else {
        None
      }
    }
  }

  val owner: StatusContext => Option[StatusContext] = {
    context : StatusContext => {
      if(context.isOwnerTweet){
        Some(context)
      } else {
        None
      }
    }
  }

  def safeWithTwitter(f: => Unit) = {
    try {
      f
    } catch {
      case e:TwitterException => log.error("twitter error", e)
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

  def pass[T]: T => Option[T] = {
    s => {
      Some(s)
    }
  }

  /**
   * {user} を置き換え
   * @return
   */
  def complement: StatusContext => StatusContext = {
    c => {
      val sts = c.updateStatus
      c.updateStatus = try {
        val userName = userDao.getUser(c.userId).map(_.nickname).getOrElse(c.userName)
        StringUtils.replace(sts, "{user}", userName)
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
  implicit def simpleStatus(text: String): StatusContext => StatusContext = {
    ctx => {
      ctx.updateStatus = text
      ctx
    }
  }

  implicit def strToFuncWithWeight(text: String): FuncWithWeight = normal(simpleStatus(text))

  implicit def daoToUserSupplier(dao: UserDao): UserSupplier = {
    new UserSupplier {
      def getUser(userId: Long): Option[User] = dao.findByPk(userId)
    }
  }

  implicit def decorateContextWithConfig(c: StatusContext): ContextDecoratorWithConfig
    = ContextDecoratorWithConfig(c, config)

  implicit def decorateContextWithConversationList(c: StatusContext): ContextDecoratorWithConversationList
    = ContextDecoratorWithConversationList(c, config, tweetCache)

  implicit def decorateContextWithUserStatus(c: StatusContext): ContextDecoratorWithUserStatus
    = ContextDecoratorWithUserStatus(c, userStatusDao)

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

case class ContextDecoratorWithConfig(c: StatusContext, config: RuleConfiguration) {
  def isOwnerTweet = c.screenName == config.ownerScreenName
  def isRuleTweet = c.screenName == config.botScreenName
}

case class ContextDecoratorWithConversationList(c: StatusContext, config: RuleConfiguration, cache: TweetCache) {
  lazy val conversationList = cache.getConversationList(c.status)
  def triggerUser = conversationList.head.getUser
  def isRuleTrigger = triggerUser.getScreenName == config.botScreenName
}

case class ContextDecoratorWithUserStatus(c: StatusContext, userStatusDao: UserStatusDao) {
  def likability = userStatusDao.findByUserId(c.userId).map(_.likability).getOrElse(0)
}

class NullStatusContext extends StatusContext(null)



