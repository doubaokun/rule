package net.floaterio.rule.database.base

import org.squeryl.KeyedEntity

/**
 * Created with IntelliJ IDEA.
 * User: Izumi
 * Date: 12/06/15
 * Time: 11:46
 * To change this template use File | Settings | File Templates.
 */

trait BaseEntity extends KeyedEntity[Long]{

  var id: Long

}
