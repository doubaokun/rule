package net.floaterio.rule.core

import org.apache.commons.configuration.PropertiesConfiguration


/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/25
 * Time: 1:42
 * To change this template use File | Settings | File Templates.
 */

trait ConfigurationFactory {

  lazy val oAuthConf = new OAuthConf(new PropertiesConfiguration("oauth.properties"))

}
