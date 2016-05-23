package services

import java.io.File
import javax.inject.Inject

import models.Word
import persistence.WordDao
import play.api.Logger
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{JsDefined, JsArray, JsString}
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

      Logger.debug(s"got the guesses:{$guesses}")

      val guess = guesses.head.asInstanceOf[JsString]

      translateClient.translate(guess.value).map { r =>
        Logger.debug(s"got the translation:{${r.json}}")

        val texts = (r.json \ "text").get.asInstanceOf[JsArray]
        val translations = texts.value.map(v => v.asInstanceOf[JsString].value)
        wordDao.create(Word(text = guess.value, translations = translations))
      }
    }
    response
  }

}