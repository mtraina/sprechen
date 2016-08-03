package ws

import javax.inject.Inject

import play.api.Configuration
import play.api.libs.ws.{WSClient, WSResponse}

import scala.concurrent.Future

trait SpeechAuthClient {
  def post(): Future[WSResponse]
}

class SpeechAuthClientImpl @Inject()(val ws: WSClient,
                                     val configuration: Configuration) extends SpeechAuthClient {

  val url = configuration.getString("speech.auth.service.url").get
  val key1 = configuration.getString("speech.auth.service.key1").get
  val key2 = configuration.getString("speech.auth.service.key2").get

  override def post(): Future[WSResponse] = {
    ws.url(url)
      .post(Map(
        "grant_type" -> Seq("client_credentials"),
        "client_id" -> Seq(key1),
        "client_secret" -> Seq(key2),
        "scope" -> Seq("https://speech.platform.bing.com")))
  }
}
