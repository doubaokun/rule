package net.floaterio.rule.filter

import impl._
import net.floaterio.rule.twitter.TwitterComponentFactory
import net.floaterio.rule.database.DaoFactory
import net.floaterio.rule.core.{RuleConfiguration, MiscFactory}

/**
 * Created with IntelliJ IDEA.
 * User: Izumi
 * Date: 12/05/30
 * Time: 16:15
 * To change this template use File | Settings | File Templates.
 */

trait FilterManager {

  def filterMap: Map[String, Filter]

  def getInfo(name: String): Option[FilterStatus]

}

class FilterManagerImpl(configuration: RuleConfiguration,miscFactory: MiscFactory,
                        twitterComponentFactory: TwitterComponentFactory, daoFactory: DaoFactory) {

  import miscFactory._
  import twitterComponentFactory._
  import daoFactory._

  lazy val dependencies = new FilterDependencies(
    twitter,updateTwitter,tweetCache,
    jobScheduler,userDao,userStatusDao,configuration
  )

  // TODO start
  lazy val filterMap = {
    List (
      new ConversationFilter(dependencies),
      new ScheduledFilter(dependencies),
      new FollowCheckFilter(dependencies),
      new GreetingFilter(dependencies),
      new CommandFilter(dependencies)
    ).map { f =>
      f.filterName -> f
    }.toMap
  }

  def getInfo(name: String) = {
    filterMap.get(name).map { f =>
      FilterStatus(f.filterName, f.isResume)
    }
  }


}

case class FilterStatus(name: String, status:Boolean)
case class FilterStatistics()
