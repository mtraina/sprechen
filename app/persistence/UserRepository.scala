package persistence

import javax.inject.{Inject, Singleton}

import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.Await

trait UserRepository {
  def login(username: String, password: String): Boolean
  def logout(username: String): Unit
  def isLoggedIn(username: String): Boolean
}

@Singleton
class UserRepositoryImpl @Inject()(val reactiveMongoApi: ReactiveMongoApi) extends UserRepository {
  import scala.concurrent.duration._
  import scala.concurrent.ExecutionContext.Implicits.global

  def collection = Await.result(reactiveMongoApi.database
    .map(db => db.collection[JSONCollection](UserRepository.collectionName)), 5 seconds)

  // TODO: change impl. after testing
  override def login(username: String, password: String): Boolean = true

  // TODO: change impl. after testing
  override def isLoggedIn(username: String): Boolean = true

  override def logout(username: String): Unit = ???
}

object UserRepository {
  val collectionName = "users"
}