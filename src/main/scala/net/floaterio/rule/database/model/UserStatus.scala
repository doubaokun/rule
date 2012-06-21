package net.floaterio.rule.database.model

import java.util.Date
import net.floaterio.rule.database.base.{DaoBase, BaseEntity}
import org.squeryl.{Table, KeyedEntity}
import org.squeryl.PrimitiveTypeMode._

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
                  ) extends BaseEntity {
  def this() = this(0L,0L,0,new Date())
}

trait UserStatusDao extends DaoBase[UserStatus] {
  def findByUserId(userId: Long) : Option[UserStatus]
  def incrementLikability(userId: Long, point: Int)
}

class UserStatusDaoImpl(val table: Table[UserStatus]) extends UserStatusDao {

  def findByUserId(userId: Long) = {
    from(table)(s =>
      where(s.userId === userId).select(s)
    ).headOption
  }

  def incrementLikability(userId: Long, point: Int) = {
    transaction {
      findByUserId(userId).map(status => {
        table.update(s => {
          where(s.userId === userId)
            .set(s.likability := status.likability + point)
        })
      }).getOrElse({
        val newStatus = UserStatus(0, userId, point, new Date())
        table.insert(newStatus)
      })

    }
  }
}

