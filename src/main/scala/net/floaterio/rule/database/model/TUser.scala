package net.floaterio.rule.database.model

import java.util.Date
import net.floaterio.rule.database.base.{DaoBase, BaseEntity}
import org.squeryl.{Table, KeyedEntity}

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/24
 * Time: 23:29
 * To change this template use File | Settings | File Templates.
 */

class TUser(var id: Long,
            var screenName: String,
            var nickname: String,
            var following: Boolean,
            var followed: Boolean,
            var allowReply: Boolean,
            var created: Date) extends BaseEntity {

  def this() = this(0L,"","",true,false,true,new Date)

}

trait UserDao extends DaoBase[TUser] {
}

class UserDaoImpl(val table: Table[TUser]) extends UserDao {
}
