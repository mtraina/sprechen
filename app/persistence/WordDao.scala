package persistence

import javax.inject.Inject

import models.Word
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.Cursor
import reactivemongo.api.commands.WriteResult
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.{Await, Future}

trait WordDao {
  def find(): Future[List[Word]]

  def create(speech: Word): Future[WriteResult]
}

class WordDaoImpl @Inject()(val reactiveMongoApi: ReactiveMongoApi) extends WordDao {
  import models.JsonFormats.wordFormat
  import play.modules.reactivemongo.json._
  import scala.concurrent.duration._

  def collection = Await.result(reactiveMongoApi.database
    .map(db => db.collection[JSONCollection](WordDao.collectionName)), 5 seconds)

  override def find(): Future[List[Word]] = {
    val cursor: Cursor[Word] = collection.find(Json.obj()).cursor[Word]()
    val speeches: Future[List[Word]] = cursor.collect[List]()
    speeches
  }

  override def create(word: Word): Future[WriteResult] = collection.insert(word)
}

object WordDao {
  val collectionName = "dictionary"
}

