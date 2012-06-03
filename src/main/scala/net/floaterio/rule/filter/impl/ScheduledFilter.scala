package net.floaterio.rule.filter.impl

import java.util.concurrent.TimeUnit
import net.floaterio.rule.util.ReplySupport
import net.floaterio.rule.twitter.model.StatusContext
import net.floaterio.rule.filter.{FilterDependencies, FilterBase}

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/11
 * Time: 15:15
 * To change this template use File | Settings | File Templates.
 */

class ScheduledFilter (dependencies: FilterDependencies) extends FilterBase(dependencies) {

  import ReplySupport._
  import dependencies._

  val converter = random(
    "だめ、またない",
    "必要なのは、覚悟と、度胸なの。",
    "……読み負けたの ",
    "そう！部活合宿なの！",
    "みんなで、タマキの田舎に遊びに行くの！",
    "晶、可哀想。一緒に来れたら良かったのに",
    "タマキもゲームしようよー",
    "具合、わるいの？",
    "熱は無いみたいだけど…… ",
    "あれ？やっぱり熱あるかな？顔が赤くなってる",
    "熱を測っただけなのに",
    "健康管理不足。３ptダウン",
    "でも女の子に触られて赤くなるのは可愛いから４ptアップ",
    "やるー！",
    "はう…… ",
    "タマキ……？",
    "……ううん。なんでもないの",
    "あの人、綺麗",
    "あわわ",
    "ずるい！わたしもするー！",
    time("そろそろ寝ようかな", 1.0, 2, 3)
  )

  atJustTime("sanjihan", "0 30 3,15 * * ?") {
    tweet("ｻﾝｼﾞﾊﾝ!!")
  }

  atJustTime("yoruho", "0 0 0 * * ?") {
    tweet("よるほー")
  }

  interval("schdule1", 30, TimeUnit.MINUTES) {
    tweet(converter.apply(new StatusContext(null, Nil, null)))
  }

}

