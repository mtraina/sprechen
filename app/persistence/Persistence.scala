package persistence

import javax.inject.Inject

import models.Speech
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.Cursor
import reactivemongo.api.commands.WriteResult
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.{Await, Future}

trait SpeechDao {
  def find(): Future[List[Speech]]

  def create(speech: Speech): Future[WriteResult]
}

class SpeechDaoImpl @Inject()(val reactiveMongoApi: ReactiveMongoApi) extends SpeechDao {

  import models.JsonFormats._
  import play.modules.reactivemongo.json._
  import scala.concurrent.duration._

  def collection = Await.result(reactiveMongoApi.database
    .map(db => db.collection[JSONCollection](SpeechDao.collectionName)), 5 seconds)

  override def find(): Future[List[Speech]] = {
    val cursor: Cursor[Speech] = collection.find(Json.obj()).cursor[Speech]()
    val speeches: Future[List[Speech]] = cursor.collect[List]()
    speeches
  }

  override def create(speech: Speech): Future[WriteResult] = collection.insert(speech)

}

object SpeechDao {
  val collectionName = "speeches"
}

