package net.floaterio.rule.filter.impl

import org.apache.commons.logging.LogFactory
import net.floaterio.rule.util._
import net.floaterio.rule.twitter.model.StatusContext
import net.floaterio.rule.filter.{FilterDependencies, FilterBase}

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/11
 * Time: 15:37
 * To change this template use File | Settings | File Templates.
 */


class ConversationFilter (dependencies: FilterDependencies) extends FilterBase(dependencies) {

  val log = LogFactory.getLog(getClass)

  import ReplySupport._
  import dependencies._

  val converters = List(
    or("なでなで").convert(random(
      fixed("はうう", 0.4),
      fixed("下心がみえる。1ptダウン", 0.1).doBefore(increment(-1)),
      fixed("寂しい時に優しいのはパートナーとして良い心がけ。3ptアップ", 0.1).doBefore(increment(3))
    )
    ),
    or("ちゅっちゅ").convert(random(
      fixed("ん……", 0.1),
      fixed("大胆な行動。1ptアップ", 0.1).doBefore(increment(1)),
      fixed("いきなりはしたない。3ptダウン", 0.1).doBefore(increment(-3))
    ))
  )

  def increment(point: Int)(c: StatusContext) = {
    userStatusDao.incrementLikability(c.userId, point)
  }

  mention /*contains("なでなで")*/ {context =>
    tweet(random("挨拶A", "挨拶B", "挨拶C").apply(context))
  }

  mention(context => {
    log.info("mention size=%s, text=%s".format(
      context.conversationList.size, context.status.getText))
  })
}

