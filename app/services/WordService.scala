package services

import java.io.File
import javax.inject.Inject

import models.Word
import persistence.WordDao
import play.api.Logger
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.JsString
import play.api.libs.ws.WSResponse
import ws.{SpeechClient, TranslateClient}

import scala.concurrent.Future

trait WordService {
  def getWords(): Future[List[Word]]
  def saveWord(speech: File): Future[WSResponse]
}

class WordServiceImpl @Inject()(val client: SpeechClient,
                                val wordDao: WordDao,
                                val translateClient: TranslateClient) extends WordService {

  override def getWords(): Future[List[Word]] = wordDao.find()

  override def saveWord(speech: File): Future[WSResponse] = {
    val response = client.post(speech)
    response.map { r =>
      val guesses = r.json \\ "transcript"
      Logger.debug(s"save the guesses:{$guesses}")
      wordDao.create(Word(text = guesses.head.asInstanceOf[JsString].value, Set()))
    }
    response
  }

}