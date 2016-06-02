package persistence

import javax.inject.{Inject, Singleton}

import models.User
import play.api.libs.json.Json
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.play.json.collection.JSONCollection
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Await

trait UserRepository {
  def login(username: String, password: String): Boolean
  def logout(username: String): Unit
  def isLoggedIn(username: String): Boolean
}

@Singleton
class UserRepositoryImpl @Inject()(val reactiveMongoApi: ReactiveMongoApi) extends UserRepository {
  import models.JsonFormats.userFormat
  import play.modules.reactivemongo.json._
  import scala.concurrent.duration._

  def collection = Await.result(reactiveMongoApi.database
    .map(db => db.collection[JSONCollection](UserRepository.collectionName)), 5 seconds)

  // TODO: check the hashed password
  override def login(username: String, password: String): Boolean = {
    val user = Await.result(collection.find(Json.obj("username" -> username, "password" -> password)).one[User], 5 seconds)
    user.isDefined
  }

  // TODO: change impl. after testing
  override def isLoggedIn(username: String): Boolean = false

  override def logout(username: String): Unit = ???
}

object UserRepository {
  val collectionName = "users"
}