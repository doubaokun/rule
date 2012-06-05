package net.floaterio.rule.core

import net.floaterio.rule.database.DaoFactoryImpl
import net.floaterio.rule.filter.FilterManagerImpl
import net.floaterio.rule.twitter.TwitterComponentFactoryImpl

/**
 * Created with IntelliJ IDEA.
 * User: Izumi
 * Date: 12/05/30
 * Time: 16:37
 * To change this template use File | Settings | File Templates.
 */

class BridgeFactory {

  // TODO use twitter configuration
  lazy val configuration = new RuleConfiguration

  // TODO Configuration
  lazy val daoFactory = new DaoFactoryImpl()

  lazy val miscFactory = new MiscFactoryImpl()
  // TODO Configuration
  lazy val twitterComponentFactory = new TwitterComponentFactoryImpl(
    configuration, miscFactory.jobScheduler, daoFactory.tweetStatusDao)

  lazy val filterManager = new FilterManagerImpl(configuration, miscFactory, twitterComponentFactory, daoFactory)

}
