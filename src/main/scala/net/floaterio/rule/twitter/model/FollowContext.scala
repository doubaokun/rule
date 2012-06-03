package net.floaterio.rule.twitter.model

import twitter4j.User

/**
 * Created with IntelliJ IDEA.
 * User: Izumi
 * Date: 12/06/01
 * Time: 2:37
 * To change this template use File | Settings | File Templates.
 */

case class FollowContext(val user: User, val isFollow: Boolean) {

}
