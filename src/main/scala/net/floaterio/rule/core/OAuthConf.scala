package net.floaterio.rule.core

import org.apache.commons.configuration.Configuration

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/25
 * Time: 1:33
 * To change this template use File | Settings | File Templates.
 */

class OAuthConf(conf: Configuration) {

  def consumerKey: String = conf.getString("oAuthConsumerKey")
  def consumerSecret: String = conf.getString("oAuthConsumerSecret")
  def oAuthAccessToken: String = conf.getString("oAuthAccessToken")
  def oAuthAccessSecret: String = conf.getString("oAuthAccessSecret")

}
