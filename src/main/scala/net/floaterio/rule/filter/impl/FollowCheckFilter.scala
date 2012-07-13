package net.floaterio.rule.filter.impl

import net.floaterio.rule.database.model.TUser
import net.floaterio.rule.filter.TimelineFilterBase
import net.floaterio.rule.twitter.model.FollowContext

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/11
 * Time: 19:56
 * To change this template use File | Settings | File Templates.
 */

class FollowCheckFilter extends TimelineFilterBase {

  override def followState = List(
    filter((c:FollowContext) => c.isFollow) >> (c => {
      val user = new TUser()
      user.id = c.user.getId
      user.screenName = c.user.getScreenName
      user.nickname = c.user.getName
      user.followed = true
      // TODO リプライ許可を引き継ぐ
      // Followingフラグを切り替えるのは別のスレッドで行う// Followのアクションが成立したあと
      userDao.insertOrUpdate(user)
      // TweetQueue
      safeWithTwitter {
        twitter.createFriendship(c.user.getId)
      }
      // TODO TweetText
      c
    }) >> tweet,
    filter((c:FollowContext) => !c.isFollow) >> (c => {
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
    })
  )
}


