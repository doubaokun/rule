package net.floaterio.rule.twitter

import twitter4j._
import java.util.concurrent.ConcurrentHashMap
import TwitterUtil._

/**
 * Created by IntelliJ IDEA.
 * User: Izumi
 * Date: 12/03/17
 * Time: 11:38
 * To change this template use File | Settings | File Templates.
 */

class TweetCache(twitter: Twitter) {

  // tweet
  // TODO 共通化
  val cache = new HeapCache[Long, Status]
  val userCache = new HeapCache[Long, User]

  def addStatus(status: Status) {
    cache.put(status.getId, status)
    userCache.put(status.getUser.getId, status.getUser)
  }

  def containsKey(statusId: Long) = {
    cache.containsKey(statusId)
  }

  def getConversationList(parent: Status) = {
    def _getStatus(list: List[Status]): List[Status] = {
      val id = list.head.getInReplyToStatusId
      if (id > 0) {
        val prevStatus = cache.getOrSet(id, {
          tryO[Status] {
            twitter.showStatus(id)
          }.orNull
        })
        Option(prevStatus).map(ps => {
          userCache.put(ps.getUser.getId, ps.getUser)
          _getStatus(ps :: list)
        }).getOrElse(list)
      } else {
        list
      }
    }
    userCache.put(parent.getUser.getId, parent.getUser)
    _getStatus(List(parent)).reverse
  }

}

class HeapCache[K, V] {

  val map = new ConcurrentHashMap[K, V]()

  def getOrSet[T](id: K, default: V) = {
    Option(map.get(id)).getOrElse({
      // nullは追加しないこととする
      Option(default).foreach(d => {
        map.put(id, d)
      })
      default
    })
  }

  def put(key: K, value: V) = {
    map.put(key, value)
  }

  def containsKey(key: K) = map.containsKey(key)

}
