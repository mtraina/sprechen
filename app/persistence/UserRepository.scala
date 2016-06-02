package persistence

import javax.inject.{Inject, Singleton}

import models.User
import org.mindrot.jbcrypt.BCrypt
import play.api.Configuration
import play.api.libs.json.Json
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.play.json.collection.JSONCollection
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import session.SessionRepository

import scala.concurrent.Await

trait UserRepository {
  def login(username: String, password: String): Boolean
  def logout(username: String): Unit
  def isLoggedIn(username: String): Boolean
}

@Singleton
class UserRepositoryImpl @Inject()(val reactiveMongoApi: ReactiveMongoApi,
                                   val configuration: Configuration,
                                   val sessionRepository: SessionRepository) extends UserRepository {
  import models.JsonFormats.userFormat
  import play.modules.reactivemongo.json._
  import scala.concurrent.duration._

  private val salt = configuration.getString("db.salt").get
  private def hash(v: String): String = BCrypt.hashpw(v, salt)

  def collection = Await.result(reactiveMongoApi.database
    .map(db => db.collection[JSONCollection](UserRepository.collectionName)), 5 seconds)

  override def login(username: String, password: String): Boolean = {
    val user = Await.result(collection.find(Json.obj("username" -> username, "password" -> hash(password))).one[User], 5 seconds)
    user.isDefined
  }

  override def isLoggedIn(username: String): Boolean = sessionRepository.isLoggedIn(username)

  // TODO: to be impl.
  override def logout(username: String): Unit = ???
}

object UserRepository {
  val collectionName = "users"
}