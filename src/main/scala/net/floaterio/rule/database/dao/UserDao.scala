package net.floaterio.rule.database.dao

import org.squeryl.Table
import net.floaterio.rule.database.model.User
import org.squeryl.PrimitiveTypeMode._

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/24
 * Time: 23:57
 * To change this template use File | Settings | File Templates.
 */


trait UserDao {

  def findByPk(userId: Long) : Option[User]

  def create(user: User): User

  def update(user: User): Unit

  def createOrUpdate(user: User): Unit
}

class UserDaoImpl(userTable: Table[User]) extends UserDao {

  def findByPk(userId: Long): Option[User] = {
    from(userTable)(u =>
      where(u.id === userId).select(u)
    ).headOption
  }

  def create(user: User) = {
    transaction {
      userTable.insert(user)
    }
  }

  def update(user: User) = {
    transaction {
      userTable.update(user)
    }
  }

  def createOrUpdate(user: User) = {
    transaction {
      findByPk(user.id).map(t => {
        userTable.update(user)
      }).getOrElse(
        userTable.insert(user)
      )
    }
  }

}

