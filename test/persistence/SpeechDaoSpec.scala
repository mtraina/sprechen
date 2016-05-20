package persistence

import models.Speech
import persistence.JsonFormats._
import play.api.test.{PlaySpecification, WithApplication}
import play.modules.reactivemongo.ReactiveMongoApi

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class SpeechDaoSpec extends PlaySpecification with EmbeddedMongo {

  "SpeechDao" should {
    "get a value" in new WithApplication(){
      // given
      lazy val reactiveMongoApi = app.injector.instanceOf[ReactiveMongoApi]
      val db = Await.result(reactiveMongoApi.database, 5 seconds)
      val collection =  db.collection[JSONCollection](SpeechDao.collectionName)
      val res = Await.result(collection.insert(Speech("cheers")), 5 seconds)

      // when
      val speechDao = app.injector.instanceOf[SpeechDao]
      val timeout = 5 seconds

      // then
      val result = Await.result(speechDao.find(), timeout)
      result.length must beEqualTo(1)
    }
  }


}
