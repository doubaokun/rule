package net.floaterio.rule.database.base

import org.squeryl.PrimitiveTypeMode._
import net.floaterio.rule.database.model.TUser
import org.squeryl.{PrimitiveTypeMode, Table}


/**
 * Created with IntelliJ IDEA.
 * User: Izumi
 * Date: 12/06/15
 * Time: 11:35
 * To change this template use File | Settings | File Templates.
 */

trait DaoBase[T <: BaseEntity] {

  def table: Table[T]

  def findByPk(id: Long): Option[T] = {
    from(table)(t =>
      where(t.id === id).select(t)
    ).headOption
  }

  def findAll(): List[T] = {
    from(table)(t => PrimitiveTypeMode.select(t)).toList
  }

  def insert(value: T):T = {
    transaction {
      table.insert(value)
    }
  }

  def update(value: T):Unit = {
    transaction {
      table.update(value)
    }
  }

  def delete(value: T):Int = {
    transaction {
      table.deleteWhere(t => t.id === value.id)
    }
  }

  def insertOrUpdate(value: T):T = {
    transaction {
      table.insertOrUpdate(value)
    }
  }

}
