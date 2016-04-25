package services

import javax.inject.Inject

import play.api.libs.ws.WSClient

trait SpeechService {
  def speechToText(): String
}

class SpeechServiceImpl @Inject()(ws: WSClient) extends SpeechService {
  override def speechToText(): String = {
    //val url = "https://stream.watsonplatform.net/speech-to-text/api/v1/recognize?timestamps=true&word_alternatives_threshold=0.9"
    //ws.url(url)
    "text"
  }
}