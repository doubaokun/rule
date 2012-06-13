package net.floaterio.rule.filter

import net.floaterio.rule.twitter.TweetResult
import net.floaterio.rule.twitter.model.{FollowContext, StatusContext}
import net.floaterio.rule.util.schedule.JobScheduler
import java.util.concurrent.TimeUnit

/**
 * Created with IntelliJ IDEA.
 * User: Izumi
 * Date: 12/06/01
 * Time: 2:40
 * To change this template use File | Settings | File Templates.
 */

trait TimelineFilter {

  def filterName() :String
  def mention: List[StatusContext => Option[_]]
  def timeline: List[StatusContext => Option[_]]
  def followState: List[FollowContext => Option[_]]

  def schedule: List[(JobSchedule, StatusContext => Option[_])]

}

sealed trait JobSchedule
case class IntervalJob(name: String, span: Int, timeUnit: TimeUnit) extends JobSchedule
case class CronJob(name: String, cron: String) extends JobSchedule



