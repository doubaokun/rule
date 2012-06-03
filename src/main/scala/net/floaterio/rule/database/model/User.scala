package net.floaterio.rule.database.model

import org.squeryl.KeyedEntity
import java.util.Date

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/24
 * Time: 23:29
 * To change this template use File | Settings | File Templates.
 */

class User(var id: Long,
            var screenName: String,
            var nickname: String,
            var following: Boolean,
            var followed: Boolean,
            var allowReply: Boolean,
            var created: Date) extends KeyedEntity[Long]{

  def this() = this(0L,"","",true,false,true,new Date)

}
