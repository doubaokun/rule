package net.floaterio.rule.util

import net.floaterio.rule.twitter.model.StatusContext
import java.util.Calendar
import util.Random
import net.floaterio.rule.database.model.TUser

/**
 * Created with IntelliJ IDEA.
 * User: Izumi
 * Date: 12/06/05
 * Time: 2:55
 * To change this template use File | Settings | File Templates.
 */

trait ReplyConverter2 {

}

trait UserSupplier {
  def getUser(userId: Long): Option[TUser]
}

//trait ReplyConverter {
//  def apply(c: StatusContext): StatusContext
//}
//
//case class SimpleReplyConverter(f: StatusContext => StatusContext) extends ReplyConverter {
//  def apply(c: StatusContext) = f(c)
//}
//
//case class StringConverter(string: String) extends ReplyConverter {
//  def apply(c: StatusContext): String = {
//    c.updateStatus = string
//    c
//  }
//}
//
//case class UserNameComplement(supplier: UserSupplier) extends ReplyConverter {
//  def apply(c: StatusContext): String = {
//    val sts = c.updateStatus
//    val userName = supplier.getUser(c.userId).map(_.nickname).getOrElse(c.userName)
//    // TODO Format error
//    c.updateStatus = sts.format(userName)
//    c
//  }
//}
//
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
//      w
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
