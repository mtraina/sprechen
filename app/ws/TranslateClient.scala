package ws

import javax.inject.Inject

import play.api.Configuration
import play.api.libs.ws.{WSClient, WSResponse}

import scala.concurrent.Future

trait TranslateClient {
  def translate(text: String): Future[WSResponse]
}

class TranslateClientImpl @Inject() (val ws: WSClient,
                                     val configuration: Configuration) extends TranslateClient {

  val url = configuration.getString("translate.service.url").get
  val key = configuration.getString("translate.service.key").get
  val lang = configuration.getString("translate.service.lang").get

  override def translate(text: String): Future[WSResponse] = {
    ws.url(url)
      .withQueryString("key" -> key)
      .withQueryString("lang" -> lang)
      .withQueryString("text" -> text)
      .get()
  }
}
