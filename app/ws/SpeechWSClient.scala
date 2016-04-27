package ws

import java.io.File
import javax.inject.Inject

import play.api.Configuration
import play.api.libs.ws.WSAuthScheme.BASIC
import play.api.libs.ws.WSClient

trait SpeechWSClient {
  def post(speech: File): WebServiceResponse
}

class SpeechWSClientImpl @Inject()(val ws: WSClient, val configuration: Configuration) extends SpeechWSClient {

  val url = configuration.getString("speech.converter.service.url").get
  val username = configuration.getString("speech.converter.service.username").get
  val password = configuration.getString("speech.converter.service.password").get

  def post(speech: File): WebServiceResponse = {
    new WebServiceResponseImpl(ws.url(url)
      .withAuth(username, password, BASIC)
      .withHeaders("content-type" -> "audio/wav")
      .post(new File("data/myRecording01.wav")))
  }
}
