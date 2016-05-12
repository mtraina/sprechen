package persistence

import javax.inject.Inject

import models.Speech
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.commands.WriteResult
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.Future

object JsonFormats {
  import play.api.libs.json.Json

  implicit val speechFormat = Json.format[Speech]
}

trait SpeechDao {
  def create(speech: Speech): Future[WriteResult]
}

class SpeechDaoImpl @Inject()(val reactiveMongoApi: ReactiveMongoApi) extends SpeechDao {

  import JsonFormats.speechFormat

  def collection: JSONCollection = reactiveMongoApi.db.collection[JSONCollection]("speeches")

  override def create(speech: Speech): Future[WriteResult] = collection.insert(speech)
}

