package net.floaterio.rule.util

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/31
 * Time: 0:03
 * To change this template use File | Settings | File Templates.
 */

import util.Random
import java.util.Calendar
import net.floaterio.rule.twitter.model.StatusContext

trait ReplyCondition {

  def apply(c: StatusContext): Boolean

}

case class And(conditions: ReplyCondition*) extends ReplyCondition {
  def apply(c: StatusContext): Boolean = {
    conditions.forall(_.apply(c))
  }
}

case class Or(conditions: ReplyCondition*) extends ReplyCondition {
  def apply(c: StatusContext): Boolean = {
    conditions.find(_.apply(c)).isDefined
  }
}

case class Not(condition: ReplyCondition) extends ReplyCondition {
  def apply(c: StatusContext): Boolean = !condition(c)
}

case class Contains(string: String) extends ReplyCondition {
  def apply(c: StatusContext): Boolean = {
    c.body.contains(string)
  }
}

case class CRegex(pattern: String) extends ReplyCondition {
  val r = pattern.r

  def apply(c: StatusContext): Boolean = {
    r.findFirstIn(c.body).isDefined
  }
}

//trait ReplyConverter {
//  def apply(c: StatusContext): String
//
//  // TODO Afterにできるか？
//  def doBefore(f: StatusContext => Unit): ReplyConverter = {
//    SimpleReplyConverter(c => {
//      val result = doApply(c)
//      f.apply(c)
//      result
//    })
//  }
//
//  private def doApply(c: StatusContext) = {
//    apply(c)
//  }
//}
//
//case class SimpleReplyConverter(f: StatusContext => String) extends ReplyConverter {
//  def apply(c: StatusContext) = f(c)
//}
//
//case class StringConverter(string: String) extends ReplyConverter {
//  def apply(c: StatusContext): String = string
//}
//
//case class ComplementConverter(parent: ReplyConverter, f: (StatusContext, String) => String)
//  extends ReplyConverter {
//  def apply(c: StatusContext): String = f(c, parent(c))
//}
//
//case class UserNameComplementConverter(override val parent: ReplyConverter)
//  extends ComplementConverter(parent,
//    // TODO Nickname
//    (context, str) => str.format(context.status.getUser.getName)
//  ) {
//}
//
//trait WeightConverter extends ReplyConverter {
//  def weight(c: StatusContext): Double
//}
//
//case class GenericWeightConverter(parent: ReplyConverter, f: StatusContext => Double) extends WeightConverter {
//  def apply(c: StatusContext) = parent(c)
//
//  def weight(c: StatusContext) = f(c)
//}
//
///**
// * 時間によって重み付けを変える
// * toを小さく指定すると翌日の時間まで考慮して判定する
// * (from = 18, to = 6とすることで18時から翌日6時まで指定した重み付けを返す)
// *
// * @param parent
// * @param w
// * @param from
// * @param to
// */
//case class TimeWeightConverter(override val parent: ReplyConverter, w: Double, from: Int, to: Int)
//  extends GenericWeightConverter(parent, c => {
//    val now = Calendar.getInstance()
//    val h = now.get(Calendar.HOUR)
//    // from = 6, to = 10
//    if((from <= h && h < to) ||
//      // from = 18, to = 6 h = 20
//      (from > to && from <= h && to < h)
//    ){
//       w
//    } else {
//      -1
//    }
//  }) {
//}
//
//case class RandomConverter(converters: ReplyConverter*) extends ReplyConverter {
//  def apply(c: StatusContext): String = {
//    converters.maxBy(converter => {
//      val w = converter match {
//        case wc: WeightConverter => wc.weight(c)
//        case _ => 0.5
//      }
//      w * Random.nextDouble()
//    }).apply(c)
//  }
//}

//class ConditionHolder(condition: ReplyCondition) {
//  def convert(converter: ReplyConverter) = new ConditionConverterCombiner(condition, converter)
//}

//class ConverterHolder(converter: ReplyConverter) {
//  def formatByName = UserNameComplementConverter(converter)
//}
//
//class ConditionConverterCombiner(val condition: ReplyCondition, val converter: ReplyConverter) {
//
//}

object ReplySupport {
  // Condition
  def and(conditions: ReplyCondition*): And = And(conditions: _*)

  def or(conditions: ReplyCondition*): Or = Or(conditions: _*)

  def not(condition: ReplyCondition): Not = Not(condition)

  implicit def contains(string: String): Contains = Contains(string)

  def r(pattern: String) = CRegex(pattern)

//  // Converter
//  implicit def text(string: String): StringConverter = StringConverter(string)
//
//  def random(converters: ReplyConverter*): RandomConverter = RandomConverter(converters: _*)
//
//  def weight(converter: ReplyConverter, f: StatusContext => Double) = GenericWeightConverter(converter, f)
//
//  implicit def normalWeight(converter: ReplyConverter): WeightConverter = {
//    GenericWeightConverter(converter, c => 0.5)
//  }
//
//  def fixed(converter: ReplyConverter, w: Double): WeightConverter =
//    GenericWeightConverter(converter, c => w)
//
//  def time(converter: ReplyConverter, w: Double, from: Int, to: Int): WeightConverter =
//    TimeWeightConverter(converter, w, from, to)
//
//  // Holder
//
//  //  implicit def conditionToHolder(condition: ReplyCondition) = new ConditionHolder(condition)
//
//  def formatName(converter: ReplyConverter): StatusContext => StatusContext = UserNameComplement(converter)

}
