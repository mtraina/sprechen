package services

import java.io.File

import org.mockito.BDDMockito._
import org.scalatest.mock.MockitoSugar
import org.scalatest.{FlatSpec, Matchers}
import persistence.SpeechDao
import play.api.libs.json.Json
import play.api.libs.ws.WSResponse
import ws.SpeechWSClient

import scala.concurrent.Future

class SpeechServiceImplSpec extends FlatSpec with Matchers with MockitoSugar {
  val f = new File("")
  val r = mock[WSResponse]
  val json = Json.parse("""{"transcript":"text"}""")
  given(r.json).willReturn(json)
  val response = Future.successful(r)
  val client = mock[SpeechWSClient]
  val dao = mock[SpeechDao]
  given(client.post(f)) willReturn response
  val speechService = new SpeechServiceImpl(client, dao)

  "A speech service" should "save the speech and return the future of the value" in {
    val resp = speechService.saveSpeech(f)
    resp.value.get.get.json shouldBe json
  }

}
