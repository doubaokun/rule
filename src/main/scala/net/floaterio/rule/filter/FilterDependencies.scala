package net.floaterio.rule.filter

import twitter4j.Twitter
import net.floaterio.rule.twitter.TweetCache
import net.floaterio.rule.util.schedule.JobScheduler
import net.floaterio.rule.database.dao.{UserStatusDao, UserDao}
import net.floaterio.rule.core.RuleConfiguration

/**
 * Created with IntelliJ IDEA.
 * User: Izumi
 * Date: 12/06/02
 * Time: 1:40
 * To change this template use File | Settings | File Templates.
 */

class FilterDependencies(
  val twitter: Twitter,
  val updateTwitter: Twitter,
  val tweetCache: TweetCache,
  val jobScheduler: JobScheduler,
  val userDao: UserDao,
  val userStatusDao: UserStatusDao,
  val config: RuleConfiguration ){
}
