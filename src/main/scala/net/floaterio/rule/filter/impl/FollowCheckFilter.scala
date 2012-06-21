package net.floaterio.rule.filter.impl

import net.floaterio.rule.database.model.TUser
import net.floaterio.rule.filter.{FilterDependencies, TimelineFilterBase}
import net.floaterio.rule.twitter.model.FollowContext

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/11
 * Time: 19:56
 * To change this template use File | Settings | File Templates.
 */

class FollowCheckFilter extends TimelineFilterBase {

  // TODO StatusContextとFollowContextを共通のTraitを継承するようにしてtweetとの関数合成をできるようにする

  override def followState = List(
    (c:FollowContext) => {
      if(c.isFollow) {
        val user = new TUser()
        user.id = c.user.getId
        user.screenName = c.user.getScreenName
        user.nickname = c.user.getName
        // TODO リプライ許可を引き継ぐ
        userDao.insertOrUpdate(user)

        // TODO Async
        safeWithTwitter {
          twitter.createFriendship(c.user.getId)
        }

      } else {
        userDao.findByPk(c.user.getId).foreach(user => {
          user.followed = false
          user.following = false
          user.allowReply = false
          userDao.update(user)

          // TODO Async
          safeWithTwitter {
            twitter.destroyFriendship(user.id)
          }

        })
      }
    }
  ).map(f => {
    pass[FollowContext] >> f
  })
}


