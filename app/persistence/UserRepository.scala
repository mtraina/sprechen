package persistence

trait UserRepository {
  def login(username: String, password: String): Boolean
  def logout(username: String): Unit
  def isLoggedIn(username: String): Boolean
}

class UserRepositoryImpl extends UserRepository {
  override def login(username: String, password: String): Boolean = ???

  override def isLoggedIn(username: String): Boolean = ???

  override def logout(username: String): Unit = ???
}
