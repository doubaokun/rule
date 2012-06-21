package net.floaterio.rule.core

import java.util.Date

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/25
 * Time: 0:13
 * To change this template use File | Settings | File Templates.
 */

// TODO file
class RuleConfiguration {

  def botScreenName = "M_Rule_bot"

  def ownerScreenName = "itm_"

  // TODO
  def isGenerateSchema = true

  val startUpTime = new Date

  val dbDriver = "com.mysql.jdbc.Driver"

  val dbUrl = "jdbc:mysql://localhost:3306/rule"

  val dbUser = "root"

  val dbPass = "pass01"

}
