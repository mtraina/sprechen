package session

import java.util.Date
import javax.inject.Inject

import models.{Session, User}
import javax.inject.Singleton

import play.api.Configuration

import scala.collection.concurrent.TrieMap

trait SessionRepository {
  def isLoggedIn(username: String): Boolean
  def login(user: User): Unit
  def logout(username: String): Unit
}

@Singleton
class SessionRepositoryImpl @Inject()(val configuration: Configuration) extends SessionRepository {
  private val sessions: TrieMap[String, Session] = TrieMap()
  private val ttl = configuration.getLong("user.inactivity.max").get

  override def isLoggedIn(username: String): Boolean = {
    val session = sessions.get(username)
    val res = session.exists(s => {
      val now = new Date().getTime
      now - s.lastSeen.getTime < ttl
    })
    if(res){
      sessions.put(username, session.get.copy(lastSeen = new Date()))
    }
    res
  }

  override def login(user: User): Unit = {
    val now: Date = new Date()
    sessions.put(user.username, Session(user, now, now))
  }

  override def logout(username: String): Unit = sessions.remove(username)

}
