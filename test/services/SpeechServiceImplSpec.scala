package services

import java.io.File

import org.mockito.BDDMockito._
import org.scalatest.mock.MockitoSugar
import org.scalatest.{FlatSpec, Matchers}
import persistence.SpeechDao
import play.api.libs.ws.WSResponse
import ws.SpeechWSClient

import scala.concurrent.Future

class SpeechServiceImplSpec extends FlatSpec with Matchers with MockitoSugar {
  val f = new File("")
  val r = mock[WSResponse]
  val response = Future.successful(r)
  val client = mock[SpeechWSClient]
  val dao = mock[SpeechDao]
  given(client.post(f)) willReturn response
  val speechService = new SpeechServiceImpl(client, dao)

  "A speech service" should "return the text related to the speech" in {
    speechService.speechToText(f) shouldBe response
  }

}
