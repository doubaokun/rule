package net.floaterio.rule.database.model

import java.util.Date
import org.squeryl.{Table, KeyedEntity}
import org.squeryl.PrimitiveTypeMode._

/**
 * Created with IntelliJ IDEA.
 * User: Izumi
 * Date: 12/06/15
 * Time: 11:27
 * To change this template use File | Settings | File Templates.
 */

case class FilterStatus(id: Long,
                        name: String,
                        enable: Boolean,
                        lastUpdated: Date) extends KeyedEntity[Long]{

  def this() = this(0, "", true, new Date())

}

trait FilterStatusDao {

}

class FilterStatusDaoImpl(table: Table[FilterStatus]) extends FilterStatusDao {

  def findByPk(id: Long): Option[FilterStatus] = {
    from(table)(t =>
      where(t.id === id).select(t)
    ).headOption
  }

}
