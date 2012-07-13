package net.floaterio.rule.filter.impl

import net.floaterio.rule.filter.TimelineFilterBase
import net.floaterio.rule.twitter.TweetResult


/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/11
 * Time: 17:58
 * To change this template use File | Settings | File Templates.
 */

class CommandFilter extends TimelineFilterBase {

  import net.floaterio.rule.util.ReplySupport._

  override def timeline = List(
    filter("%block") >> {
      s => {
        // TODO get UserName from body
        val user = s.body //.get("screenName")
        // TODO Queue
        twitter.createBlock(user)
        TweetResult(true)
      }
    },
    filter("%follow") >> {
      c => {
        val user = c.body
        twitter.createFriendship(user)
        TweetResult(true)
      }
    }
  ).map(filter => owner >>> filter)

}

