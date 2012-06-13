package net.floaterio.rule.twitter

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/20
 * Time: 23:59
 * To change this template use File | Settings | File Templates.
 */

import scala.actors.Actor
import scala.actors.Actor._
import scala.collection.JavaConversions._
import java.util.concurrent.TimeUnit
import org.apache.commons.logging.LogFactory
import twitter4j._
import net.floaterio.rule.core.command._
import net.floaterio.rule.util.schedule.JobScheduler
import net.floaterio.rule.core.{DependencyFactory, RuleConfiguration}


trait TweetCollector {

  def += (a: Actor)

  def start()
  def resume()
  def pause()
  def stop()

}

class TweetCollectorImpl extends TweetCollector {

  val twitter = DependencyFactory.twitter.vend
  val twitterStream = DependencyFactory.twitterStream.vend
  val tweetCache = DependencyFactory.tweetCache.vend
  val config = DependencyFactory.ruleConfig.vend
  val jobScheduler = DependencyFactory.jobScheduler.vend

  val timelinePaging = new Paging(1, 1)
  val timelineCount = 100
  val mentionPaging = new Paging(1, 1)
  val mentionCount = 50
  val getTweetInterval = 60

  val jobName = "timeline"

  val jobGroup = getClass.getName

  val log = LogFactory.getLog(getClass)

  var listeners = List[Actor]()

  case class AddListener(actor: Actor)
  case class OnStatus(list: List[Status])

  def += (actor: Actor) = {
    commandActor ! AddListener(actor)
  }

  val commandActor: Actor = actor {
    loop {
      react {
        case Start => {
          twitterStream.user()
          jobScheduler.addIntervalJob(jobName, jobGroup, commandActor, getTweetInterval, TimeUnit.SECONDS)
        }
        case Pause => {
          twitterStream.addConnectionLifeCycleListener(new ConnectionLifeCycleListener {
            def onConnect() {}

            def onCleanUp() {}

            def onDisconnect() {}
          })

          twitterStream.shutdown()
          jobScheduler.pause(jobName, jobGroup)
        }
        case Resume => {
          twitterStream.user()
          jobScheduler.resume(jobName, jobGroup)
        }
        case Stop => {
          twitterStream.shutdown()
          jobScheduler.remove(jobName, jobGroup)
        }
        case s: OnStatus => s.list.foreach(sta => onStatusFunc(sta))
        case f: Follow => notifyToListener(f)
        case c: OnSchedule => {
          polling
        }
        case a: AddListener => {
          listeners = a.actor :: listeners
        }
      }
    }
  }

  // init

  twitterStream.addListener(new UserStreamAdapter() {
    override def onStatus(s: Status) {
        commandActor ! OnStatus(List(s))
    }

    override def onFollow(source: User, followedUser: User) {
      log.info("follow!:from=%s, to=%s".format(source.getScreenName, followedUser.getScreenName))
      commandActor ! Follow(source)
    }
  })

  def notifyToListener(obj : Any) = {
    listeners.foreach(_ ! obj)
  }

  def isMention(status: Status) = {
    // 自分自身のTweetは除く
    status.getUser.getScreenName != config.botScreenName &&
    status.getText.contains(config.botScreenName)
  }

  def polling = {

    def get(f: () => List[Status], paging: Paging, count: Int) {
      log.info("attempt to get timeLine")
      val list = f()
      log.info("get timeLine size=" + list.size)

      list.foreach(s => {
        onStatusFunc(s)
      })

      if(!list.isEmpty){
        val max = list.map(_.getId).max
        paging.setSinceId(max)
        paging.setCount(count)
      }
    }



    get(() => {
      twitter.getHomeTimeline(timelinePaging).toList
    }, timelinePaging, timelineCount)

    get(() => {
      twitter.getMentions(mentionPaging).filter { s =>
        s.getCreatedAt.compareTo(config.startUpTime) > 0
      }.toList
    }, mentionPaging, mentionCount)
  }

  def start = commandActor ! Start
  def resume = commandActor ! Resume
  def pause = commandActor ! Pause
  def stop = commandActor ! Stop

  private def onStatusFunc(status: Status) {

    val accept = !status.isRetweet && !status.isFavorited && !status.isRetweetedByMe && !status.isTruncated

    if(!tweetCache.containsKey(status.getId) && accept) {
      tweetCache.addStatus(status)
      if (isMention(status)) {
        notifyToListener(MentionStatus(status))
      } else {
        // ここに来るときはリプライは一切含まない（他の人へのリプライを除外する）
        if(!status.getText.contains("@")){
          notifyToListener(TimeLineStatus(status))
        }
      }
    }
  }
}


