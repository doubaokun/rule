package net.floaterio.rule.filter

import impl._
import net.floaterio.rule.twitter.model.StatusContext

/**
 * Created with IntelliJ IDEA.
 * User: Izumi
 * Date: 12/05/30
 * Time: 16:15
 * To change this template use File | Settings | File Templates.
 */

trait FilterManager {

  def filterMap: Map[String, TimelineFilter]

  def getInfo(name: String): Option[FilterStatus]

}

class FilterManagerImpl {

  // TODO start
  lazy val filterMap = {
    List (
      new ConversationFilter(),
      new ScheduledFilter(),
      new FollowCheckFilter(),
      new GreetingFilter(),
      new CommandFilter()
    ).map { f =>
      f.filterName -> f
    }.toMap
  }

  def getInfo(name: String) = {
//    filterMap.get(name).map { f =>
//      FilterStatus(f.filterName, f.isResume)
//    }
    // TODO
    Nil
  }

  def onTimeline(statusContext: StatusContext) = {
    filterMap.values.map(f => {
      f.timeline.find(func => {
        func.apply(statusContext).isDefined
      })
    })
  }


}

case class FilterStatus(name: String, status:Boolean)
case class FilterStatistics()
