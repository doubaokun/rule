package net.floaterio.rule.twitter

import javasource.MockTwitter
import twitter4j.{TwitterStreamFactory, TwitterFactory, TwitterStream, Twitter}
import net.floaterio.rule.core.RuleConfiguration
import net.floaterio.rule.util.schedule.JobScheduler


/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/20
 * Time: 22:57
 * To change this template use File | Settings | File Templates.
 */

trait TwitterComponentFactory {

  def twitter : Twitter
  def updateTwitter : Twitter
  def twitterStream : TwitterStream
  def tweetCache : TweetCache
  def tweetCollector : TweetCollector

}

class TwitterComponentFactoryImpl(config: RuleConfiguration, scheduler: JobScheduler)
  extends TwitterComponentFactory {

  lazy val twitter = TwitterFactory.getSingleton
  // TODO Mock
  lazy val updateTwitter = new MockTwitter
  lazy val twitterStream = TwitterStreamFactory.getSingleton
  lazy val tweetCache = new TweetCache(twitter)
  lazy val tweetCollector = new TweetCollectorImpl(
    twitter, twitterStream, tweetCache, config, scheduler
  )

}

