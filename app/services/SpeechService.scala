package services

import java.io.File
import javax.inject.Inject

import play.api.libs.ws.WSResponse
import ws.SpeechWSClient

import scala.concurrent.Future

trait SpeechService {
  def speechToText(speech: File): Future[WSResponse]
}

class SpeechServiceImpl @Inject()(val client: SpeechWSClient) extends SpeechService {

  override def speechToText(speech: File): Future[WSResponse] = client.post(speech)

}