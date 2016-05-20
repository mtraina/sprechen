package persistence

import models.Speech
import persistence.JsonFormats._
import play.api.test.{PlaySpecification, WithApplication}
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

class SpeechDaoSpec extends PlaySpecification with EmbeddedMongo {

  "SpeechDao" should {
    "get a value" in new WithApplication(){
      // given
      val timeout = 5 seconds
      val reactiveMongoApi = app.injector.instanceOf[ReactiveMongoApi]
      Await.ready(reactiveMongoApi.database
        .map(db => db.collection[JSONCollection](SpeechDao.collectionName))
          .map(coll => coll.insert(Speech("cheers"))), timeout)

      // when
      val speechDao = app.injector.instanceOf[SpeechDao]

      // then
      val result = Await.result(speechDao.find(), timeout)
      result.length must beEqualTo(1)
    }
  }


}
