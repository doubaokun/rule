package net.floaterio.rule.filter.impl

import org.apache.commons.logging.LogFactory
import net.floaterio.rule.util._
import net.floaterio.rule.twitter.model.StatusContext
import net.floaterio.rule.filter.{FilterDependencies, FilterBase}
import util.Random
import java.util.Date

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

//  val converters = List(
//    or("なでなで").convert(random(
//      fixed("はうう", 0.4),
//      fixed("下心がみえる。1ptダウン", 0.1).doBefore(increment(-1)),
//      fixed("寂しい時に優しいのはパートナーとして良い心がけ。3ptアップ", 0.1).doBefore(increment(3))
//    )
//    ),
//    or("ちゅっちゅ").convert(random(
//      fixed("ん……", 0.1),
//      fixed("大胆な行動。1ptアップ", 0.1).doBefore(increment(1)),
//      fixed("いきなりはしたない。3ptダウン", 0.1).doBefore(increment(-3))
//    ))
//  )

//  mention += filter("message") >>> withFrequency >>
//    rand(
//      fix("Reply A", 3) withAction increment(3),
//      fix("Reply C", 4) withAction increment(4)
//    ) >> reply
//
//  mention += filter("message2") >>> withFrequency >>
//    rand("aaaa", "BBBBB") >> reply
//
//  mention += filter("message3") >>> withFrequency >> {
//    s => {
//      val like = likability(s)
//      s.updateStatus = like match {
//        case i if i > 0 => "Good message"
//        case _ => "Bad message"
//      }
//    }
//  } >> reply

}

