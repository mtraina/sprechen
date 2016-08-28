package session

import java.util.Date
import javax.inject.Inject

import models.{Session, User}
import javax.inject.Singleton

import controllers.action.Secured
import play.api.{Configuration, Logger}

import scala.collection.concurrent.TrieMap

trait SessionRepository {
  def isLoggedIn(username: String): Boolean
  def login(user: User): String
  def logout(username: String): Unit
}

@Singleton
class SessionRepositoryImpl @Inject()(val configuration: Configuration) extends SessionRepository {
  private val sessions: TrieMap[String, Session] = TrieMap()
  private val ttl = configuration.getLong("user.inactivity.max").get

  override def isLoggedIn(token: String): Boolean = {
    val session = sessions.get(token)
    val res = session.exists(s => {
      val now = new Date().getTime
      now - s.lastSeen.getTime < ttl
    })
    if(res){
      sessions.put(token, session.get.copy(lastSeen = new Date()))
    }
    Logger.debug(s"logged in $res")
    res
  }

  override def login(user: User): String = {
    val now: Date = new Date()
    val token = Secured.createToken()
    sessions.put(token, Session(user, now, now))
    token
  }

  override def logout(username: String): Unit = sessions.remove(username)

}
