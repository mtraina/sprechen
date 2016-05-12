package services

import java.io.File
import javax.inject.Inject

import models.Speech
import persistence.SpeechDao
import play.api.libs.ws.WSResponse
import reactivemongo.api.commands.WriteResult
import ws.SpeechWSClient

import scala.concurrent.Future

trait SpeechService {
  def speechToText(speech: File): Future[WSResponse]

  def createSpeech(): Future[WriteResult]
}

class SpeechServiceImpl @Inject()(val client: SpeechWSClient, val speechDao: SpeechDao) extends SpeechService {

  override def speechToText(speech: File): Future[WSResponse] = client.post(speech)

  override def createSpeech(): Future[WriteResult] = {
    speechDao.create(Speech(transcript = "cheers"))
  }
}