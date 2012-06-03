package net.floaterio.rule.twitter

import twitter4j.{User, Status}


/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/17
 * Time: 3:55
 * To change this template use File | Settings | File Templates.
 */

sealed trait StatusWrapper

case class TimeLineStatus(status: Status) extends StatusWrapper
case class MentionStatus(status: Status) extends StatusWrapper
case class RetweetStatus(status: Status) extends StatusWrapper
case class FavoriteStatus(status: Status) extends StatusWrapper

sealed trait FollowControl

case class Follow(user: User) extends FollowControl
case class Remove(user: User) extends FollowControl