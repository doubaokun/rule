package net.floaterio.rule.database

import dao.{UserStatusDao, UserDao, UserStatusDaoImpl, UserDaoImpl}

/**
 * Created with IntelliJ IDEA.
 * User: Izumi
 * Date: 12/05/30
 * Time: 16:03
 * To change this template use File | Settings | File Templates.
 */

trait DaoFactory {

  def userDao : UserDao
  def userStatusDao : UserStatusDao

}

class DaoFactoryImpl extends DaoFactory {

  lazy val dBInitializer = new DBInitializer

  lazy val userDao = new UserDaoImpl(dBInitializer.userTable)
  lazy val userStatusDao = new UserStatusDaoImpl(dBInitializer.userStatusTable)

}
