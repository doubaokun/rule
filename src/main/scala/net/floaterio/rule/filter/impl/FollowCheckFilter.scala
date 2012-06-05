package net.floaterio.rule.filter.impl

import net.floaterio.rule.database.model.User
import net.floaterio.rule.filter.{FilterDependencies, FilterBase}

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/11
 * Time: 19:56
 * To change this template use File | Settings | File Templates.
 */

class FollowCheckFilter (dependencies: FilterDependencies) extends FilterBase(dependencies) {

  import dependencies._

  onFollow(context => {
    val user = new User()
    user.id = context.user.getId
    user.screenName = context.user.getScreenName
    user.nickname = context.user.getName
    // TODO リプライ許可を引き継ぐ
    userDao.createOrUpdate(user)

    // TODO Async
    safeWithTwitter {
      updateTwitter.createFriendship(context.user.getId)
    }
  })

  onRemove(context => {

    userDao.findByPk(context.user.getId).foreach(user => {
      user.followed = false
      user.following = false
      user.allowReply = false
      userDao.update(user)

      // TODO Async
      safeWithTwitter {
        updateTwitter.destroyFriendship(user.id)
      }

    })
  })
}


