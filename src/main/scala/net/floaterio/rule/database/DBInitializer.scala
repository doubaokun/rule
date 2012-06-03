package net.floaterio.rule.database

import model._
import org.squeryl.adapters.MySQLInnoDBAdapter
import org.squeryl.{SessionFactory, Session, Schema}
import org.squeryl.PrimitiveTypeMode._

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/24
 * Time: 23:23
 * To change this template use File | Settings | File Templates.
 */

import org.slf4j.LoggerFactory

class DBInitializer extends Schema {

  val logger = LoggerFactory.getLogger(getClass)

  def init = {
    Class.forName("com.mysql.jdbc.Driver")
    logger.info("mysql driver loaded")
    // TODO Config
    SessionFactory.concreteFactory = Some(() => Session.create(
      java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/rule", "root", "pass01")
      , new MySQLInnoDBAdapter)
    )
    logger.info("session created")
  }

  val userTable = table[User]("tuser")
  on(userTable)(u =>
    declare(u.id is (primaryKey)))

  val userStatusTable = table[UserStatus]("userStatus")
  on(userStatusTable)(u => {
    declare(u.id is (primaryKey))
  })

}
