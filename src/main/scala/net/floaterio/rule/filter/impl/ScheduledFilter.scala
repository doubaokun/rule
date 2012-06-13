package net.floaterio.rule.filter.impl

import java.util.concurrent.TimeUnit
import net.floaterio.rule.util.ReplySupport
import net.floaterio.rule.twitter.model.StatusContext
import net.floaterio.rule.filter._

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/11
 * Time: 15:15
 * To change this template use File | Settings | File Templates.
 */

class ScheduledFilter  extends TimelineFilterBase {

  import ReplySupport._

//  val converter = random(
//    "だめ、またない",
//    "必要なのは、覚悟と、度胸なの。",
//    "……読み負けたの ",
//    "そう！部活合宿なの！",
//    "みんなで、タマキの田舎に遊びに行くの！",
//    "晶、可哀想。一緒に来れたら良かったのに",
//    "タマキもゲームしようよー",
//    "具合、わるいの？",
//    "熱は無いみたいだけど…… ",
//    "あれ？やっぱり熱あるかな？顔が赤くなってる",
//    "熱を測っただけなのに",
//    "健康管理不足。３ptダウン",
//    "でも女の子に触られて赤くなるのは可愛いから４ptアップ",
//    "やるー！",
//    "はう…… ",
//    "タマキ……？",
//    "……ううん。なんでもないの",
//    "あの人、綺麗",
//    "あわわ",
//    "ずるい！わたしもするー！",
//    time("そろそろ寝ようかな", 1.0, 2, 3)
//  )

  override def schedule = List(
    (CronJob("sanjihan", "0 30 3,15 * * ?"), simpleStatus("ｻﾝｼﾞﾊﾝ!!")),
    (CronJob("yoruho", "0 0 0 * * ?"), simpleStatus("よるほー")),
    (IntervalJob("schdule1", 30, TimeUnit.MINUTES),
      rand("ずるい！わたしもするー！",
           "でも女の子に触られて赤くなるのは可愛いから４ptアップ"))
  ).map(sc => {
    (sc._1, pass >> sc._2 >> tweet)
  })

}

