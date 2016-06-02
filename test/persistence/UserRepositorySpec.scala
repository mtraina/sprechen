package persistence

import models.JsonFormats.userFormat
import models.User
import org.mindrot.jbcrypt.BCrypt
import play.api.test.{PlaySpecification, WithApplication}
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class UserRepositorySpec extends PlaySpecification with EmbeddedMongo {

  "An user repository" should {
    "get a value" in new WithApplication(){
      // given
      val salt = app.configuration.getString("db.salt").get

      val timeout = 5 seconds
      val reactiveMongoApi = app.injector.instanceOf[ReactiveMongoApi]
      Await.ready(reactiveMongoApi.database
        .map(db => db.collection[JSONCollection](UserRepository.collectionName))
        .map(coll => coll.insert(User("user", BCrypt.hashpw("pass", salt)))), timeout)

      // when
      val userRepository = app.injector.instanceOf[UserRepository]

      // then
      val result = userRepository.login("user", "pass")
      result must beTrue
    }
  }
}
