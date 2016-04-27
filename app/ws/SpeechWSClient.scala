package ws

import java.io.File
import javax.inject.Inject

import play.api.Configuration
import play.api.libs.ws.WSAuthScheme.BASIC
import play.api.libs.ws.{WSResponse, WSClient}

import scala.concurrent.Future

trait SpeechWSClient {
  def post(speech: File): Future[WSResponse]
}

class SpeechWSClientImpl @Inject()(val ws: WSClient,
                                   val configuration: Configuration) extends SpeechWSClient {

  val url = configuration.getString("speech.converter.service.url").get
  val username = configuration.getString("speech.converter.service.username").get
  val password = configuration.getString("speech.converter.service.password").get

  def post(speech: File): Future[WSResponse] = {
    ws.url(url)
      .withAuth(username, password, BASIC)
      .withHeaders("content-type" -> "audio/wav")
      .post(speech)
  }
}
