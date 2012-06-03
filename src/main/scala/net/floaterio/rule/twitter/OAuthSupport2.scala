package net.floaterio.rule.twitter

import twitter4j.auth.AccessToken
import net.floaterio.rule.core.OAuthConf
import twitter4j.{TwitterStreamFactory, TwitterFactory}
import twitter4j.conf.ConfigurationBuilder

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/25
 * Time: 1:34
 * To change this template use File | Settings | File Templates.
 */

class OAuthSupport2(conf: OAuthConf) {
  
  val token = new AccessToken(conf.oAuthAccessToken,conf.oAuthAccessSecret)

  lazy val twitter = {
    val twitter = new TwitterFactory().getInstance()
    twitter.setOAuthAccessToken(token)
    twitter
  }

  def createUserStream () {
    val stream = new TwitterStreamFactory(
      new ConfigurationBuilder()
        .setOAuthAccessToken(conf.oAuthAccessToken) //
        .setOAuthAccessTokenSecret(conf.oAuthAccessSecret)//
        .setOAuthConsumerKey(conf.consumerKey) //
        .setOAuthConsumerSecret(conf.consumerSecret) //
        .build())//
      .getInstance(token)
    stream
  }

}
