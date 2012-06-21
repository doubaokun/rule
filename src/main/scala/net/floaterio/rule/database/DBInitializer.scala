package net.floaterio.rule.database

import model._
import org.squeryl.adapters.MySQLInnoDBAdapter
import org.squeryl.{SessionFactory, Session, Schema}
import org.squeryl.PrimitiveTypeMode._
import net.floaterio.rule.core.DependencyFactory

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

  val config = DependencyFactory.ruleConfig.vend

  def init = {
    Class.forName(config.dbDriver)
    logger.info("mysql driver loaded")
    // TODO Config
    SessionFactory.concreteFactory = Some(() => Session.create(
      java.sql.DriverManager.getConnection(config.dbUrl, config.dbUser, config.dbPass)
      , new MySQLInnoDBAdapter)
    )
    logger.info("session created")
  }

  // TODO TWitterのIDと同一にするためにはAuto Incrementを無効にしなければならない
  val userTable = table[TUser]("user")
  on(userTable)(u =>
    declare(u.id is (primaryKey)))

  val userStatusTable = table[UserStatus]("userStatus")
  on(userStatusTable)(u => {
    declare(u.id is (primaryKey))
  })

  val tweetStatusTable = table[TweetStatus]("tweetStatus")
  on(tweetStatusTable)(t => {
    // TODO Index by targetUser and created
    declare(t.id is (primaryKey))
  })

}
