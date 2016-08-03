package services

import java.io.File
import javax.inject.Inject

import models.Word
import persistence.WordRepository
import play.api.Logger
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.{JsArray, JsString}
import play.api.libs.ws.WSResponse
import ws.{SpeechClient, TranslateClient}

import scala.concurrent.Future

trait WordService {
  def getWords(): Future[List[Word]]
  def saveWord(speech: File): Future[WSResponse]
}

class WordServiceImpl @Inject()(val client: SpeechClient,
                                val wordRepository: WordRepository,
                                val translateClient: TranslateClient) extends WordService {

  override def getWords(): Future[List[Word]] = wordRepository.find()

  override def saveWord(speech: File): Future[WSResponse] = {
    val response = client.post(speech)
    response.map { r =>
      Logger.debug(s"got the recognition:{${r.json}")
      val text = (r.json \ "header" \ "lexical").as[String]
      Logger.debug(s"got the lexical: $text")

      translateClient.translate(text).map { r =>
        Logger.debug(s"got the translation:{${r.json}}")
        val texts = (r.json \ "text").get.asInstanceOf[JsArray]
        val translations = texts.value.map(v => v.asInstanceOf[JsString].value)
        wordRepository.create(Word(text = text, translations = translations))
      }
    }
    response
  }
}