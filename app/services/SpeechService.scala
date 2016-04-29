package services

import java.io.File
import javax.inject.Inject

import play.api.Logger
import ws.SpeechWSClient

import scala.concurrent.ExecutionContext

trait SpeechService {
  def speechToText(speech: File): String
}

class SpeechServiceImpl @Inject()(val client: SpeechWSClient,
                                  implicit val context: ExecutionContext) extends SpeechService {

  override def speechToText(speech: File): String = {

    val response = client.post(speech)
    response.map {
      r => {
        Logger.debug(s"json response=${r.json}")
        val transcripts = r.json \\ "transcript"
        Logger.debug(s"best transcript=${transcripts.head}")
      }
    }
    "text"
  }
}