package services

import java.io.File
import javax.inject.Inject

import models.Speech
import persistence.SpeechDao
import play.api.Logger
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.ws.WSResponse
import ws.SpeechWSClient

import scala.concurrent.Future

trait SpeechService {
  def saveSpeech(speech: File): Future[WSResponse]
}

class SpeechServiceImpl @Inject()(val client: SpeechWSClient, val speechDao: SpeechDao) extends SpeechService {

  override def saveSpeech(speech: File): Future[WSResponse] = {
    val response = client.post(speech)
    response.map { r =>
      val guesses = r.json \\ "transcript"
      Logger.debug(s"save the guesses:{$guesses}")
      speechDao.create(Speech(transcript = guesses.head.toString()))
    }
    response
  }

}