package net.floaterio.rule.filter.impl

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/11
 * Time: 6:21
 * To change this template use File | Settings | File Templates.
 */

import org.apache.commons.logging.LogFactory
import net.floaterio.rule.util._
import net.floaterio.rule.filter.TimelineFilterBase

class GreetingFilter extends TimelineFilterBase {

  import ReplySupport._

//  val combiners = List(or("おはよう", r("(お|起)き(た|る)")).convert(
//    random(
//      formatByName("おはよう。%s"),
//      fixed("寝ぐせが可愛い。3ptアップ", 0.1) doBefore (c => {
//        println(c.userName)
//      })
//    )
//  ),
//    or("帰宅", "きたく", "ただいま").convert(
//      random(
//        formatByName("%s おかえりー"),
//        formatByName("おかえり。%s"),
//        time("おかえり。オーヘルして遊ぼ？", 1, 17, 20),
//        time("おかえり。遅くまでお疲れさま", 1, 22, 4)
//      )
//    )
//  )

  override def timeline = List(
    filter("aaaa") >> rand("aaaaaa", "bbbbbb"),
    filter(and(or("帰宅", "きたく", "ただいま"), not("行きたく"))) >>
      rand("おかえり。{user}", "おかえりなさいー") >> complement
  ).map {f =>
    permitted >>> f >> tweet
  }

}
