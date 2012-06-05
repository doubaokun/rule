package net.floaterio.rule.filter.impl

import net.floaterio.rule.twitter.model.StatusContext
import net.floaterio.rule.filter.{FilterDependencies, FilterBase}


/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/11
 * Time: 17:58
 * To change this template use File | Settings | File Templates.
 */

class CommandFilter (dependencies: FilterDependencies) extends FilterBase(dependencies) {

  import dependencies._
  import net.floaterio.rule.util.ReplySupport._

  val a = owner >>> filter("%block") >> {
    s => {
      // TODO get UserName from body
      val user = s.body//.get("screenName")
      // TODO Queue
      twitter.createBlock(user)
    }
  }

}

