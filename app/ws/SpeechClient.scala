package ws

import java.io.File
import javax.inject.Inject

import play.api.{Configuration, Logger}
import play.api.libs.json.JsString
import play.api.libs.ws.WSAuthScheme.BASIC
import play.api.libs.ws.{WSClient, WSResponse}

import scala.concurrent.Future

trait SpeechClient {
  def post(speech: File): Future[WSResponse]
}

class SpeechClientImpl @Inject()(val ws: WSClient,
                                 val configuration: Configuration,
                                 val speechAuthClient: SpeechAuthClient) extends SpeechClient {

  val url = configuration.getString("speech.converter.service.url").get
  val appId = configuration.getString("speech.converter.service.appId").get
  val uniqueId = configuration.getString("speech.converter.service.uniqueId").get

  override def post(speech: File): Future[WSResponse] = {
    import scala.concurrent.ExecutionContext.Implicits.global

    for {
      auth <- speechAuthClient.post()
      text <- recognize(speech, extractAccessToken(auth))
    } yield text
  }

  private def extractAccessToken(auth: WSResponse): String = {
    val accessToken = auth.json \\ "access_token"
    accessToken.head.asInstanceOf[JsString].value
  }

  private def recognize(speech: File, accessToken: String): Future[WSResponse] = {
    ws.url(url)
      .withHeaders(
        "Authorization" -> s"Bearer $accessToken",
        "content-type" -> "audio/wav; samplerate=8000")
      .withQueryString("scenarios" -> "websearch")
      .withQueryString("appID" -> appId)
      .withQueryString("locale" -> "en-US")
      .withQueryString("device.os" -> "Android")
      .withQueryString("version" -> "3.0")
      .withQueryString("format" -> "json")
      .withQueryString("requestid" -> uniqueId)
      .withQueryString("instanceId" -> uniqueId)
      .post(speech)
  }

}
