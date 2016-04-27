package ws

import play.api.libs.ws.WSResponse

import scala.concurrent.Future

trait WebServiceResponse {
  def getResponse(): Future[WSResponse]
}

class WebServiceResponseImpl(val response: Future[WSResponse]) extends WebServiceResponse {
  override def getResponse(): Future[WSResponse] = response
}
