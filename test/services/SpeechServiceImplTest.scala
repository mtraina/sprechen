package services

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}
import play.api.libs.ws.WSClient

class SpeechServiceImplTest extends FlatSpec with Matchers with MockFactory {
  val wsClient = mock[WSClient]
  val speechService = new SpeechServiceImpl(wsClient)

  "A Speech service" should "return the text related to the speech" in {
    speechService.speechToText() shouldBe "test"
  }

}
