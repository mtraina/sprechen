package persistence

import javax.inject.Inject

import models.Speech
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.Cursor
import reactivemongo.api.commands.WriteResult
import reactivemongo.bson.BSONDocument
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.Future

object JsonFormats {
  import play.api.libs.json.Json

  implicit val speechFormat = Json.format[Speech]
}

trait SpeechDao {
  def find(): Future[List[Speech]]

  def create(speech: Speech): Future[WriteResult]
}

class SpeechDaoImpl @Inject()(val reactiveMongoApi: ReactiveMongoApi) extends SpeechDao {

  import JsonFormats._
  import play.modules.reactivemongo.json._

  def collection: JSONCollection = reactiveMongoApi.db.collection[JSONCollection]("speeches")

  override def find(): Future[List[Speech]] = {
    //val query = BSONDocument("transcript" -> BSONDocument("$gt" -> 27))
    val cursor: Cursor[Speech] = collection.find(Json.obj("transcript" -> "cheers")).cursor[Speech]
    //val speeches: Future[List[Speech]] = cursor.collect[List]()
    val speeches: Future[List[Speech]] = cursor.collect[List]()
    speeches
  }

  override def create(speech: Speech): Future[WriteResult] = collection.insert(speech)

}

