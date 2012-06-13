package net.floaterio.rule.core

import net.liftweb.util.SimpleInjector
import net.floaterio.rule.database.dao._
import net.floaterio.rule.database.DBInitializer
import net.floaterio.rule.util.schedule.{JobSchedulerImpl, JobScheduler}
import twitter4j.{TwitterStream, Twitter, TwitterStreamFactory, TwitterFactory}
import net.floaterio.rule.twitter._

/**
 * Created with IntelliJ IDEA.
 * User: Izumi
 * Date: 12/06/12
 * Time: 19:22
 * To change this template use File | Settings | File Templates.
 */

object DependencyFactory extends SimpleInjector {

  private def _inject[T](value: T)(implicit m:Manifest[T]):Inject[T] = {
    new Inject(value) {}
  }

  // config
  val ruleConfig = _inject[RuleConfiguration](new RuleConfiguration)

  // dao
  val dbInitializer = new DBInitializer() // TODO
  val userDao = _inject[UserDao](new UserDaoImpl(dbInitializer.userTable))
  val userStatusDao = _inject[UserStatusDao](new UserStatusDaoImpl(dbInitializer.userStatusTable))
  val tweetStatusDao = _inject[TweetStatusDao](new TweetStatusDaoImpl(dbInitializer.tweetStatusTable))

  // scheduler
  val jobScheduler = _inject[JobScheduler](new JobSchedulerImpl())

  // tweet
  val twitter = _inject[Twitter](TwitterFactory.getSingleton)
  val twitterStream = _inject[TwitterStream](TwitterStreamFactory.getSingleton)
  val tweetCache = _inject[TweetCache](new TweetCache)
  val tweetCollector = _inject[TweetCollector](new TweetCollectorImpl())
  val tweetQueue = _inject[TweetQueue](new TweetQueueImpl())


}
