package net.floaterio.rule.database.dao

import net.floaterio.rule.database.model.UserStatus
import org.squeryl.Table
import org.squeryl.PrimitiveTypeMode._
import java.util.Date

/**
 * Created with IntelliJ IDEA.
 * User: Izumi
 * Date: 12/04/08
 * Time: 19:56
 * To change this template use File | Settings | File Templates.
 */

trait UserStatusDao {
  def findByPk(id: Long) : Option[UserStatus]
  def findByUserId(userId: Long) : Option[UserStatus]
  def create(userStatus: UserStatus) : UserStatus
  def update(userStatus: UserStatus) : Unit
  def incrementLikability(userId: Long, point: Int)
}

class UserStatusDaoImpl(table: Table[UserStatus]) extends UserStatusDao {

  def findByPk(id: Long) = {
    from(table)(s =>
      where(s.id === id).select(s)
    ).headOption
  }

  def findByUserId(userId: Long) = {
    from(table)(s =>
      where(s.userId === userId).select(s)
    ).headOption
  }

  def create(userStatus: UserStatus) = {
    transaction{
      table.insert(userStatus)
    }
  }

  def update(userStatus: UserStatus) = {
    transaction{
      table.update(userStatus)
    }
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
