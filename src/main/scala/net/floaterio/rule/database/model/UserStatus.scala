package net.floaterio.rule.database.model

import java.util.Date
import org.squeryl.KeyedEntity

/**
 * Created with IntelliJ IDEA.
 * User: Izumi
 * Date: 12/04/07
 * Time: 23:27
 * To change this template use File | Settings | File Templates.
 */

case class UserStatus(var id: Long,
                 var userId: Long,
                 var likability : Int,
                 var updated : Date
                  ) extends KeyedEntity[Long] {
  def this() = this(0L,0L,0,new Date())
}

//class User(var id: Long,
//            var screenName: String,
//            var nickname: String,
//            var following: Boolean,
//            var followed: Boolean,
//            var allowReply: Boolean,
//            var created: Date) extends KeyedEntity[Long]{
//
//  def this() = this(0L,"","",true,false,true,new Date)
//
//}
