package services

import javax.inject.Inject

import play.api.libs.ws.WSClient

trait SpeechService {
  def speechToText(): String
}

class SpeechServiceImpl @Inject()(ws: WSClient) extends SpeechService {
  override def speechToText(): String = "test"
}