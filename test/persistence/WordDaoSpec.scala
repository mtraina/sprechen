package persistence

import models.Word
import play.api.test.{PlaySpecification, WithApplication}
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import models.JsonFormats.wordFormat

class WordDaoSpec extends PlaySpecification with EmbeddedMongo {

  "A WordDao" should {
    "get a value" in new WithApplication(){
      // given
      val timeout = 5 seconds
      val reactiveMongoApi = app.injector.instanceOf[ReactiveMongoApi]
      Await.ready(reactiveMongoApi.database
        .map(db => db.collection[JSONCollection](WordDao.collectionName))
          .map(coll => coll.insert(Word("car", Set("Auto")))), timeout)

      // when
      val wordDao = app.injector.instanceOf[WordDao]

      // then
      val result = Await.result(wordDao.find(), timeout)
      result.length must beEqualTo(1)
    }
  }
}
