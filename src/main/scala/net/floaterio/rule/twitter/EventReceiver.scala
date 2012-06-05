package net.floaterio.rule.twitter

import scala.actors.Actor
import collection.mutable.ListBuffer
import net.floaterio.rule.core.command.{Pause, Resume, OnSchedule}
import org.apache.commons.logging.LogFactory

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/17
 * Time: 3:51
 * To change this template use File | Settings | File Templates.
 */

class EventReceiver extends Actor {

  var status = true

  val log = LogFactory.getLog(getClass())

  this.start()

  def act = {
    loop {
      react {
        case Resume => status = true
        case Pause =>  status = false
        case a: Any => {
          if (status) {
            a match {
              // TODO Followとか
              case s: TimeLineStatus => onTimeLine(s)
              case s: MentionStatus => onMention(s)
              case s: Follow => onFollow(s)
              case s: Remove => onRemove(s)
              case c: OnSchedule => onSchedule(c)
              case _ => log.warn("this message is not supported")
            }
          }
        }
      }
    }
  }

  def resume = this ! Resume
  def pause = this ! Pause

  val onTimeLineListeners = ListBuffer[TimeLineStatus => Option[_]]()
  val onMentionListeners = ListBuffer[(MentionStatus) => Option[_]]()
  val onFollowListeners = ListBuffer[Follow => Unit]()
  val onRemoveListeners = ListBuffer[Remove => Unit]()
  var scheduleReceiverMap = Map[String, () => Option[_]]()

  def onTimeLine(s: TimeLineStatus) {
    onTimeLineListeners.find(_(s).isDefined)
  }

  def onMention(s: MentionStatus) {
    onMentionListeners.find(_(s).isDefined)
  }

  def onFollow(s : Follow) {
    onFollowListeners.foreach(_(s))
  }

  def onRemove(s : Remove) {
    onRemoveListeners.foreach(_(s))
  }

  def onSchedule(c: OnSchedule) {
    scheduleReceiverMap.get(c.name).foreach(_.apply())
  }

}

