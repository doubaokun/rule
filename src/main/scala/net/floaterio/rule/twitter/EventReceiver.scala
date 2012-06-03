package net.floaterio.rule.twitter

import scala.actors.Actor
import collection.mutable.ListBuffer
import net.floaterio.rule.core.command.{Pause, Resume, OnSchedule}

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/17
 * Time: 3:51
 * To change this template use File | Settings | File Templates.
 */

class EventReceiver extends Actor {

  var status = true

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
              case c: OnSchedule => {
                scheduleReceiverMap.get(c.name).foreach(_.apply())
              }
              case _ =>
            }
          }
        }
      }
    }
  }

  def resume = this ! Resume
  def pause = this ! Pause

  var onTimeLineListeners = new ListBuffer[(TimeLineStatus) => Unit]
  var onMentionListeners = new ListBuffer[(MentionStatus) => Unit]
  var onFollowListeners = new ListBuffer[(Follow) => Unit]
  var onRemoveListeners = new ListBuffer[(Remove) => Unit]
  var scheduleReceiverMap = Map[String, () => Unit]()

  def onTimeLine(s: TimeLineStatus) {
    onTimeLineListeners.foreach(_(s))
  }

  def onMention(s: MentionStatus) {
    onMentionListeners.foreach(_(s))
  }

  def onFollow(s : Follow) {
    onFollowListeners.foreach(_(s))
  }

  def onRemove(s : Remove) {
    onRemoveListeners.foreach(_(s))
  }

}

