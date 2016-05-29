package session

import models.User

trait SessionRepository {
  def isLoggedIn(name: String): Boolean
  def login(user: User): Unit
  def logout(name: String): Unit
}

class SessionRepositoryImpl extends SessionRepository {
  override def isLoggedIn(name: String): Boolean = ???

  override def logout(name: String): Unit = ???

  override def login(user: User): Unit = ???
}
