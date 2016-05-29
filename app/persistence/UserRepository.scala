package persistence

import javax.inject.Singleton

trait UserRepository {
  def login(username: String, password: String): Boolean
  def logout(username: String): Unit
  def isLoggedIn(username: String): Boolean
}

@Singleton
class UserRepositoryImpl extends UserRepository {
  // TODO: change impl. after testing
  override def login(username: String, password: String): Boolean = true

  override def isLoggedIn(username: String): Boolean = ???

  override def logout(username: String): Unit = ???
}

object UserRepository {
  lazy val instance: UserRepository = new UserRepositoryImpl
}