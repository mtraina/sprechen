package services

import java.io.File

import org.mockito.BDDMockito._
import org.scalatest.mock.MockitoSugar
import org.scalatest.{FlatSpec, Matchers}
import persistence.WordDao
import play.api.libs.json.Json
import play.api.libs.ws.WSResponse
import ws.{TranslateClient, SpeechClient}

import scala.concurrent.Future

class SpeechServiceImplSpec extends FlatSpec with Matchers with MockitoSugar {
  val f = new File("")
  val r = mock[WSResponse]
  val json = Json.parse("""{"transcript":"text"}""")
  given(r.json).willReturn(json)
  val response = Future.successful(r)
  val client = mock[SpeechClient]
  val dao = mock[WordDao]
  given(client.post(f)) willReturn response

  val tc = mock[TranslateClient]
  val speechService = new WordServiceImpl(client, dao, tc)

  "A speech service" should "save the speech and return the future of the value" in {
    val resp = speechService.saveWord(f)
    resp.value.get.get.json shouldBe json
  }

}
