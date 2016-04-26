package services

import java.io.File
import javax.inject.Inject

import play.api.Configuration
import play.api.libs.ws.WSAuthScheme.BASIC
import play.api.libs.ws.{WSClient, WSResponse}

import scala.concurrent.{ExecutionContext, Future}

trait SpeechService {
  def speechToText(): String
}

class SpeechServiceImpl @Inject()(val ws: WSClient,
                                  implicit val context: ExecutionContext,
                                  val configuration: Configuration) extends SpeechService {

  override def speechToText(): String = {
    val url = configuration.getString("speech.converter.service.url")
    val username = configuration.getString("speech.converter.service.username")
    val password = configuration.getString("speech.converter.service.password")

    val response: Future[WSResponse] = ws.url(url.get)
      .withAuth(username.get, password.get, BASIC)
      .withHeaders("content-type" -> "audio/wav")
        .post(new File("data/myRecording01.wav"))

    //val r = Await.result(response, Duration.Inf)

    response.map {
      r => print(r.json)
    }
    "text"
  }
}